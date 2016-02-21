package com.bank.Action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bank.DAO.LoanRequestDao;
import com.bank.FormBean.LoanRequestFormBean;







public class ViewLoanRequestAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	LoanRequestFormBean fb=new LoanRequestFormBean();
		Map map=request.getParameterMap();
	try {
		BeanUtils.populate(fb, map);
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
        String path="";
        Vector<LoanRequestFormBean> AllLoanRequest=null;

	  try{
		  
		 
		 
		  try {
			AllLoanRequest= new LoanRequestDao().getAllLoanRequest();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  System.out.println("in Action class vcb Loans..........."+AllLoanRequest);
		   if(!AllLoanRequest.isEmpty())
		  {
			  request.setAttribute("LoanRequest", AllLoanRequest);
			  request.setAttribute("status", "Here is the Loans ");
			  path="./ViewLoanRequest.jsp";
			 }
		  else {
			  request.setAttribute("status", "No Loans R There");
			  path="./ViewLoans.jsp";
			}
		  }
	  catch (NullPointerException e) {
		  request.setAttribute("status", "Invalid data");
		  path="./ViewLoanRequest.jsp";
		e.printStackTrace();		
	}
	RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);
}

	}


