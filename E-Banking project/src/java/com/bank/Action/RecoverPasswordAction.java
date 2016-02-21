package com.bank.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.SecurityDaoImpl;
import com.bank.FormBean.RegisterFormBean;
import com.bank.util.LoggerManager;

public class RecoverPasswordAction extends HttpServlet {

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

		String target = "Recoverpassword.jsp?status=<font color=red>Entries not Matched... Try Again</font>";;
	      try{
	    	  String sanswer="";
	          String password="";
	    	  RegisterFormBean rb=new RegisterFormBean();
	          String loginid=request.getParameter("username");
	          String squest=request.getParameter("squest");
	          rb.setUsername(loginid);
	          rb.setSquest(squest);
	         
	          sanswer=request.getParameter("sanswer");
	          
	          
	          rb.setSanswer(sanswer);
	          
	              password=new SecurityDaoImpl().passwordRecovery(rb);
	          if(password.equals("") || password==null){
	              target="./Recoverpassword.jsp";
	          request.setAttribute("status","Entries not Matched... Try Again");
	          }
	          else{
	        	
	             target="./Login.jsp";
	             request.setAttribute("status","Password is "+password);
	        }
	      }
	        catch(Exception e)
	        {
	           LoggerManager.writeLogSevere(e);
	        }
	        RequestDispatcher rd = request.getRequestDispatcher(target);
	        rd.forward(request,response);
	  
	}

}
