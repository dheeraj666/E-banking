package com.bank.Action;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.DAO.LoanRequestDao;
import com.bank.FormBean.LoanRequestFormBean;

/**
 * Servlet implementation class ViewLoanStatusAction
 */
@WebServlet("/ViewLoanStatusAction")
public class ViewLoanStatusAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewLoanStatusAction() {
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
		// TODO Auto-generated method stub
		
		
String path="";
        
        boolean flag=false;

	  try{
		  
		  	HttpSession hs=request.getSession();
    	  
	      	String login=(String)hs.getAttribute("username");
	      	
	      	System.out.println("in GetUser Action login is........................>"+login);
	      	
			  
		
		 
	      	Vector<LoanRequestFormBean> vab= new LoanRequestDao().loanStatus(login);
		 
		 
		   if(vab!=null)
		  {
			  
			  request.setAttribute("LOANREQUEST", vab);
			  path="./ViewStatus.jsp";
			 }
		  else {
			  request.setAttribute("status", "View Failed");
			  path="./ViewStatus.jsp";
			}
		  }
	  catch (Exception e) {
		  request.setAttribute("status", "Invalid data");
		  path="./ViewStatus.jsp";
		e.printStackTrace();		
	}
	RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);

		
	}

}
