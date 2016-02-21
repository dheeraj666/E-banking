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

import com.bank.DAO.LoansDao;
import com.bank.FormBean.LoansFormBean;







public class ViewLoansAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        LoansFormBean fb=new LoansFormBean();
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
        Vector<LoansFormBean> AllLoans=null;

	  try{
		  
		 
		 
		  try {
			AllLoans = new LoansDao().getAllLoans();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  System.out.println("in Action class vcb Loans..........."+AllLoans);
		   if(!AllLoans.isEmpty())
		  {
			  request.setAttribute("Loans", AllLoans);
			  request.setAttribute("status", "Here is the Loans ");
			  path="./ViewLoans.jsp";
			 }
		  else {
			  request.setAttribute("status", "No Loans R There");
			  path="./ViewLoans.jsp";
			}
		  }
	  catch (NullPointerException e) {
		  request.setAttribute("status", "Invalid data");
		  path="./ViewLoans.jsp";
		e.printStackTrace();		
	}
	RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request,response);
}

	}


