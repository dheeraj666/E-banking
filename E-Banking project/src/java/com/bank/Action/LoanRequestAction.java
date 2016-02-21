package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.DAO.LoanRequestDao;
import com.bank.DAO.LoansDao;
import com.bank.DAO.RegisterDaoImpl;
import com.bank.FormBean.LoanRequestFormBean;
import com.bank.FormBean.LoansFormBean;
import com.bank.util.CoreList;

public class LoanRequestAction extends HttpServlet {

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
		
		doPost(request,response);

		
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

		  try{
			  
		/*	  int userid =Integer.parseInt(request.getParameter("userid"));
			  System.out.println("userid in action========="+userid);*/
			  int loanid =Integer.parseInt(request.getParameter("loanid"));
			  System.out.println("loanid in action========="+loanid);
			  String description= request.getParameter("description");
			  System.out.println("description in action========="+description);
			  
			  String rdate=request.getParameter("rdate");
			  System.out.println("rdate in action========="+rdate);
			  String Status= request.getParameter("Status");
			  System.out.println("Status in action========="+Status);
			  HttpSession hs=request.getSession();
	    	  
		      	String login=(String)hs.getAttribute("username");
		      	System.out.println("in GetUser Action login is........................>"+login);
		      	
			  
			  LoanRequestFormBean formBean = new LoanRequestFormBean();
			 // formBean.setUserid(userid);
			  formBean.setLoanid(loanid);
			  formBean.setDescription(description);
			 
			  formBean.setRdate(rdate);
			  formBean.setStatus(Status);
			  
			  
			  LoanRequestDao LoanRequestDao=new LoanRequestDao();
			 boolean flag= LoanRequestDao.LoanRequesrDetails(formBean,login);
			  
			  if(flag)
			  {
				  
				  path="./LoanRequest.jsp";
				  request.setAttribute("status","LoanDetails Added Successfully");
				 }
			  else {
				  
				  path="./LoanRequest.jsp";
				  request.setAttribute("status","LoanDetails adding failed");
				 
				
			}
			  
		}
		  catch (Exception e) {
			e.printStackTrace();
			path="./LoanRequest.jsp";
			  request.setAttribute("status","Server Busy");
			 
		}


		  RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request,response);


	
	
	
	}

	
}
