package com.bank.DAO;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.bank.FormBean.LoansFormBean;

public class LoansDao {

	
	private static final Statement ConnectionFactory = null;
	PreparedStatement pstmt,pstmt1;
	Statement stmt;
	ResultSet rs,rs1;
	Connection con;
	
	
	public LoansDao() throws SQLException {
		con=com.bank.db.ConnectionFactory.getConnection();
	
	}
	
	
	
	public boolean LoanDetails(LoansFormBean formBean )
	{
		boolean flag=false;
    	int insert=0;	    	
    	try{	    		
    		
    		con.setAutoCommit(false);
	
	    	
	    	
	    	int LoanId =formBean.getLoanid();
	    	String loanname=formBean.getLoanname();
	    	String ldescription=formBean.getLoandescription();
	    	Double interest = formBean.getInterest();
	    	String instalement=formBean.getInstalement();
	    	String ldate=formBean.getLoandate();
	    	String status=formBean.getStatus();
	    	
	    	
	    	
	    	
            pstmt=con.prepareStatement("INSERT INTO EBAN.Loans(loanname,loandescription,interest,instalement,loandate,status) values (?,?,?,?,(select current date from sysibm.sysdummy1),?)");	            		    		
    		System.out.println();
    		
    		pstmt.setString(1, loanname);
    		pstmt.setString(2, ldescription);
    		pstmt.setDouble(3, interest);
    		pstmt.setString(4, instalement);
    		pstmt.setString(5, "active");
    		
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
	
	
	
	public boolean deleteLoan(int loanid){
		
		
		
		boolean flag=false;
    	int insert=0;	    	
    	try{	    		
    		
    		con.setAutoCommit(false);
	    	
            pstmt=con.prepareStatement("delete from EBAN.Loans where loanid=?");	            		    		
    		
            pstmt.setInt(1, loanid);
    		
    		
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
	public boolean updateLoans(LoansFormBean formBean ) throws FileNotFoundException
	{
		boolean flag=false;
    	int insert=0;	    	
    	try{	    		
    		
    		con.setAutoCommit(false);
	    
	    	int LoanId =formBean.getLoanid();
	    	String loanname=formBean.getLoanname();
	    	String ldescription=formBean.getLoandescription();
	    	Double interest = formBean.getInterest();
	    	String instalement=formBean.getInstalement();
	    	String ldate=formBean.getLoandate();
	    	String status=formBean.getStatus();
	    	

            pstmt=con.prepareStatement("UPDATE EBAN.Loans set loanname=?,ldescription=?,interest=?,instalement=?,status=?)where loanid=?");	            		    		
    		System.out.println();
    		pstmt.setInt(1, LoanId);
    		pstmt.setString(2, loanname);
    		pstmt.setString(3, ldescription);
    		pstmt.setDouble(4, interest);
    		pstmt.setString(5, instalement);
    		pstmt.setString(6, "active");
    		
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
	
	
	public Vector<LoansFormBean>  getAllLoans()
	{
		Vector<LoansFormBean> vfb=null;
		
		try{
			
			
			pstmt=con.prepareStatement("select *from EBAN.Loans");
			
		
			
			
			rs=pstmt.executeQuery();
			
			vfb=new Vector<LoansFormBean>();
			while(rs.next())
			{
			
				int Loanid=rs.getInt(1);
				String Loanname=rs.getString(2);
				String Loandescription=rs.getString(3);
				Double Interest=rs.getDouble(4);
				String instalement=rs.getString(5);
				String ldate=rs.getString(6);
				String status=rs.getString(7);
				
			
				
				
				
				LoansFormBean fb=new LoansFormBean();
								
				
				fb.setLoanid(Loanid);
				fb.setLoanname(Loanname);
				fb.setLoandescription(Loandescription);
				fb.setInterest(Interest);
				fb.setInstalement(instalement);
				fb.setStatus(status);
				fb.setLoandate(ldate);
				
				
				
			
			vfb.add(fb);
			
			
			}
			
			
			
			
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		
		return vfb;
		
	}



	public boolean cancel(int loanid) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public Vector<LoansFormBean>  getLoanNames()
	{
		Vector<LoansFormBean> vfb=null;
		
		try{
			
			
			pstmt=con.prepareStatement("select loanid,loanname from EBAN.Loans ");
		
			rs=pstmt.executeQuery();
			
			vfb=new Vector<LoansFormBean>();
			while(rs.next())
			{
			
				int Loanid=rs.getInt(1);
				String Loanname=rs.getString(2);
				
			
				
				
				
				LoansFormBean fb=new LoansFormBean();
								
				
				fb.setLoanid(Loanid);
				fb.setLoanname(Loanname);
				
				
			
			vfb.add(fb);
			
			
			}
			
			
			
			
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		return vfb;
		
	}

	

}
	
	
	
	
	
	
	
	
	
	
	
	