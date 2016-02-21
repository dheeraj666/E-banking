package com.bank.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.LoanRequestDao;

/**
 * Servlet implementation class RejectAction
 */
@WebServlet("/RejectAction")
public class RejectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	String path="";
        
        boolean flag=false;

	  try{
		  
		 int rid=Integer.parseInt(request.getParameter("rid"));
		 
		  flag= new LoanRequestDao().reject(rid);
		 
		 
		   if(flag)
		  {
			  
			  request.setAttribute("status", " Reject Successfully");
			  path="./ViewLoanRequestAction";
			 }
		  else {
			  request.setAttribute("status", "Rejection  is failed");
			  path="./ViewLoanRequestAction";
			}
		  }
	  catch (Exception e) {
		  request.setAttribute("status", "Invalid data");
		  path="./AcceptAction.java";
		e.printStackTrace();		
	}
	RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);
	}

		// TODO Auto-generated method stub
	}

