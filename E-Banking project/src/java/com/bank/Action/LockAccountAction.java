package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.AccountDAoImpl;

public class LockAccountAction extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String path="";
        
        boolean flag=false;
        try{
       	 
       	      int accno=Integer.parseInt(request.getParameter("accno"));
		  
		       flag= new AccountDAoImpl().lockAccount(accno);
		 
		   if(flag){
			   request.setAttribute("status", "AccountLocked Successfully");
			  path="./ViewAllAccStatus.jsp";
			 }
		  else{
			  request.setAttribute("status", "Locking Of Account  is Failed");
			  path="./ViewAllAccStatus.jsp";
			  }}
	  catch (Exception e) {
		request.setAttribute("status", "Invalid Entries");
		  path="./ViewAllAccStatus.jsp";
		  e.printStackTrace();
		  }
	RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);
	
	
	
	}

}
