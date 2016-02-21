package com.bank.DAO;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;


import com.bank.FormBean.AccountFormBean;

import com.bank.db.ConnectionFactory;
import com.bank.util.CoreHash;

import com.bank.util.DateWrapper;
import com.bank.util.LoggerManager;






public class AccountDAoImpl {
	
	
	PreparedStatement pstmt,pstmt1,pstmt2,pstmt4;
	Statement stmt,stmt1;
	ResultSet rs,rs1;
	Connection con;
	
	public AccountDAoImpl(){
		
		
		con=ConnectionFactory.getConnection();
		
		
	}
	
	public Vector<AccountFormBean> getCustomer(AccountFormBean ab){
		
		
		Vector<AccountFormBean> vab=null;
		String userid="";
		
		String path="";
		
		try{
			
			path=ab.getPath();
			
			
			pstmt = con.prepareStatement("select u.userid,u.firstname,u.lastname,TO_CHAR(u.dob,'DD-MM-YYYY'),u.emailid,u.photo,a.accno,a.accbal,a.accopendate,ad.houseno,ad.street,ad.city,ad.country,ad.pincode,ad.phno,ad.state,u.logintype from EBAN.userdetails u,EBAN.accountdetails a ,EBAN.address ad where u.userid=a.userid and a.userid=ad.userid and a.accno=?");
			
			
			
            pstmt.setInt(1, ab.getAccno());
			
			rs=pstmt.executeQuery();
			
			
			vab=new Vector<AccountFormBean>();
			
			while(rs.next())
			{
				
				AccountFormBean	rb=new AccountFormBean();
				  userid=rs.getString(1);
				  
				  
			
				rb.setUserid(userid);
				
				rb.setFname(rs.getString(2));
				rb.setLname(rs.getString(3));
				rb.setDob(rs.getString(4));
				rb.setEmail(rs.getString(5));
			
				Blob b=rs.getBlob(6);
				System.out.println();
				if(b!=null){
				byte b1[]=b.getBytes(1,(int)b.length());
				OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
				fout.write(b1);
			
				rb.setPhoto(userid+".jpg");
				}
				
				rb.setAccno(rs.getInt(7));
				rb.setAccbal(rs.getDouble(8));
				rb.setAdate(rs.getString(9));
				rb.setHno(rs.getString(10));
			    rb.setStreet(rs.getString(11));
			    rb.setCity(rs.getString(12));
			    rb.setCountry(rs.getString(13));
			    rb.setPin(rs.getString(14));
			    rb.setMobile(rs.getString(15));
			    rb.setState(rs.getString(16));
			    rb.setLogintype(rs.getString(17));
		         
				vab.add(rb);
			
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return vab;
		
	
		
	}
	
	
public Vector<AccountFormBean> getCustomerByName(String fname,String lname,String path){
		
		
		Vector<AccountFormBean> vab=null;
		String userid="";
		
		
		
		try{
			
			
			
			pstmt = con.prepareStatement("select u.userid,u.firstname,u.lastname,TO_CHAR(u.dob,'DD-MM-YYYY'),u.emailid,u.photo,a.accno,a.accbal,a.accopendate,ad.houseno,ad.street,ad.city,ad.country,ad.pincode,ad.phno,ad.state from EBAN.userdetails u,EBAN.accountdetails a ,EBAN.address ad where u.userid=a.userid  and u.firstname=? and lastname=?");
			
			
			
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
           
			
			rs=pstmt.executeQuery();
			
			
			vab=new Vector<AccountFormBean>();
			AccountFormBean	rb=null;
			while(rs.next())
			{
				
					rb=new AccountFormBean();
				  userid=rs.getString(1);
				  
				  
			
				rb.setUserid(userid);
				
				rb.setFname(rs.getString(2));
				rb.setLname(rs.getString(3));
				rb.setDob(rs.getString(4));
				rb.setEmail(rs.getString(5));
			
				Blob b=rs.getBlob(6);
				System.out.println();
				if(b!=null){
				byte b1[]=b.getBytes(1,(int)b.length());
				OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
				fout.write(b1);
			
				rb.setPhoto(userid+".jpg");
				}
				
				rb.setAccno(rs.getInt(7));
				rb.setAccbal(rs.getDouble(8));
				rb.setAdate(rs.getString(9));
				rb.setHno(rs.getString(10));
			    rb.setStreet(rs.getString(11));
			    rb.setCity(rs.getString(12));
			    rb.setCountry(rs.getString(13));
			    rb.setPin(rs.getString(14));
			    rb.setMobile(rs.getString(15));
			    rb.setState(rs.getString(16));
			    
		         
				
			
			
			}
			vab.add(rb);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return vab;
		
	
		
	}
	

public Vector<AccountFormBean> getCustomerByPhone(String phone,String path){
	
	
	Vector<AccountFormBean> vab=null;
	String userid="";
	
	
	
	try{
		
		
		
		pstmt = con.prepareStatement("select u.userid,u.firstname,u.lastname,TO_CHAR(u.dob,'DD-MM-YYYY'),u.emailid,u.photo,a.accno,a.accbal,a.accopendate,ad.houseno,ad.street,ad.city,ad.country,ad.pincode,ad.phno,ad.state from EBAN.userdetails u,EBAN.accountdetails a ,EBAN.address ad where u.userid=a.userid and a.userid=ad.userid and ad.phno=?");
		
		
		
        pstmt.setString(1, phone);
      
		
		rs=pstmt.executeQuery();
		
		
		vab=new Vector<AccountFormBean>();
		
		while(rs.next())
		{
			
			AccountFormBean	rb=new AccountFormBean();
			  userid=rs.getString(1);
			  
			  
		
			rb.setUserid(userid);
			
			rb.setFname(rs.getString(2));
			rb.setLname(rs.getString(3));
			rb.setDob(rs.getString(4));
			rb.setEmail(rs.getString(5));
		
			Blob b=rs.getBlob(6);
			System.out.println();
			if(b!=null){
			byte b1[]=b.getBytes(1,(int)b.length());
			OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
			fout.write(b1);
		
			rb.setPhoto(userid+".jpg");
			}
			
			rb.setAccno(rs.getInt(7));
			rb.setAccbal(rs.getDouble(8));
			rb.setAdate(rs.getString(9));
			rb.setHno(rs.getString(10));
		    rb.setStreet(rs.getString(11));
		    rb.setCity(rs.getString(12));
		    rb.setCountry(rs.getString(13));
		    rb.setPin(rs.getString(14));
		    rb.setMobile(rs.getString(15));
		    rb.setState(rs.getString(16));
		    
	         
			vab.add(rb);
		
		
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return vab;
	

	
}

	public Vector<AccountFormBean> getAccount(String login)
	{
		
		Vector<AccountFormBean> vtb=null;
		int uid=0;
		try{
			
			
			
			pstmt=con.prepareStatement("select u.userid,u.firstname,u.lastname,u.emailid ,a.accno,a.acctype,a.accbal,a.tdate from EBAN.userdetails u,EBAN.accountdetails a where u.userid=a.userid and a.userid=?");
		 	
	       stmt=con.createStatement();
			
			rs=stmt.executeQuery("select userid from EBAN.userdetails where loginid="+"'"+login+"'");
			
		
			if(rs.next())
		     uid=rs.getInt(1);
			
			
			pstmt.setInt(1, uid);
			
			rs=pstmt.executeQuery();
			vtb=new Vector<AccountFormBean>();
			
			while(rs.next())
			{
				String uid1=rs.getString(1);
				String fname=rs.getString(2);
				String lname=rs.getString(3);
				String email=rs.getString(4);
				int accno=rs.getInt(5);
				String acctype=rs.getString(6);
				double accbal=rs.getShort(7);
				String date=rs.getString(8);
				
				
				AccountFormBean tfb=new AccountFormBean();
				
				
				tfb.setAccno(accno);
				tfb.setAcctype(acctype);
				tfb.setEmail(email);
				tfb.setFname(fname);
				tfb.setLname(lname);
				tfb.setAccbal(accbal);
				tfb.setTdate(date);
				tfb.setUserid(uid1);
			
				vtb.add(tfb);
				}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
	
		return vtb;
		
		
		
	}
	

	public boolean insertTransaction(AccountFormBean tfb)
	{
		boolean flag=true;
		
		int update=0;
		int accbal=0;
		
		try{
			
		
			pstmt=con.prepareStatement("insert into EBAN.transaction(accno,transtype,transamount,transdate,acctype) values(?,?,?,(select current date from sysibm.sysdummy1),?)");
			
			String  ttype=tfb.getTtype();

			int accno=tfb.getAccno();
			double amount=tfb.getTamount();
			String date=tfb.getTdate();
			pstmt.setInt(1, accno);
			pstmt.setString(2, ttype);
			pstmt.setDouble(3, amount);
		    pstmt.setString(4, tfb.getAcctype());
			
			
			int insert=pstmt.executeUpdate();
			
			
			stmt=con.createStatement();
			rs=stmt.executeQuery("select accbal from EBAN.accountdetails where accno="+accno);
			if(rs.next())
			 accbal=rs.getInt(1);
			

			
			if((insert>0)&&(ttype.equalsIgnoreCase("deposit")))
			{
				
			

			pstmt=con.prepareStatement("update EBAN.accountdetails set accbal=accbal+?,tdate=(select current date from sysibm.sysdummy1) where accno=?");
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, accno);
			
			
			 update=pstmt.executeUpdate(); 
			 
			
			}
			
			
		
			
		
			else if((accbal>amount)&&(insert>0 )&&(ttype.equalsIgnoreCase("withdraw")))
			{
				
				
				pstmt=con.prepareStatement("update EBAN.accountdetails set accbal=accbal-?,tdate=(select current date from sysibm.sysdummy1) where accno=?");
				pstmt.setDouble(1, amount);
		
				pstmt.setInt(2, accno);
				
				 update=pstmt.executeUpdate();
			
				
			}
		 else{
			 
			
			 flag=false;
		 }
			
			
			
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
	
	public Vector<AccountFormBean> getAllAccounts()
	{
		
		Vector<AccountFormBean> vtb=null;
		
		try{
			
			
			
			
			pstmt=con.prepareStatement("select u.userid,u.firstname,u.lastname,u.emailid ,a.accno,a.acctype,a.accbal,TO_CHAR(a.tdate,'DD-MM-YY'),a.accstatus from EBAN.userdetails u,EBAN.accountdetails a where u.userid=a.userid and u.logintype='customer'");
		 	
	       stmt=con.createStatement();
			rs=pstmt.executeQuery();
			vtb=new Vector<AccountFormBean>();
			
			while(rs.next())
			{
				String uid1=rs.getString(1);
				String fname=rs.getString(2);
				String lname=rs.getString(3);
				String email=rs.getString(4);
			        int accno=rs.getInt(5);
				String acctype=rs.getString(6);
				double accbal=rs.getDouble(7);
				String date=rs.getString(8);
				String status=rs.getString(9);
				
				
				
				AccountFormBean tfb=new AccountFormBean();
				
				
				tfb.setAccno(accno);
				tfb.setAcctype(acctype);
				tfb.setEmail(email);
				tfb.setFname(fname);
				tfb.setLname(lname);
				tfb.setTamount(accbal);
				tfb.setTdate(date);
				tfb.setUserid(uid1);
				tfb.setAstatus(status);
				tfb.setAccbal(accbal);
				
				vtb.add(tfb);
				
			}
			
			
			
		}
		catch (Exception e) {
			
			
			e.printStackTrace();
		}
	
		return vtb;
		
		
		
	}
	
	
	public boolean lockAccount(int accno)
	{
		
		boolean flag=false;
		try {
			
			
			 
			 
            	 pstmt=con.prepareStatement("update EBAN.AccountDetails set accstatus=? where accno=?");
       
            String status="locked";
            
            pstmt.setString(1, status);
            pstmt.setInt(2, accno);
            int i=pstmt.executeUpdate();
                  
            
            if(i>0)
                    {
                        flag=true;
              
                    }
                    else
                    	flag=false;
                    
        
		}
		catch(Exception e)
		{e.printStackTrace();
			LoggerManager.writeLogWarning(e);
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
	
	
	
	public boolean unLockAccount(int accno)
	{
		
		boolean flag=false;
		try {
			 
			   
	
            	 pstmt=con.prepareStatement("update EBAN.AccountDetails set accstatus=? where accno=?");
       
            String status="active";
            
            pstmt.setString(1, status);
            pstmt.setInt(2, accno);
            int i=pstmt.executeUpdate();
                  
            
            if(i>0)
                    {
                        flag=true;
               
                    }
                    else
                    	flag=false;
                    
        
		}
		catch(Exception e)
		{e.printStackTrace();
			LoggerManager.writeLogWarning(e);
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
	
	
	
public Vector<AccountFormBean> getAccno(String loginuser){
		
		
		
		int uid=0;
		int acno=0;
		Vector<AccountFormBean> vfb=null;
		
		try{
		  
		pstmt1=con.prepareStatement("select userid from EBAN.userdetails where loginid=?");
		
		pstmt1.setString(1, loginuser);
		
		rs=pstmt1.executeQuery();
		if(rs.next()){
			
			uid=rs.getInt(1);
			
		}
			
		 pstmt=con.prepareStatement("select accno,acctype,accbal,accopendate from EBAN.accountdetails where userid=? and accstatus=?");
		 
		 pstmt.setInt(1, uid);
		 pstmt.setString(2, "active");
		 rs=pstmt.executeQuery();
		    
		 vfb=new Vector<AccountFormBean>();
		 
		 while(rs.next()){
			 
			  acno=rs.getInt(1);
			  
			 
			 AccountFormBean tb=new AccountFormBean();
			 
			 tb.setAccno(acno);
			 tb.setAcctype(rs.getString(2));
			 tb.setAccbal(rs.getDouble(3));
			 tb.setTdate(rs.getString(4));
			 
			 vfb.add(tb);
			 
			 
			}
		
	}
		catch (SQLException e) {
		e.printStackTrace();
	}
		
	return vfb;
	
	}



public boolean transforAmount(AccountFormBean ab){
	
	
	boolean flag=false;
	double accbal=0.0;
	double fromaccbal=0.0;
	int update=0;
	try{
		int accno=ab.getAccto();
		int accfrom=ab.getAccfrom();
	
		double tamt=ab.getTamount();

		String acctype="";
		
		
		 stmt1=con.createStatement();
		rs=stmt1.executeQuery("select accbal from EBAN.accountdetails where accno="+accfrom);
		if(rs.next()){
			fromaccbal=rs.getInt(1);
		

		
		
		
		if(fromaccbal>tamt){
		
		
		
		pstmt=con.prepareStatement("insert into EBAN.TRANSFORAMOUNT(ACCFROM,ACCTO,TRANSAMOUNT,TRANSDATE) values(?,?,?,(select current date from sysibm.sysdummy1))");
		
		pstmt.setInt(1, ab.getAccfrom());
		pstmt.setInt(2, ab.getAccto());
		pstmt.setDouble(3, tamt);
		
		
		int i=pstmt.executeUpdate();
		
		int j=0;
		
		int modify=0;
		
		if(i>0){
			
			pstmt4=con.prepareStatement("insert into EBAN.transaction(ACCNO,TRANSTYPE,TRANSAMOUNT,TRANSDATE,ACCTYPE) values (?,?,?,(select current date from sysibm.sysdummy1),?)");
			
			pstmt4.setInt(1, accfrom);
			pstmt4.setString(2, "Amount Transfored");
			pstmt4.setDouble(3, tamt);
			pstmt4.setString(4, acctype);
			
			 modify=pstmt4.executeUpdate();
			
			
			
		}
		
		
		if(modify>0){
			
			stmt1=con.createStatement();
			
			rs1=stmt1.executeQuery("select accbal from EBAN.accountdetails where accno="+accfrom);
			while(rs1.next()){
				 accbal=rs1.getInt(1);
				
				
				}
			
			pstmt2=con.prepareStatement("update EBAN.accountdetails set accbal=? where accno=?");
			
			double fromamount=accbal-tamt;
			pstmt2.setDouble(1, fromamount);
			pstmt2.setInt(2, accfrom);
			
			
			
			j=pstmt2.executeUpdate();
		}
		
		
		if(j>0)
        {
			stmt=con.createStatement();
			rs=stmt.executeQuery("select accbal from EBAN.accountdetails where accno="+accno);
			while(rs.next()){
			 accbal=rs.getInt(1);
			
			
			}
			
			pstmt1=con.prepareStatement("update EBAN.accountdetails set accbal=? where accno=?");
			
			double total=accbal+tamt;
			
			pstmt1.setDouble(1, total);
			pstmt1.setInt(2, accno);
			
			update=pstmt1.executeUpdate(); 
			 

        
        }
		
		
			
			 if(update>0)
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
		else{
			 
	
			 flag=false;
		 }
		}
	}
catch(Exception e)
{
	e.printStackTrace();
   LoggerManager.writeLogWarning(e);
}
finally
{
try{
 if(con!=null)
	 con.close();
 
}
catch(Exception e){
	
	e.printStackTrace();
}
}
return flag;
	

	
}

public AccountFormBean getAccDetails(int accno,String loginid)
{
Statement st;
AccountFormBean accDetails=null;
try {
		     
		   	 st=con.createStatement();
        	 ResultSet rs=st.executeQuery("select accno,acctype,balance,tdate from EBAN.AccountDetails where accno="+accno+" and loginid='"+loginid+"'");
        	while(rs.next())
             {
          	   accDetails=new AccountFormBean();
          	   accDetails.setAccno(rs.getInt(1));
          	   accDetails.setAcctype(rs.getString(2));
          	   accDetails.setAccbal(rs.getDouble(3));
          	 String date=DateWrapper.parseDate(rs.getDate(4)).trim();
        	   accDetails.setTdate(date);
        	   
          	 
			
             }
	}
	catch(Exception e)
	{e.printStackTrace();
		LoggerManager.writeLogWarning(e);
	}
	finally
	{
	 try{
		 if(con!=null)
			 con.close();
		 
	 }
	 catch(Exception e){}
	}
	return accDetails;
}

public CoreHash getAccountReport(String sdate,String edate,int accno)
{
	
	Properties p=ConnectionFactory.getProperties();
	String pattern="";
	if(p.getProperty("dbname").equals("access"))
		pattern="#";
	CoreHash aCoreHash = new CoreHash();
	aCoreHash.clear();
	
	int sno=1;
	
	
	
	AccountFormBean aTrans=null;
	try {
		
		stmt=con.createStatement();
        	  rs=stmt.executeQuery("select accno,transtype,transamount,TO_CHAR(Transdate,'yyyy-MON-dd') from EBAN.transaction where Transdate>='"+pattern+sdate+pattern+"' and Transdate<='"+pattern+edate+pattern+"' and accNo="+accno);
        	
        	  while(rs.next())
             {
          	   aTrans=new AccountFormBean();
          	   
          	 aTrans.setAccno(rs.getInt(1));
          	   aTrans.setTtype(rs.getString(2)); 
          	 
          	   aTrans.setTamount(rs.getDouble(3));
          	 String date=rs.getString(4);
           	aTrans.setTdate(date);
         	 
          	 aCoreHash.put(new Integer(sno),aTrans);
			    sno++;
		  
             }
	}
	catch(Exception e)
	{e.printStackTrace();
		LoggerManager.writeLogWarning(e);
	}
	finally
	{
	 try{
		 if(con!=null)
			 con.close();
		 
	 }
	 catch(Exception e){}
	}
	return aCoreHash;
}


public CoreHash getReport(String accno,String acctype)
{
	
	

	
	CoreHash aCoreHash = new CoreHash();
	aCoreHash.clear();
	System.out.println("aCoreHash--"+aCoreHash.isEmpty());
	int sno=1;
	
	AccountFormBean aTrans=null;
	try {
		
		con=ConnectionFactory.getConnection();
		   
		
        	 pstmt=con.prepareStatement("select accno,Transtype,Transamount,TransDate from EBAN.transaction where accno=? and acctype=? ");
        	 pstmt.setString(1, accno);
        	 pstmt.setString(2, acctype);
        	 
        	 ResultSet rs=pstmt.executeQuery();
        	 while(rs.next())
             {
          	   aTrans=new AccountFormBean();
          	   
          	   aTrans.setAccno(rs.getInt(1));
          	   aTrans.setAcctype(rs.getString(2));  
          	   aTrans.setTamount(rs.getInt(3));
          	 String date=DateWrapper.parseDate(rs.getDate(4)).trim();
          	aTrans.setTdate(date);
        	   
          	 aCoreHash.put(new Integer(sno),aTrans);
			    sno++;
		  
             }
	}
	catch(Exception e)
	{e.printStackTrace();
		LoggerManager.writeLogWarning(e);
	}
	finally
	{
	 try{
		 if(con!=null)
			 con.close();
		 
	 }
	 catch(Exception e){}
	}
	return aCoreHash;
}

public CoreHash getUserReport(String path,String login)
{
	
	

	
	CoreHash aCoreHash = new CoreHash();
	aCoreHash.clear();
	
	int sno=1;
	
	AccountFormBean aTrans=null;
	try {
		
		con=ConnectionFactory.getConnection();
		   
		
        	 pstmt=con.prepareStatement("select t.accno,t.Transtype,t.Transamount,t.TransDate,u.userid,u.nickname,u.photo,t.acctype  from EBAN.transaction t,EBAN.userdetails u ,EBAN.accountdetails a where t.accno=a.accno and a.userid=u.userid and u.loginid=?");
        	 pstmt.setString(1, login);
        	 
        	 
        	 ResultSet rs=pstmt.executeQuery();
        	 while(rs.next())
             {
          	   aTrans=new AccountFormBean();
          	   
          	   aTrans.setAccno(rs.getInt(1));
          	   aTrans.setTtype(rs.getString(2));  
          	   aTrans.setTamount(rs.getInt(3));
          	 String date=DateWrapper.parseDate(rs.getDate(4)).trim();
          	aTrans.setTdate(date);
          	
          	int userid=rs.getInt(5);
          	aTrans.setNname(rs.getString(6));
          	
          	Blob b=rs.getBlob(7);
			System.out.println();
			if(b!=null){
			byte b1[]=b.getBytes(1,(int)b.length());
			OutputStream fout=new FileOutputStream(path+"/"+ userid+".jpg");
			fout.write(b1);
		
			aTrans.setPhoto(userid+".jpg");
			}
        	   
			
			aTrans.setAcctype(rs.getString(8));
          	 aCoreHash.put(new Integer(sno),aTrans);
			    sno++;
		  
             }
	}
	catch(Exception e)
	{e.printStackTrace();
		LoggerManager.writeLogWarning(e);
	}
	finally
	{
	 try{
		 if(con!=null)
			 con.close();
		 
	 }
	 catch(Exception e){}
	}
	return aCoreHash;
}

}
