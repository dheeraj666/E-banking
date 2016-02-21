<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
  <script type="text/javascript">
  
  function invoke(){
   
   var click=document.getElementById("name").value;
   
   
   if(click=="Name"){
   window.location.href="./Name.jsp";
  }
  }
  
  function phone(){
   
   var click=document.getElementById("phone").value;
   
   
   if(click=="Phone"){
  window.location.href="./Phone.jsp";
 
  }
  }
     </script>
     <script type="text/javascript">
   
  function accno(){
   
   var click=document.getElementById("accno").value;
   if(click=="AccountNumber"){
  window.location.href="./SearchCustomer.jsp";
  
  }
  }
  </script>

<script language="JavaScript" src="scripts/gen_validatorv31.js" type="text/javascript"></script>
  </head>
  <jsp:include page="Header.jsp"></jsp:include>
  <br/><br/>
  <body>
  
   <table align="center"><tr><td><a href=""><input type="button" value="Name" id="name" onclick="invoke();"></a></td><td><a href="./Phone.jsp"><input type="button" value="Phone" id="phone" onclick="phone();"></a></td><td><a href="./SearchCustomer.jsp"><input type="button" value="AccountNumber" id="accno" onclick="accno();"></a></td></tr></table>
  
  <form action="./SearchByNameAction" name="search">
  <table bgcolor="#66CC33" align="center">
  <tr><td>FirstName<input type="text" name="fname">
 </td><td>SecondName<input type="text" name="lname"> 
 </td>
 </tr>
 <br/><br/>
 
 <tr align="center"><td><input type="submit" value="Search"></td></tr>
  
  </table> 
  </form>
  <script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
  var frmvalidator  = new Validator("search");
  
 
  frmvalidator.addValidation("fname","req","Please enter Firstname");
  frmvalidator.addValidation("fname","alpha","only characters");
  
  frmvalidator.addValidation("lname","req","Please enter Lastname");
  frmvalidator.addValidation("lname","alpha","only characters");
  
  </script>
   </body>
   
   <br/>
   <jsp:include page="Footer.jsp"></jsp:include>
</html>
