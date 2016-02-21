package com.bank.Action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.bank.DAO.ForgotPwdDAO;
import com.bank.FormBean.RegisterFormBean;



/**
 * Servlet implementation class ForgotPasswordAction
 */
@WebServlet("/ForgotPasswordAction")
public class ForgotPasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=null;

		boolean flag=false;
		String path="";
		
		RegisterFormBean rto=new RegisterFormBean();
		
		
		Map map=request.getParameterMap();
	 try {
		BeanUtils.populate(rto, map);
		
		
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
		
		try{
			
			String email=(request.getParameter("email"));
		
			
			HttpSession session=request.getSession();
      	  
        	String login=(String) session.getAttribute("loginuser");
        	 
        	   rto.setLogin(login);
			
			
			
			    flag=new ForgotPwdDAO().postMailTo(email);
			    
			
			if(flag){
				
				
				path="./forgotpassword.jsp";
				
				request.setAttribute("status", "Check your mail for password recovery");
				}
			else{
				
               path="./forgotpassword.jsp";
				
				request.setAttribute("status", "Recovery Failed..check your EmailId");
				}
			}catch (Exception e) {
			e.printStackTrace();
			
			path="./forgotpassword.jsp";
			
			request.setAttribute("status", "Envalied enteries");
		}
		rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
	
	}

}
