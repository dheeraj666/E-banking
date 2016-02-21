package com.bank.DAO;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import com.bank.FormBean.AccountFormBean;
import com.bank.FormBean.LoanRequestFormBean;


public class LoanRequestDao {

	
	private static final Statement ConnectionFactory = null;
	PreparedStatement pstmt,pstmt1,pstmt3,pstmt4,pstmt5;
	Statement stmt;
	ResultSet rs,rs1;
	Connection con;
	
	
	public LoanRequestDao() throws SQLException {
		con=com.bank.db.ConnectionFactory.getConnection();
	
	}
	
	
	
	public boolean LoanRequesrDetails(LoanRequestFormBean formBean,String loginid )
	{
		boolean flag=false;
 	
    	int userid=0;
    	try{	    		
    		
    		con.setAutoCommit(false);
	    	
	    	
	    	
	    	
	    	int LoanId =formBean.getLoanid();
	    	
	    	String description=formBean.getDescription();
	    	
	    	
	    	pstmt3=con.prepareStatement("select userid from EBAN.userdetails where loginid=?");
    		
    		pstmt3.setString(1, loginid);
            rs=pstmt3.executeQuery();
            if(rs.next())
           
            	userid=rs.getInt(1);
	    	
	    	
          
    		pstmt=con.prepareStatement("INSERT INTO EBAN.LOANREQUEST(USERID,LOANID,DESCRIPTION,RDATE,STATUS) VALUES(?,?,?,(select current date from SYSIBM.SYSDUMMY1),?)");

    		
    		
    		pstmt.setInt(1,userid );
    		
    		pstmt.setInt(2,LoanId );
    		
    		pstmt.setString(3,description);
    		
    		pstmt.setString(4,"pending");
    		
    		int i=pstmt.executeUpdate();
          
          if(i>0)
               
	         {
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
	
	
	public boolean deleteLoan(int rid){
		
		
		
		boolean flag=false;
    	    	
    	try{	    		
    		
    		con.setAutoCommit(false);
	    
	 
            pstmt=con.prepareStatement("delete from EBAN.LoanRequest where rid=?");	            		    		
    		
            pstmt.setInt(1, rid);
    		
    		
    		int i=pstmt.executeUpdate();
          
          if(i>0)
               
	         {
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
	
	public Vector<LoanRequestFormBean>  getAllLoanRequest()
	{
		Vector<LoanRequestFormBean> vfb=null;
		
		try{
			
			
			pstmt=con.prepareStatement("select *from EBAN.LOANREQUEST ");
			
		
			
			
			rs=pstmt.executeQuery();
			
			vfb=new Vector<LoanRequestFormBean>();
			
			while(rs.next())
			{
			
				int Rid=rs.getInt(1);
				String Description=rs.getString(2);
				String Rdate=rs.getString(3);
				String Status=rs.getString(4);
				int Loanid=rs.getInt(5);
				int Userid=rs.getInt(6);
				
				
			LoanRequestFormBean fb=new LoanRequestFormBean();
								
				
				fb.setRid(Rid);
				fb.setUserid(Userid);
				fb.setLoanid(Loanid);
				fb.setDescription(Description);
				fb.setRdate(Rdate);
				
				fb.setStatus(Status);
				
				
				
			
			vfb.add(fb);
			
			
			}
			
			
			
			
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		return vfb;
		
	}



	public boolean cancel(int loanid) {
		
		return false;
	}



	public boolean accept(int rid) {

		boolean flag=false;
		try
		{
		
		pstmt4=con.prepareStatement("update EBAN.LOANREQUEST set STATUS=? where rid=?");
		
		pstmt4.setString(1, "accept");
		
		pstmt4.setInt(2,rid);
		
		int i=pstmt4.executeUpdate();
		if(i>0)
		{
		flag=true;
		con.commit();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
		
	}
	

	public boolean reject(int rid) {
		
		boolean flag=false;
		try
		{
		
		pstmt4=con.prepareStatement("update EBAN.LOANREQUEST set STATUS=? where rid=?");
		
		pstmt4.setString(1, "reject");
		
		pstmt4.setInt(2,rid);
		
		int i=pstmt4.executeUpdate();
		if(i>0)
		{
		flag=true;
		con.commit();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
		
	}




	public Vector<LoanRequestFormBean> loanStatus(String login) {

		
		Vector<LoanRequestFormBean> vab=new Vector<LoanRequestFormBean>();
		
		
		
		int userid=0;
		try
		{
		

			
			pstmt3=con.prepareStatement("select userid from EBAN.userdetails where loginid=?");
    		
    		pstmt3.setString(1, login);
    		
            rs=pstmt3.executeQuery();
            
            if(rs.next())
           
            	userid=rs.getInt(1);
          
            
            
	    	
			pstmt5=con.prepareStatement("select * from EBAN.LOANREQUEST where userid=?");
			
			
			
			pstmt5.setInt(1,userid);
			
		    rs= pstmt5.executeQuery();
		    
		    while(rs.next())
		    {
		    
		    	
		    	LoanRequestFormBean lfb=new LoanRequestFormBean();
		    	
		    	lfb.setStatus(rs.getString(4));
		   
		    	lfb.setLoanid(rs.getInt(5));
	
		    	lfb.setRid(rs.getInt(1));
		    	
		    	vab.add(lfb);
		    }
		    
		    
		           
		}
	    	
    		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return vab;
		
		
		
		
	}



}
	
	
	
	
	
	
	
	
	
	
	
	