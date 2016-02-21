package com.bank.Action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.DAO.MailDao;


public class PostMail extends HttpServlet {

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

		
		
		
		RequestDispatcher rd = null;
		
		String target = "./SessionOut.jsp";
		String user = request.getSession().getAttribute("loginuser").toString();
		if (user != null) {
			String mailTo=request.getParameter("mailTo");
			String mailFrom=request.getParameter("mailFrom");
			String password=request.getParameter("password");
			String body=request.getParameter("mailBody");
			String status=request.getParameter("status");
			
			String aid=request.getParameter("admission");
			System.out.println("in Action class======Aid===="+aid);
		    boolean flag=false;
			try {
				flag=new MailDao().postMailTo(mailTo,mailFrom,password,body,status,aid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(flag){
				request.setAttribute("status", "your mail has been sent successfully");
				request.setAttribute("dMail", mailTo);
				target="./ComposeMail.jsp";
			}else
			{
				request.setAttribute("status", "your mail sending failed");
				request.setAttribute("dMail", mailTo);
				target="./ComposeMail.jsp";
			}
		}
		rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
		
	
		
	}

}

