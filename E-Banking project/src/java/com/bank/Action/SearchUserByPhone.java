package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.DAO.AccountDAoImpl;
import com.bank.FormBean.AccountFormBean;

public class SearchUserByPhone extends HttpServlet {

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
        

		  try{
			  String path1=request.getRealPath("/userimages");
			  String phone=request.getParameter("phone");
			  System.out.println("in action class--------------phone---->"+phone);
			  
			 
	      	Vector<AccountFormBean>  vab= new AccountDAoImpl().getCustomerByPhone(phone,path1);
			
			  System.out.println("in Action class vcb ..........."+vab);
			  if(!vab.isEmpty())
			  {
				  request.setAttribute("UserInfo", vab);
				  request.setAttribute("status", "Here is the Info of Customers");
				  path="./ViewAllCustomer.jsp";
				 }
			  else {
				  request.setAttribute("status", "Sorry ....Account not found");
				  path="./SearchCustomer.jsp";
				  }
			  }
		  catch (NullPointerException e) {
			  request.setAttribute("status", "In Valid Entries Try Again");
			  path="./SearchCustomer.jsp";
			e.printStackTrace();
			
		}
		 RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request,response);
			
		
		
		
		
		
		
		
	}

}
