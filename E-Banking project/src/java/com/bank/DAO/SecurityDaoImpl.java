package com.bank.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.FormBean.LoginForm;
import com.bank.FormBean.RegisterFormBean;
import com.bank.db.ConnectionFactory;


public class SecurityDaoImpl {

	Connection con;
	PreparedStatement pstmt,pstmt1;
	Statement stmt;
	ResultSet rs,rs1;

	 public String loginCheck(LoginForm lf)
	 {
		 
		 String ltype="";
		 
		 try{
		 con=ConnectionFactory.getConnection();
		 
		 
		 pstmt=con.prepareStatement("select logintype,userid  from EBAN.userdetails where loginid=? and password=?");
		 
		 String uname=lf.getUsername();
		 
		 String pwd=lf.getPassword();
	
		 
		 pstmt.setString(1, uname);
		 pstmt.setString(2, pwd);
		 
		 rs=pstmt.executeQuery();
		 
		 
		 if(rs.next())
		 {
			 ltype=rs.getString(1);
			 int userid=rs.getInt(2);
			 
			
			 
			
		 }
			 
	 }
		 catch (SQLException e) {
			 
			 e.printStackTrace();
		
		}
		 return ltype;
			
	
	 }

	 //Method for login audit
	    public void loginaudit(String loginid)
	    {
	        try 
	        { 
	        	
	        	 con=ConnectionFactory.getConnection();
	        	
	            CallableStatement cstmt=con.prepareCall("{call signoutprocedure(?)}");
	            cstmt.setString(1,loginid);
	            
	            System.out.println("in loginaudit");
	            
	              cstmt.execute();
	           
	            con.close();
	        }
	        catch(Exception e)
	        {
	             
	            e.printStackTrace();
	            }
	    }
	    
	    public String passwordRecovery(RegisterFormBean rto){
	    	String password="";
	        try{
	        	con=ConnectionFactory.getConnection();
	        	
	        	 String question=rto.getSquest();
				 String ans=rto.getSanswer();
		
				 String loginid=rto.getUsername();
				
				 
				pstmt=con.prepareStatement("select password from EBAN.userdetails where loginid=? and squestion=? and sanswer=?");
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
	
	
}
