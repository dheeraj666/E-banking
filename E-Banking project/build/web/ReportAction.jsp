<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.bank.util.DateWrapper"%>
<%@page import="com.bank.DAO.AccountDAoImpl"%>
<%@page import="com.bank.util.CoreHash"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.bank.FormBean.AccountFormBean"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="en" xmlns="http://www.w3.org/1999/xhtml" lang="en">
 <body onload="mainOnLoad();">
    <%String report="";
    report=report+"<table width=786 border=1 align=center>";
    report=report+"<tr bgcolor=lightblue><td><div><strong>SNo</strong></div></td><td><div><strong>AccountNo</strong></div></td><td><div><strong>TrasactionType</strong></div></td><td><div><strong>Transaction Amount</strong></div></td><td><div><strong>Transaction Date</strong></div></td></tr>";
    %>
	
	<jsp:include page="Header.jsp"></jsp:include><br/><br/>
         
      Export to : <a href="ExportXLS" target="_blank"><font color="brown">XLS</font></a><br/>
      <table width="786" border="1" align="center">
      <tr bgcolor="lightblue">
        <td><div class="style2"><strong>SNo</strong></div></td>
        <td><div class="style2"><strong>AccountNo</strong></div></td>
        <td><div class="style2"><strong>TrasactionDescription</strong></div></td>
        <td><div class="style2"><strong>Transaction Amount</strong></div></td>
        <td><div class="style2"><strong>Transaction Date</strong></div></td>
      </tr>
		       
		       
		       <%
		        String acctype1=request.getParameter("acctype"); 
		        System.out.println("acctype1=========="+acctype1);
		       if(acctype1.equalsIgnoreCase("Savings")){
		       %>
		       <h3>Bank Transaction Report Details of Savings</h3>  
		       <%}
		       else{
		        %>
		        <h3>Bank Transaction Report Details of Checkings</h3>  
		<%} %>
		<%int sno=1;
		   String acctype=request.getParameter("acctype");
		   
		   String accno=request.getParameter("accno");
		   
		   CoreHash aCoreHash=new AccountDAoImpl().getReport(accno,acctype);
		   if(aCoreHash.size()>0){
		   System.out.println(acctype+"---From jsp ---"+accno);
		   Enumeration e=aCoreHash.keys();
		   while(e.hasMoreElements())
		   {
		   Integer i=(Integer)e.nextElement();
		   AccountFormBean aTransaction=(AccountFormBean)aCoreHash.get(i);
		   report=report+"<tr><td><div></div></td>";
             report=report+"<td>"+aTransaction.getAccno()+"</td>";
             report=report+"<td>"+aTransaction.getAcctype()+"</td>";
             report=report+"<td>"+aTransaction.getTamount()+"</td>";
             report=report+"<td>"+aTransaction.getTdate()+"</td>";
		   
		   
		   %>
		  
		   
		  <tr>
		  <td><div><%=sno%></div></td>
        <td><div><%=aTransaction.getAccno() %></div></td>
        <td><div><%=aTransaction.getAcctype() %></div></td>
        <td><div><%=aTransaction.getTamount()%></div></td>
        <td><div><%=aTransaction.getTdate()%></div></td>
      </tr>
<%

sno++;
} 


}else{%>
<tr><td>Sorry No Transaction Info Is Available</td></tr>
 <%
 }

report+="</table>";
  session.setAttribute("Report",report);
  %>

</table>
 
 <br/>
     <jsp:include page="Footer.jsp"></jsp:include>
 
 
</body></html>
