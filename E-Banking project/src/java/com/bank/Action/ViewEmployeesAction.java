package com.bank.Action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.RegisterDaoImpl;
import com.bank.util.CoreList;


public class ViewEmployeesAction extends HttpServlet {

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
			  
			 
			  
			  CoreList vqb= new RegisterDaoImpl().getEmployeeInfo(path1);				  								 
			  
			  request.setAttribute("UserInfo", vqb);
			  System.out.println("in Action class vcb userinfo..........."+vqb.size());
			  
			  
			  if(!vqb.isEmpty())
			  {
				  
				  path="./Viewemployee.jsp";
				  request.setAttribute("UserInfo",vqb);
				  request.setAttribute("status","Here Is The Employees Info");
				 }
			  else {
				  
				  path="./Viewemployee.jsp";
				  request.setAttribute("status","Here Is The Users Info");
				 
				
			}
			  
		}
		  catch (Exception e) {
			e.printStackTrace();
			path="./Viewemployee.jsp";
			  request.setAttribute("status","Server Busy");
			 
		}


		  RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request,response);


	
	
	
	}

}
