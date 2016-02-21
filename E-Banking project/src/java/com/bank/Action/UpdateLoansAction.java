package com.bank.Action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.bank.DAO.LoansDao;
import com.bank.FormBean.LoansFormBean;

public class UpdateLoansAction extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd=null;

		boolean flag=false;
		String path="";
		
		LoansFormBean rto=new LoansFormBean();
		
		
		Map map=request.getParameterMap();
	 try {
		BeanUtils.populate(rto, map);
		
		
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
		
		try{
			
			HttpSession session=request.getSession();
      	  
        	int loanid=Integer.parseInt((String)session.getAttribute("loginuser"));
        	    
			rto.setLoanid(loanid);
		    
        	   flag=new LoansDao().updateLoans(rto);
			    
			
			if(flag){
				
				
				path="./Loans.jsp";
				
				request.setAttribute("status", "Updation of u r Loans is successfull");
				}
			else{
				
               path="./Loans.jsp";
				
				request.setAttribute("status", "Updation of u r loans is Failed");
				}
			}catch (Exception e) {
			e.printStackTrace();
			
			path="./Loans.jsp";
			
			request.setAttribute("status", "Invalid enteries");
		}
		rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	
		
	}

}
