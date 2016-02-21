package com.bank.Action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.DAO.SecurityDaoImpl;
import com.bank.FormBean.LoginForm;
import com.ibm.Authentication.DesEncrypter;





public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		   String role="";
		
		String target ="";
		
		HttpSession session=request.getSession();
		
		LoginForm lf=new LoginForm();
		
		String username = (String)session.getAttribute("username");
		lf.setUsername(username);
		String pass=request.getParameter("password");
		DesEncrypter encrypter=new DesEncrypter("passPhrase");
		String encriptpwd=encrypter.encrypt(pass);
		
		lf.setPassword(encriptpwd);
		
		
		
		try{
			
		
             
		     role = new SecurityDaoImpl().loginCheck(lf);
		
		
			System.out.println("in LoginAction Role is.........."+role);

			if (role.equalsIgnoreCase("admin"))
			{
				
				request.setAttribute("status","Welcome "+ username);
				
				target = "./Name.jsp";
				
				session.setAttribute("loginuser", username );
				session.setAttribute("role", role);
				session.setAttribute("password",pass);
			
				
			}
			
			else if (role.equalsIgnoreCase("employee"))
			{
				
                request.setAttribute("status","Welcome " + username );
				 target = "./Name.jsp";
				session.setAttribute("loginuser", username );
				session.setAttribute("role", role);
				session.setAttribute("password",pass);
				
			}
			else if (role.equalsIgnoreCase("customer"))
			{
				
                request.setAttribute("status","Welcome " + username );
				 target = "./userhome.jsp";
				session.setAttribute("loginuser", username );
				session.setAttribute("role", role);
				session.setAttribute("password",pass);
				
			}
			else
			{
				request.setAttribute("status", "Invalid username&password");
				target = "./Login.jsp";
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
				
	  }

}
