package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.LoansDao;
import com.bank.DAO.RegisterDaoImpl;
import com.bank.FormBean.LoansFormBean;
import com.bank.util.CoreList;

public class LoansAction extends HttpServlet {

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
			  
			 
			  
			  String loanname= request.getParameter("loanname");
			  System.out.println("loanname in action========="+loanname);
			  String Loandescription=request.getParameter("loandescription");
			  System.out.println("ldesc in action========="+Loandescription);
			  Double interest =Double.parseDouble(request.getParameter("interest"));
			  System.out.println("interest in action========="+interest);
			  String instalement=request.getParameter("installments");
			  System.out.println("installments in action========="+instalement);
			  String loandate=request.getParameter("loandate");
			  System.out.println("loandate in action========="+loandate);
			  String Status= request.getParameter("Status");
			  System.out.println("Status in action========="+Status);
			  
			  LoansFormBean formBean = new LoansFormBean();
			  formBean.setLoanname(loanname);
			  formBean.setLoandescription(Loandescription);
			  formBean.setInterest(interest);
			  formBean.setInstalement(instalement);
			  formBean.setLoandate(loandate);
			  
			  
			  LoansDao loansDao=new LoansDao();
			 boolean flag= loansDao.LoanDetails(formBean);
			  
			  if(flag)
			  {
				  
				  path="./Loans.jsp";
				  request.setAttribute("status","LoanDetails Added Successfully");
				 }
			  else {
				  
				  path="./Loans.jsp";
				  request.setAttribute("status","LoanDetails adding failed");
				 
				
			}
			  
		}
		  catch (Exception e) {
			e.printStackTrace();
			path="./Loans.jsp";
			  request.setAttribute("status","Server Busy");
			 
		}


		  RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request,response);


	
	
	
	}

	
}
