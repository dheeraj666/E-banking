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
   }else if(click=="Phone"){
   
   window.location.href="./Phone.jsp";
   }
   else if(click=="AccountNumber"){
   
   
   window.location.href="./SearchCustomer.jsp";
  
  }
  
  }
  
  
  </script>

  </head>
  
  <body>
  <jsp:include page="Header.jsp"></jsp:include>
  <br/><br/>
   
   <table><tr><td><a href="./Name.jsp"><input type="button" value="Name" id="name" onclick="invoke();"></a></td><td><a href="./Phone.jsp"><input type="button" value="Phone"></a></td><td><a href="./SearchCustomer.jsp"><input type="button" value="AccountNumber"></a></td></tr></table>
   
  </body>
</html>
