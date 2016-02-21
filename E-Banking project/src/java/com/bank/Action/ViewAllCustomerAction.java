package com.bank.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.RegisterDaoImpl;
import com.bank.util.CoreList;

/**
 * Servlet implementation class ViewAllCustomerAction
 */
@WebServlet("/ViewAllCustomerAction")
public class ViewAllCustomerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllCustomerAction() {
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

		  try{
			  
			 
			    String path1=request.getRealPath("/userimages");
			  
			 
			  
			  CoreList vqb= new RegisterDaoImpl().getCustomerInfo(path1);				  								 
			  
			  request.setAttribute("UserInfo", vqb);
			  System.out.println("in Action class vcb userinfo..........."+vqb.size());
			  
			  
			  if(!vqb.isEmpty())
			  {
				  
				  path="./ViewAllCustomer.jsp";
				  request.setAttribute("UserInfo",vqb);
				  request.setAttribute("status","Here Is The Customer Info");
				 }
			  else {
				  
				  path="./ViewAllCustomer.jsp";
				  request.setAttribute("status","Here Is The Users Info");
				 
				
			}
			  
		}
		  catch (Exception e) {
			e.printStackTrace();
			path="./ViewAllCustomer.jsp";
			  request.setAttribute("status","Server Busy");
			 
		}


		  RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request,response);




		// TODO Auto-generated method stub
	}

}
