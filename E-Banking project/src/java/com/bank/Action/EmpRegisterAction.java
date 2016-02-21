package com.bank.Action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.bank.DAO.RegisterDaoImpl;
import com.bank.FormBean.RegisterFormBean;

public class EmpRegisterAction extends HttpServlet{
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
	
	HttpSession session=request.getSession();
	  
	String login=(String) session.getAttribute("loginuser");
	    
	   rto.setLogin(login);
	
	
	
	    flag=new RegisterDaoImpl().insertEmployees(rto);
	    
	
	if(flag){
		
		
		path="./AppointEmployee.jsp";
		
		request.setAttribute("status", "Employee Registeration is successfull");
		}
	else{
		
       path="./AppointEmployee.jsp";
		
		request.setAttribute("status", "Employee Registeration is Failed");
		}
	}catch (Exception e) {
	e.printStackTrace();
	
	path="./AppointEmployee.jsp";
	
	request.setAttribute("status", "Envalied enteries");
}
rd=request.getRequestDispatcher(path);
rd.forward(request, response);

}

}
