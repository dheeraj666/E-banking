<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    

  </head>
  
  <body>
   
    <table border="1" bgcolor="#FFF8DC"><tr background="images/backgrnd[2].gif"><td><a href="./AdminHome.jsp">Home</a></td>&nbsp;&nbsp;<td>||<a href="./AccountBalanceAction.jsp"> AccountBalance</a>&nbsp;||</td>&nbsp;&nbsp;<td><td><a href="./Transforamount.jsp"> TransforAmount</a>&nbsp;||</td><td><a href="./UserTransaction.jsp"> Transactions</a>&nbsp;||</td>
  <td><a href="./ViewProfile.jsp"> ViewProfile</a>&nbsp;||</td><td><a href="./UpdateProfile.jsp"> UpdateProfile</a>&nbsp;||</td><td><a href="./Changepassword.jsp"> ChangePassword</a>&nbsp;||</td><td><a href="./LogoutAction"> Logout</a>&nbsp;||</td></tr></table>
    
   
  </body>
</html>
