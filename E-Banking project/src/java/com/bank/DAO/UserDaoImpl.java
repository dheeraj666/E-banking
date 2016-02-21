package com.bank.DAO;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.FormBean.RegisterFormBean;
import com.bank.db.ConnectionFactory;
import com.bank.util.CoreList;
import com.bank.util.LoggerManager;





public class UserDaoImpl {

	
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs,rs1;
	Connection con;
	
	
	public UserDaoImpl()
	{
		con=ConnectionFactory.getConnection();
		
	}
	public CoreList getUsers(String path,String login)
    {
    	CoreList aCoreList = new CoreList();
    	
    	String userid="";
    
    	RegisterFormBean rb=null;
		aCoreList.clear();
		Statement st;
		try {
		       
			pstmt = con.prepareStatement("select u.userid,u.logintype,u.firstname,u.lastname,TO_CHAR(u.dob,'DD-MM-YYYY'),u.emailid,u.photo,a.accno from EBAN.userdetails u,EBAN.accountdetails a where u.userid=a.userid and u.loginid=?");
			pstmt.setString(1, login);
			
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				
				rb=new RegisterFormBean();
				userid=rs.getString(1);
			
				rb.setUserid(userid);
				rb.setLogintype(rs.getString(2));
				rb.setFname(rs.getString(3));
				rb.setLname(rs.getString(4));
				rb.setDob(rs.getString(5));
				rb.setEmail(rs.getString(6));
			
				Blob b=rs.getBlob(7);
				System.out.println();
				if(b!=null){
				byte b1[]=b.getBytes(1,(int)b.length());
				OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
				fout.write(b1);
			
				rb.setPhoto(userid+".jpg");
				}
				
				rb.setAccno(rs.getInt(8));
			
				aCoreList.add(rb);
			}	
    	}
    	catch(SQLException se)
    	{
    		LoggerManager.writeLogWarning(se);
    	}
    	catch(Exception e)
    	{
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
		
	
	
	public CoreList getUser(String path,String login)
    {
    	CoreList aCoreList = new CoreList();
    	
    	String userid="";
    
    	RegisterFormBean rb=null;
		aCoreList.clear();
		Statement st;
		try {
		      
		
			pstmt = con.prepareStatement("select u.userid,u.logintype,u.firstname,u.lastname,TO_CHAR(u.dob,'DD-MM-YYYY'),u.emailid,u.photo from EBAN.userdetails u where  u.loginid=?");
			pstmt.setString(1, login);
			
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				
				rb=new RegisterFormBean();
				userid=rs.getString(1);
			
				rb.setUserid(userid);
				rb.setLogintype(rs.getString(2));
				rb.setFname(rs.getString(3));
				rb.setLname(rs.getString(4));
				rb.setDob(rs.getString(5));
				rb.setEmail(rs.getString(6));
			
				Blob b=rs.getBlob(7);
				System.out.println();
				if(b!=null){
				byte b1[]=b.getBytes(1,(int)b.length());
				OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
				fout.write(b1);
			
				rb.setPhoto(userid+".jpg");
				}
				
				
			
				aCoreList.add(rb);
			}	
    	}
    	catch(SQLException se)
    	{
    		LoggerManager.writeLogWarning(se);
    	}
    	catch(Exception e)
    	{
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
	
	public CoreList getProfile(String path,String login)
    {
    	CoreList aCoreList = new CoreList();
    	
    	String userid="";
    
    	RegisterFormBean rb=null;
		aCoreList.clear();
		Statement st;
		try {
		       
			pstmt = con.prepareStatement("select u.userid,u.logintype,u.firstname,u.lastname,TO_CHAR(u.dob,'DD-MM-YYYY'),u.emailid,u.photo,a.houseno,a.street,a.city,a.state,a.country,a.pincode,a.phno from EBAN.userdetails u,EBAN.address a where u.userid=a.userid and u.loginid=?");
			pstmt.setString(1, login);
			
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				
				rb=new RegisterFormBean();
				userid=rs.getString(1);
			
				rb.setUserid(userid);
				rb.setLogintype(rs.getString(2));
				rb.setFname(rs.getString(3));
				rb.setLname(rs.getString(4));
				rb.setDob(rs.getString(5));
				rb.setEmail(rs.getString(6));
			
				Blob b=rs.getBlob(7);
				System.out.println();
				if(b!=null){
				byte b1[]=b.getBytes(1,(int)b.length());
				OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
				fout.write(b1);
			
				rb.setPhoto(userid+".jpg");
				}
				
				rb.setHno(rs.getString(8));
				rb.setStreet(rs.getString(9));
				rb.setCity(rs.getString(10));
				rb.setState(rs.getString(11));
				rb.setCountry(rs.getString(12));
				rb.setPin(rs.getString(13));
				rb.setMobile(rs.getString(14));
			
				aCoreList.add(rb);
			}	
    	}
    	catch(SQLException se)
    	{
    		LoggerManager.writeLogWarning(se);
    	}
    	catch(Exception e)
    	{
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
	
		
		
	
		
	
		
		
		
		
	public boolean deleteCustomer(int accno)
	{
		
		
		int uid=0;

		boolean flag=true;
		try
		{
			
			stmt=con.createStatement();
		    pstmt=con.prepareStatement("select userid from EBAN.ACCOUNTDETAILS where accno=?");
			  
		 pstmt.setInt(1,accno);
			rs=pstmt.executeQuery();
			  
			  if(rs.next())
			  {
				 uid=rs.getInt(1);
				 
				 
			  }
			  
			  
			 
			  
			
							
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
		 catch(Exception e){}
		}
	return flag;
	}
	public CoreList getAllCustomerInfo(String path1) {
		// TODO Auto-generated method stub
		
		
	    {
			CoreList aCoreList = new CoreList();
	   
	    	
	    	
	    	RegisterFormBean rb=null;
			aCoreList.clear();
			
			String userid="";
			
			
			try {
				
				pstmt = con.prepareStatement("select u.userid,u.firstname,u.lastname,u.nickname,u.dob,u.dor,u.loginid,u.password,u.logintype,u.squestion,u.sanswer,u.photo,u.emailid,a.phno from EBAN.userdetails u,EBAN.address a where u.logintype=? and u.userid=a.userid");
				
				pstmt.setString(1,"user");
				
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
	
}
		
	
	
	
