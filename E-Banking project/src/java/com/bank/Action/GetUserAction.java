package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.DAO.UserDaoImpl;
import com.bank.util.CoreList;

public class GetUserAction extends HttpServlet {

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
        CoreList empInfo=null;

	  try{
		  String path1=request.getRealPath("/userimages");
		  
		  HttpSession hs=request.getSession();
    	  
      	String login=request.getParameter("username");
      	System.out.println("in GetUser Action login is........................>"+login);
      	
      	hs.setAttribute("username",login );
		  
		 
		  empInfo= new UserDaoImpl().getUser(path1,login);
		
		  System.out.println("in Action class vcb ..........."+empInfo);
		  if(!empInfo.isEmpty())
		  {
			  request.setAttribute("UserInfo", empInfo);
			  request.setAttribute("status", "Here is the Info of Loged person");
			  path="./Enterpassword.jsp";
			 }
		  else {
			  request.setAttribute("status", "No Data Found");
			  path="./Enterpassword.jsp";
			  }
		  }
	  catch (NullPointerException e) {
		  request.setAttribute("status", "In Valid Entries Try Again");
		  path="./Enterpassword.jsp";
		e.printStackTrace();
		
	}
	 RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);
		
		
		
		
		
		
		
	}

}
