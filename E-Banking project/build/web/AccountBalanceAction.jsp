<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.bank.DAO.AccountDAoImpl"%>
<%@page import="com.bank.FormBean.AccountFormBean"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>

 



<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="en" xmlns="http://www.w3.org/1999/xhtml" lang="en"><head>
<body onload="mainOnLoad();">
	
			
		
			<jsp:include page="Header.jsp"></jsp:include>
		<br />
		<br />
		 
         
      <table width="786" border="1"  align="center">
      <tr bgcolor="#66CC33">
              <td><div class="style2"><strong>AccNo</strong></div></td>
        <td><div class="style2"><strong>AccountType</strong></div></td>
        <td><div class="style2"><strong>Balance Amount</strong></div></td>
        <td><div class="style2"><strong>Date</strong></div></td>
      </tr>
		       <h3><i> <font color="green">U R Balance Details</font></i></h3>       
		<%
		 String login=(String)session.getAttribute("loginuser");   
		 Vector<AccountFormBean> aDetails=new AccountDAoImpl().getAccno(login);
		 Iterator it=aDetails.iterator();
		 
		 while(it.hasNext()){
		  %>
		  <%
		  
		  AccountFormBean ab=(AccountFormBean)it.next();
		  
		   %>
		  <tr>
		  <td><div><%=ab.getAccno()%></div></td>
         <td><div><%=ab.getAcctype() %></div></td>
        <td><div><%=ab.getAccbal()%></div></td>
        <td><div><%=ab.getTdate()%></div></td>
      </tr>
      
      <%} %>
</table>
 <br/> <br/> <br/>
		  <jsp:include page="Footer.jsp"></jsp:include>
    
	
</body></html>
