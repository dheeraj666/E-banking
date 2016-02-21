package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.LoansDao;
import com.bank.FormBean.LoansFormBean;



public class DeleteLoansAction extends HttpServlet {

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
        Vector<LoansFormBean> AllLoans=null;
        boolean flag=false;

	  try{
		  
		 int Loanid=Integer.parseInt(request.getParameter("Loanid"));
		 
		  flag= new LoansDao().cancel(Loanid);
		 
		  System.out.println("in Action class vcb Loans..........."+AllLoans);
		   if(flag)
		  {
			  request.setAttribute("Loans", AllLoans);
			  request.setAttribute("status", " Loans Deleted Successfully");
			  path="./ViewLoansAction";
			 }
		  else {
			  request.setAttribute("status", "Deletion of Loans is failed");
			  path="./ViewLoansAction";
			}
		  }
	  catch (NullPointerException e) {
		  request.setAttribute("status", "Invalid data");
		  path="./ViewLoansAction";
		e.printStackTrace();		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);
	}

}
