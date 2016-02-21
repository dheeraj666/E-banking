
package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDao {
	
	Connection con;
	PreparedStatement pstmt,pstmt1;
	ResultSet rs,rs1;
	
	
	
	
	private  String ghost="smtp.gmail.com";
	private  String port="465";
	private  String socketFactoryClass="javax.net.ssl.SSLSocketFactory";
	
	public MailDao(){
		
		con=com.bank.db.ConnectionFactory.getConnection();

		
	}
	
	
public boolean postMailTo(String mailTo,String mailFrom,String password,String body,String status,String aid){
		
		boolean flag=false;
		if(mailFrom.substring(mailFrom.indexOf("@")+1).equals("yahoo.com") || mailFrom.substring(mailFrom.indexOf("@")+1).equals("yahoo.in")){
			ghost="smtp.mail.yahoo.com";
			port="25";
		}
	
		
		try{
					
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
			msg.setSubject("Mail From Admission Dept of Business School......");
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

