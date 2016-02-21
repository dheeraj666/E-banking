package com.bank.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.RegisterDaoImpl;

public class DeleteEmployeeAction extends HttpServlet {

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
		
		int uid=Integer.parseInt(request.getParameter("userid"));
		
		
		boolean flag=new RegisterDaoImpl().deleteEmployee(uid);
		
		
		if(flag){
			
	     request.setAttribute("status", "employeeDeleted");
	     path="./ViewEmployeesAction";
			
			
		}else{
			
			request.setAttribute("status", "employee Not Deleted");
		     path="./ViewEmployeesAction";
			
			
		}
		
		
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		request.setAttribute("status", "Server Busy");
	     path="./ViewEmployeesAction";
		
	}
		
		
	RequestDispatcher rd=request.getRequestDispatcher(path);
	
	rd.forward(request, response);
	}

}
