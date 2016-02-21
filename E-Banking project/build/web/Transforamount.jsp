<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.bank.DAO.AccountDAoImpl"%>
<%@page import="com.bank.FormBean.AccountFormBean"%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
<script language="JavaScript" src="scripts/gen_validatorv31.js" type="text/javascript"></script>
  </head>
  
  <body>
  
		  <div class="hr"> </div>
          <p></p> 
 <center>
 
 
  <%!
   
          Date date=new Date();
              DateFormat df = new SimpleDateFormat("dd-MM-yy");
              
            String  reportDate = df.format(date); 
             
             %>
             
             
         <%
           if(request.getParameter("status")!=null){
          %>
         <font color="red"> <%=request.getParameter("status") %></font>
          <%}
          
          String login=(String)session.getAttribute("loginuser");
          
           %>
          </center>
  
  <body>
  <jsp:include page="Header.jsp"></jsp:include>
   <center><h2>Transfor Amount Form u r Account to AnotherAccount</h2></center>
 
       <form action="./AccountTransforAction"   method="post" onsubmit="return checkban()" name="account">
         <table width="200" border="0" align="center" bgcolor="#66CC33">
           <tr>
             <td><table  border="1" >
               <tr>
                 <td height="120" align="right"></td>
                 <td><table border="0" align="center">
                 
                 
                 
                 <%
                 
                
           
            AccountDAoImpl loanDAO=new AccountDAoImpl();
            
            Vector<AccountFormBean> loanList=loanDAO.getAccno(login);
            
            if(loanList.isEmpty()){
            
            %>
            <tr>
            <td><b> <font size="3">Sorry U Account is Locked .Try Again</font></b>
            <%
            }
            
            System.out.println("in jsp vector obj ...is........"+loanList);
            System.out.println("in jsp vector obj ...is........"+loanList+"and size is ........"+loanList.size());  
           
          
         Iterator it=loanList.listIterator();
%>

                     <tr>
                     
                     <td>AccountType</td>  
                     <td>
                     <select name="accfrom">
                     <option>--SELECT--</option>
                     <% while(it.hasNext())
                     {
                     AccountFormBean tfb=(AccountFormBean)it.next();
                     %>
                     
                    <option value="<%=tfb.getAccno() %>"><%=tfb.getAcctype() %> </option>
                    
                       <%}
                        %>
                      </select>
                     
                     
                    
                     </tr>
                   
                                
                     <tr>
                     <td>ToAccountNo</td>
                     <td>
                      <input type=text name="accto" />
                      </td>
                     </tr>
                     
                     <tr>
                     <td>date</td>
                     <td>
                   <input type=text name="tdate" readonly="readonly" value="<%=reportDate %>"/>
       
                     </td>
                     </tr>
  
                   <tr>
                     <td>TransactionAmount</td>
                      <td>
                        <input type=text name="tamount"/>
                        </td>
                      </tr>
                   <tr>
                     <td colspan="2">
                       <div align="center">
                         <input type=submit value="Submit">
                          &nbsp;
                          <input type=reset value="Clear">
                         </div>                        </td>
                      </tr>
                 </table></td>
                 <td>&nbsp;</td>
               </tr>
             </table></td>
           </tr>
           </table>
           
           
            <br/><br/>
                 
		</form>
		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
  var frmvalidator  = new Validator("account");
  
 
  frmvalidator.addValidation("accfrom","req","Please enter From accno");
  frmvalidator.addValidation("accfrom","numeric");
  
  frmvalidator.addValidation("accto","req","Please enter To accno");
  frmvalidator.addValidation("accto","numeric");
  frmvalidator.addValidation("tamount","req","Transaction Amount is Required");
  
  </script>
		 <br/>
     <jsp:include page="Footer.jsp"></jsp:include>
  </body>
</html>
