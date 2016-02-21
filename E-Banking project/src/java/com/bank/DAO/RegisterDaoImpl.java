package com.bank.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bank.FormBean.RegisterFormBean;
import com.bank.db.ConnectionFactory;
import com.bank.util.CoreList;
import com.bank.util.LoggerManager;
import com.ibm.Authentication.DesEncrypter;




public class RegisterDaoImpl {

	Connection con;
	PreparedStatement pstmt,pstmt1,pstmt2,pstmt3,pstmt4;
	Statement stmt;
	ResultSet rs,rs1;
	
	private  String ghost="smtp.gmail.com";
	private  String port="465";
	private  String socketFactoryClass="javax.net.ssl.SSLSocketFactory";
	
	
	
private	String mailFrom="edues2013@gmail.com";
	private String password="9705368807";
	
	
	public RegisterDaoImpl(){
		
		
		con=ConnectionFactory.getConnection();
		
	}
	
	 public boolean insertNewUser(RegisterFormBean cp) throws FileNotFoundException
	    
	    {
	    	boolean flag=true;
	    	int insert=0;	  
	    	int acc=0;
	    	int uid=0;
	    	String loginid=cp.getUserid();
    		String acctype=cp.getAcctype();
    		double accbal=cp.getAccbal();
    		String photo =cp.getPhoto();
    		
    		String dob=cp.getDob();
    		
    		
    		String fname=cp.getFname();
    		String lname=cp.getLname();
    		String squestion=cp.getQuestn();
    		String ans=cp.getAnsr();
    		String email=cp.getEmail();
    		String phone=cp.getMobile();
    	
    		
    		
    		String pwd=cp.getUserpwd();	  
    		
    		DesEncrypter encrypter=new DesEncrypter("passPhrase");
        	String encriptpwd=encrypter.encrypt(pwd);
     
        	String decriptpwd=encrypter.decrypt(encriptpwd);
        	
        	
    		String hno=cp.getHno();
    		String street=cp.getStreet();
    		String city=cp.getCity();
    		String state=cp.getState();
    		String country=cp.getCountry();
    		String pin=cp.getPin();	   
	    	try{	    		
	    		
	    		
	    		
	    		pstmt3=con.prepareStatement("select userid from EBAN.userdetails where loginid=?");
	    		
	    		pstmt3.setString(1, loginid);
	            rs=pstmt3.executeQuery();
	            if(rs.next())
	           
	            	uid=rs.getInt(1);
	            
	            
	            if(uid==0)
	            {
	    		
		    	
	            pstmt=con.prepareStatement("insert into EBAN.userdetails(firstname,lastname,nickname,dob,dor,loginid,password,logintype,squestion,sanswer,emailid,photo) values(?,?,?,?,(select current date from sysibm.sysdummy1),?,?,?,?,?,?,?)");	            		    		
	    		
	    		
	    		
	    		
	    		
	        	File f=new File("D:/"+photo);
	        	
	        	FileInputStream fis=new FileInputStream(f); 
	        	 
	        	
	        	
	        	String nname=cp.getNname();
	        	
	        	
	        		    		
	        		    		
	            pstmt.setString(1, fname);
	            pstmt.setString(2, lname);
	            pstmt.setString(3, nname);
	            pstmt.setString(4, dob);
	            pstmt.setString(5, loginid);
	            pstmt.setString(6, encriptpwd);
	            pstmt.setString(7, "customer");
	            pstmt.setString(8, squestion);
	            pstmt.setString(9, ans);
	          
	          
	          
	          pstmt.setString(10, email);
	          pstmt.setBinaryStream(11, fis,(int)f.length());
	        
	      
	          int i=pstmt.executeUpdate();
	          
	          if(i>0)
	          {	        	  
	        	  pstmt1=con.prepareStatement("insert into EBAN.address(userid,houseno,street,city,state,country,pincode,phno) values((select max(userid) from EBAN.userdetails),?,?,?,?,?,?,?)");	        	  	        	  
	        	  pstmt1.setString(1, hno);
	        	  pstmt1.setString(2, street);
	        	  pstmt1.setString(3, city);
	        	  pstmt1.setString(4, state);
	        	  pstmt1.setString(5, country);
	        	  pstmt1.setString(6,pin);
	        	  pstmt1.setString(7, phone);
	        	  
	        	  
	        	  
	        	  insert=pstmt1.executeUpdate();
	        	  }	          	          
	          if(insert>0)
		         {
	        	  
	        	  pstmt2=con.prepareStatement("insert into EBAN.accountdetails(acctype,userid,tdate,accstatus,accbal,accopendate)  values(?,(select max(userid) from EBAN.userdetails),?,?,?,(select current date from sysibm.sysdummy1))");
	        	  
	        	  
	        	  String tdate=cp.getTdate();
	        	  
	        	 double bal =cp.getAccbal();
	        	  
	        	  pstmt2.setString(1, acctype);
	        	
	        	  if(tdate!=null){
	        	  pstmt2.setString(2, tdate);
	        	  }
	        	  else{
	        		  pstmt2.setString(2, null);
	        	  }
	        	  pstmt2.setString(3,"active");
	        	 
	            pstmt2.setDouble(4, bal);
	        	 	  
	              
	        	  acc= pstmt2.executeUpdate();
	              
	        	  
		         }
	          
	          if(acc>0){
	        	  
	        	  String body="We are Happy to Inform that your Registration is Successfull and you can Enjoy our Services with the following username--> "+loginid+"password--"+pwd;
	        	  
	        	  postMailTo(email, mailFrom, password, body);
	        	  
	        	  
		          con.commit();
		         }
		         else
		         {
		        	 flag=false;
		        	 con.rollback();
		         }
		    } 
	            else{
	            	
	            	pstmt4=con.prepareStatement("insert into EBAN.accountdetails(acctype,userid,tdate,accstatus,accbal,accopendate)  values(?,(select max(userid) from EBAN.userdetails),?,?,?,(select current date from sysibm.sysdummy1))");
		        	  
		        	  String tdate=cp.getTdate();
		        	  
		        	 
		        	 double bal=cp.getAccbal();
		        	  
		        	  pstmt4.setString(1, acctype);
		        	  
		        	 pstmt4.setString(2, tdate);
		        	  
		        	  pstmt4.setString(3,"active");
		        	  
		        	  pstmt4.setDouble(4, bal);
		        	  
		        	  
		        	  
		           int   update= pstmt4.executeUpdate();
		              
	            	System.out.println("in Dao class Update in accountdetails is..........."+update);
	            	
	            	
	            }
	            
	    	}
		    catch (SQLException e) 
		    {
		        e.printStackTrace();
		        flag=false;
		        try 
		        {
		            con.rollback();
		        } 
		        catch (SQLException se) 
		        {
		            se.printStackTrace();
		        }
		    }
		    
		    finally
			{
			 try{
				 if(con!=null)
					 con.close();				 
			 }
			 catch(SQLException e){}
			}
		return flag;
	}
	 
	
	 
	 public boolean updateUser(RegisterFormBean cp) throws FileNotFoundException
	    
	    {
	    	boolean flag=true;
	    	int uid=0;
	    	int insert=0;	  
	    
	    	try{	   
	    		int accno=cp.getAccno();
	    		
	    		pstmt=con.prepareStatement("select userid from EBAN.accountdetails  where accno=?");
	            pstmt.setInt(1, accno);
	            rs=pstmt.executeQuery();
	            if(rs.next())
	            	
	            	uid=rs.getInt(1);
	            
	                      	
	            pstmt=con.prepareStatement("update EBAN.userdetails set emailid=? where userid=?");	            		    		
	    		
	    		String email=cp.getEmail();
	    		String phone=cp.getMobile();
	    	
	    		 			    		
	    		String hno=cp.getHno();
	    		String street=cp.getStreet();
	    		String city=cp.getCity();
	    		String state=cp.getState();
	    		String country=cp.getCountry();
	    		String pin=cp.getPin();	   
	    		
	    			        	
	            
	          pstmt.setString(1, email);
	          pstmt.setInt(2, uid);
	        
	      
	          int i=pstmt.executeUpdate();
	          
	          if(i>0)
	          {	        	  
	        	  pstmt1=con.prepareStatement("update EBAN.address set houseno=?,street=?,city=?,state=?,country=?,pincode=?,phno=? where userid=?");	        	  	        	  
	        	  pstmt1.setString(1, hno);
	        	  pstmt1.setString(2, street);
	        	  pstmt1.setString(3, city);
	        	  pstmt1.setString(4, state);
	        	  pstmt1.setString(5, country);
	        	  pstmt1.setString(6,pin);
	        	  pstmt1.setString(7, phone);
	        	  pstmt1.setInt(8, uid);
	        	  
	        
	        	  insert=pstmt1.executeUpdate();
	        	  }	          	          
	          
	        
	          if(insert>0){
		          con.commit();
		         }
		         else
		         {
		        	 flag=false;
		        	 con.rollback();
		         }
		    } 
		    catch (SQLException e) 
		    {
		        e.printStackTrace();
		        flag=false;
		        try 
		        {
		            con.rollback();
		        } 
		        catch (SQLException se) 
		        {
		            se.printStackTrace();
		        }
		    }
		    
		    finally
			{
			 try{
				 if(con!=null)
					 con.close();				 
			 }
			 catch(SQLException e){}
			}
		return flag;
	}
	 
	 
	 
	 public boolean updateProfile(RegisterFormBean cp) throws FileNotFoundException
	    
	    {
	    	boolean flag=true;
	    	int uid=0;
	    	int insert=0;	  
	    
	    	try{	   
	    		String login=cp.getLogin();
	    		
	    		pstmt=con.prepareStatement("select userid from EBAN.userdetails  where loginid=?");
	            pstmt.setString(1, login);
	            rs=pstmt.executeQuery();
	            if(rs.next())
	            	
	            	uid=rs.getInt(1);
	            
	                   	
	            pstmt=con.prepareStatement("update EBAN.userdetails set emailid=? where userid=?");	            		    		
	    		
	    		String email=cp.getEmail();
	    		String phone=cp.getMobile();
	    	
	    		 			    		
	    		String hno=cp.getHno();
	    		String street=cp.getStreet();
	    		String city=cp.getCity();
	    		String state=cp.getState();
	    		String country=cp.getCountry();
	    		String pin=cp.getPin();	   
	    		
	    			        	
	            
	          pstmt.setString(1, email);
	          pstmt.setInt(2, uid);
	        
	      
	          int i=pstmt.executeUpdate();
	          
	          if(i>0)
	          {	        	  
	        	  pstmt1=con.prepareStatement("update EBAN.address set houseno=?,street=?,city=?,state=?,country=?,pincode=?,phno=? where userid=?");	        	  	        	  
	        	  pstmt1.setString(1, hno);
	        	  pstmt1.setString(2, street);
	        	  pstmt1.setString(3, city);
	        	  pstmt1.setString(4, state);
	        	  pstmt1.setString(5, country);
	        	  pstmt1.setString(6,pin);
	        	  pstmt1.setString(7, phone);
	        	  pstmt1.setInt(8, uid);
	        	  
	        
	        	  insert=pstmt1.executeUpdate();
	        	  }	          	          
	          
	        
	          if(insert>0){
		          con.commit();
		         }
		         else
		         {
		        	 flag=false;
		        	 con.rollback();
		         }
		    } 
		    catch (SQLException e) 
		    {
		        e.printStackTrace();
		        flag=false;
		        try 
		        {
		            con.rollback();
		        } 
		        catch (SQLException se) 
		        {
		            se.printStackTrace();
		        }
		    }
		    
		    finally
			{
			 try{
				 if(con!=null)
					 con.close();				 
			 }
			 catch(SQLException e){}
			}
		return flag;
	}
	 
	 public void logout1(String user){
	    	
	    	
	    	try{
	    		pstmt=con.prepareStatement("update EBAN.loginmaster set logofftime=sysdate where userid=? and logofftime=null");
	    		pstmt.setString(1, user);
	    		int c=pstmt.executeUpdate();
	    		if(c>0){
	     			con.commit();
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    }
	 public boolean logout(String loginid)
	    {
		 
		 boolean flag=true;
	        try 
	        {
	        	
	        	
	        int uid=0;
	      
	       
	        	
	            pstmt=con.prepareStatement("select userid from EBAN.userdetails where loginid=?");
	            pstmt.setString(1,loginid);
	            rs=pstmt.executeQuery();
	            if(rs.next())
	            	
	            	uid=rs.getInt(1);
	            
	           
	           
	            pstmt1=con.prepareStatement("update EBAN.loginmaster set logofftime=sysdate where logofftime is null and userid=?");
	            pstmt1.setInt(1, uid);
	            
	            int update=pstmt1.executeUpdate();
	            
	          

				if(update>0)
		         {
		         
		          con.commit();
		         }
		         else
		         {
		        	 flag=false;
		        	 con.rollback();
		         }

		    } 
		    
		    catch (SQLException e) 
		    {
		        e.printStackTrace();
		        flag=false;
		        try 
		        {
		            con.rollback();
		        } 
		        catch (SQLException se) 
		        {
		            se.printStackTrace();
		        }
		    }
		    
		    finally
			{
			 try{
				 if(con!=null)
					 con.close();				 
			 }
			 catch(SQLException e){}
			}
		return flag;
	}
	 
	 
	 public String passwordRecovery(RegisterFormBean rto){
	    	String password="";
	        try{
	        	
	        	 String question=rto.getQuestn();
				 String ans=rto.getAnsr();
				 String loginid=rto.getUserid();
				
	        	pstmt=con.prepareStatement("select password from EBAN.userdetails where loginid=? and forgotpwquestion=? and forgotpwanswer=?");
	        	pstmt.setString(1, loginid);
	        	pstmt.setString(2, question);
	        	pstmt.setString(3, ans);
	        	rs=pstmt.executeQuery();
	        	if(rs.next()){
	        		password=rs.getString(1);
	        	}
	        }catch(SQLException e){
	        	e.printStackTrace();
	        }
	    	return password;
	    }
	 
	 
	 public boolean checkAvailability(String userid){
	    	boolean flag=false;
	    	try{
	    		pstmt=con.prepareStatement("select userid from EBAN.userdetails where loginid=?");
	    		pstmt.setString(1, userid);
	    		rs=pstmt.executeQuery();
	    		if(rs.next()){
	    			flag=true;
	    		}
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	return flag;
	    }
	    
	 
	 public boolean changePass(RegisterFormBean rto){
	    	boolean flag=false;
	    	try{
	    		
	    
	    		
	    		String newpass=rto.getNewpassword();
	    		
	    		String oldpasswprd=rto.getOldpassword();
	    		String user=rto.getUsername();
	    		
	    		pstmt=con.prepareStatement("update EBAN.userdetails set password=? where loginid=? and password=?");
	    		pstmt.setString(1, newpass);
	    		pstmt.setString(2, user);
	    		pstmt.setString(3, oldpasswprd);
	    		int c=pstmt.executeUpdate();
	    		if(c>0){
	    			flag=true;
	    			con.commit();
	    		}
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    	return flag;
	    }
	 
	 
	 
	 public boolean changeQuestion(RegisterFormBean rto)
	 {
		 boolean flag=true;
		 
		 try{
			 
			 
			
			 
			 String question=rto.getQuestn();
			 
			 String ans=rto.getAnsr();
			 
			 String loginid=rto.getUserid();
			
			 String pwd=rto.getUserpwd();
			
			 
			 pstmt=con.prepareStatement("update EBAN.userdetails set forgotpwquestion=?,forgotpwanswer=? where loginid=? and password=?");
			 pstmt.setString(1, question);
			 pstmt.setString(2, ans);
			 pstmt.setString(3, loginid);
			 pstmt.setString(4, pwd);
			 
			 int update=pstmt.executeUpdate();
			 
			 
			 if(update>0)
	         {
	         
	          con.commit();
	         }
	         else
	         {
	        	 flag=false;
	        	 con.rollback();
	         }

	    } 
	    
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        flag=false;
	        try 
	        {
	            con.rollback();
	        } 
	        catch (SQLException se) 
	        {
	            se.printStackTrace();
	        }
	    }
	    
	    finally
		{
		 try{
			 if(con!=null)
				 con.close();				 
		 }
		 catch(SQLException e){}
		}
	return flag;
}

	public boolean deleteEmployee(int uid) {
		// TODO Auto-generated method stub
		

		boolean flag=true;
		
		System.out.println(""+uid);
		
		try{
			
		
		
		
		pstmt=con.prepareStatement("delete from EBAN.USERDETAILS where userid=?");
		pstmt.setInt(1, uid);
	
		int delete1=pstmt.executeUpdate();
    
		if(delete1>0)
	    {
	    
	     con.commit();
	    }
	    else
	    {
	   	 flag=false;
	   	 con.rollback();
	    }

} 

catch (Exception e) 
{
   e.printStackTrace();
   flag=false;
   try 
   {
       con.rollback();
   } 
   catch (SQLException se) 
   {
       se.printStackTrace();
   }
}

finally
{
 try{
	 if(con!=null)
		 con.close();				 
 }
 catch(SQLException e){
	 e.printStackTrace();
	 
 }
}
return flag;

 
		
	}
	public boolean insertEmployees(RegisterFormBean cp) throws FileNotFoundException

	{
		
		boolean flag=false;
		int insert=0;	    	
		try{	    		
			
			con.setAutoCommit(false);
	    	
	        pstmt=con.prepareStatement("INSERT INTO EBAN.USERDETAILS(FIRSTNAME,LASTNAME,NICKNAME,DOB,DOR,LOGINID,PASSWORD,LOGINTYPE,SQUESTION,SANSWER,EMAILID,PHOTO) values (?,?,?,(select current date from SYSIBM.SYSDUMMY1),?,?,?,?,?,?,?,?)");	            		    		
		
			String photo =cp.getPhoto();
			
			String dob=cp.getDob();
			
			
			String fname=cp.getFname();
			String lname=cp.getLname();
			String nname=cp.getNname();
			String squestion=cp.getQuestn();
			String ans=cp.getAnsr();
			String email=cp.getEmail();
			String phone=cp.getMobile();
			
		
			String loginid=cp.getUserid();
			String pwd=cp.getUserpwd();	 
			

    		DesEncrypter encrypter=new DesEncrypter("passPhrase");
        	String encriptpwd=encrypter.encrypt(pwd);
        	
        	String decriptpwd=encrypter.decrypt(encriptpwd);
        	
			
			String hno=cp.getHno();
			String street=cp.getStreet();
			String city=cp.getCity();
			String state=cp.getState();
			String country=cp.getCountry();
			String pin=cp.getPin();	    		
			
	    	File f=new File("D:/"+photo);
	    	FileInputStream fis=new FileInputStream(f); 
	    	System.out.println("fole="+f.length());	        	
	    	
	    		    		        	
	        pstmt.setString(1, fname);
	        pstmt.setString(2, lname);
	        pstmt.setString(3, nname);
	        pstmt.setString(4, dob);
	        pstmt.setString(5, loginid);
	     
	        pstmt.setString(6, encriptpwd);
	        pstmt.setString(7, "employee");
	        pstmt.setString(8, squestion);
	        pstmt.setString(9, ans);
	        pstmt.setString(10, email);
	      pstmt.setBinaryStream(11, fis,(int)f.length());
	    
	      
	    
	    
	  
	      int i=pstmt.executeUpdate();
	      
	      if(i>0)
	      {	        	  
	    	  pstmt1=con.prepareStatement("INSERT INTO EBAN.ADDRESS (USERID, HOUSENO, STREET, CITY, STATE, COUNTRY, PINCODE,PHNO) VALUES ((SELECT MAX(USERID) FROM EBAN.USERDETAILS),?,?,?,?,?,?,?)");	        	  	        	  
	    	  pstmt1.setString(1, hno);
	    	  pstmt1.setString(2, street);
	    	  pstmt1.setString(3, city);
	    	  pstmt1.setString(4, state);
	    	  pstmt1.setString(5, country);
	    	  pstmt1.setString(6,pin);
	    	  pstmt1.setString(7, phone);
	    	  insert=pstmt1.executeUpdate();
	    	  }	          	          
	      if(insert>0)
	         {
	    	  
	    	  String body="We are Happy to Inform that your Registration is Successfull and you can Enjoy our Services with the following username--> "+loginid+"password--"+pwd;
        	  
        	  postMailTo(email, mailFrom, password, body);
	    	  
	    	  
	    	  
	    	  flag=true;
	          con.commit();
	         }
	         else
	         {
	        	 flag=false;
	        	 con.rollback();
	         }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        flag=false;
	        try 
	        {
	            con.rollback();
	        } 
	        catch (SQLException se) 
	        {
	            se.printStackTrace();
	        }
	    }
	    
	    finally
		{
		 try{
			 if(con!=null)
				 con.close();				 
		 }
		 catch(SQLException e){
			 
			 e.printStackTrace();
		 }
		}
	return flag;
	}

	
	
public boolean postMailTo(String mailTo,String mailFrom,String password,String body){
		
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
			msg.setSubject("Mail From Admission Dept Bank......");
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
	
	
	
	
	public CoreList getEmployeeInfo(String path)
    {
		CoreList aCoreList = new CoreList();
   
    	RegisterFormBean rb=null;
		aCoreList.clear();
		
		String userid="";
		
		
		try {
			
			pstmt = con.prepareStatement("select u.userid,u.firstname,u.lastname,u.nickname,u.dob,u.dor,u.loginid,u.password,u.logintype,u.squestion,u.sanswer,u.photo,u.emailid,a.phno from EBAN.userdetails u,EBAN.address a where u.logintype=? and u.userid=a.userid");
			
			pstmt.setString(1,"employee");
		     rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				
				rb=new RegisterFormBean();
				userid=rs.getString(1);
			
				rb.setUserid(userid);
				rb.setFname(rs.getString(2));
				rb.setLname(rs.getString(3));
				
				rb.setNname(rs.getString(4));
				rb.setDob(rs.getString(5));
				rb.setDoe(rs.getString(6));
				rb.setLogin(rs.getString(7));
				rb.setPassword(rs.getString(8));
				rb.setLogintype(rs.getString(9));
				rb.setSquest(rs.getString(10));
				rb.setSanswer(rs.getString(11));
				
				
				rb.setEmail(rs.getString(13));
			
				Blob b=rs.getBlob(12);
				if(b!=null){
				byte b1[]=b.getBytes(1,(int)b.length());
				OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
				fout.write(b1);
			
				rb.setPhoto(userid+".jpg");
				
				rb.setMobile(rs.getString(14));
				}
				
				
				aCoreList.add(rb);
			}	
    	}
    	catch(SQLException se)
    	{
    		se.printStackTrace();
    		LoggerManager.writeLogWarning(se);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		LoggerManager.writeLogWarning(e);
    	}
    	finally
    	{
    		try
    		{
    			con.close();
    		}
    		catch(SQLException se)
    		{
    			LoggerManager.writeLogWarning(se);
    		}
    	}
    	return aCoreList;
    }

	public CoreList getCustomerInfo(String path1) {
		// TODO Auto-generated method stub
		CoreList aCoreList = new CoreList();
	
	    	
	    	
	    	RegisterFormBean rb=null;
			aCoreList.clear();
			
			String userid="";
			
			
			try {
				
				
				pstmt = con.prepareStatement("select u.userid,u.firstname,u.lastname,u.nickname,u.dob,u.dor,u.loginid,u.password,u.logintype,u.squestion,u.sanswer,u.photo,u.emailid,a.phno,d.accno from EBAN.userdetails u,EBAN.address a,EBAN.accountdetails d where u.logintype=? and u.userid=a.userid and u.userid=d.userid");
				
				
				
				pstmt.setString(1,"customer");
			     rs = pstmt.executeQuery();
				
				
				while(rs.next())
				{
					
					rb=new RegisterFormBean();
					userid=rs.getString(1);
				
					rb.setUserid(userid);
					rb.setFname(rs.getString(2));
					rb.setLname(rs.getString(3));
					
					rb.setNname(rs.getString(4));
					rb.setDob(rs.getString(5));
					rb.setDoe(rs.getString(6));
					rb.setLogin(rs.getString(7));
					rb.setPassword(rs.getString(8));
					rb.setLogintype(rs.getString(9));
					rb.setSquest(rs.getString(10));
					rb.setSanswer(rs.getString(11));
					
					
					rb.setEmail(rs.getString(13));
				
					Blob b=rs.getBlob(12);
					if(b!=null){
					byte b1[]=b.getBytes(1,(int)b.length());
					OutputStream fout=new FileOutputStream(path1+"/"+ userid+".jpg");
					fout.write(b1);
				
					rb.setPhoto(userid+".jpg");
					
					rb.setMobile(rs.getString(14));
					
					rb.setAccno(rs.getInt(15));
					}
					
					
					aCoreList.add(rb);
				}	
	    	}
	    	catch(SQLException se)
	    	{
	    		se.printStackTrace();
	    		LoggerManager.writeLogWarning(se);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    		LoggerManager.writeLogWarning(e);
	    	}
	    	finally
	    	{
	    		try
	    		{
	    			con.close();
	    		}
	    		catch(SQLException se)
	    		{
	    			LoggerManager.writeLogWarning(se);
	    		}
	    	}
	    	return aCoreList;
	}
	}

	 
	
	
