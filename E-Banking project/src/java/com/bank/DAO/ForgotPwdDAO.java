package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bank.FormBean.RegisterFormBean;
import com.bank.db.ConnectionFactory;
import com.ibm.Authentication.DesEncrypter;

public class ForgotPwdDAO {
		
		
		PreparedStatement pstmt,pstmt1;
		Statement stmt;
		ResultSet rs,rs1;
		Connection con;
		
		private  String ghost="smtp.gmail.com";
		private  String port="465";
		private  String socketFactoryClass="javax.net.ssl.SSLSocketFactory";
		
		
		public ForgotPwdDAO(){
			
			con=ConnectionFactory.getConnection();
			System.out.println("conn in dao is -----"+con);
		
		}
public boolean postMailTo(String mailTo){
	
	RegisterFormBean rb=null;
	boolean flag=false;
	try
	{
	pstmt = con.prepareStatement("select * from HELLO.userdetails where email=?");
	
	pstmt.setString(1,mailTo);
     rs = pstmt.executeQuery();
	
	
	while(rs.next())
	{
		
		rb=new RegisterFormBean();
		
		rb.setEmail(rs.getString(6));
	    rb.setPassword(rs.getString(7));
	
	}
	
	DesEncrypter encrypter=new DesEncrypter("passPhrase");
	
	System.out.println("Encripted password=========>>>>>"+rb.getPassword());
	String decriptpwd=encrypter.decrypt(rb.getPassword());
	System.out.println("decripted-------------->"+decriptpwd);
	
	
    String username=rb.getEmail();
    String pwd=decriptpwd;
	
	String body="We are Happy to Inform that Your Registration is Successfull  Under Bank your can Have the Bank Services and u r username is------- "+username+   "\npassword is--------"+pwd;
	String mailFrom="edues2013@gmail.com";
	String password="9705368807";
	
		flag=false;
		
		if(mailFrom.substring(mailFrom.indexOf("@")+1).equals("yahoo.com") || mailFrom.substring(mailFrom.indexOf("@")+1).equals("yahoo.in")){
			ghost="smtp.mail.yahoo.com";
			port="25";
		}
	System.out.println("mail to ------------>>"+mailTo);
	System.out.println("mail from------>>"+mailFrom);
		
		
					
			Properties props=new Properties();
			props.put("mail.smtp.user", mailFrom);
			props.put("mail.smtp.host", ghost);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "false");
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			Session session=Session.getDefaultInstance(props,null);
			MimeMessage msg=new MimeMessage(session);
			msg.setText(body);
			msg.setSubject("Mail From E-Dues Account Creation Dept ......");
			msg.setFrom(new InternetAddress(mailFrom));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			msg.saveChanges();
			Transport transport=session.getTransport("smtp");
			transport.connect(ghost, mailFrom, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			flag=true;
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
	
