package com.bank.Action;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.FeedbackDaoImpl;
import com.bank.DAO.LoanRequestDao;
import com.bank.FormBean.FeedBackFormBean;
import com.bank.FormBean.LoanRequestFormBean;

/**
 * Servlet implementation class AcceptAction
 */
@WebServlet("/AcceptAction")
public class AcceptAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path="";
        
        boolean flag=false;

	  try{
		  
		 int rid=Integer.parseInt(request.getParameter("rid"));
		 
		  flag= new LoanRequestDao().accept(rid);
		 
		 
		   if(flag)
		  {
			  
			  request.setAttribute("status", " Acceppt Successfully");
			  path="./ViewLoanRequestAction";
			 }
		  else {
			  request.setAttribute("status", "Aception is failed");
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
	

	}


