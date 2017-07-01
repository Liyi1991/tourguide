<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<center> 
 <b> Login Form </b> 
  <div>
   <form:form method="post" action="process-login.html" commandName="user-entity">
    <table>
     <tr>
      <td> UserName :</td>
      <td><form:input path="username" /></td>
     </tr>
     <tr>
      <td> Password :</td>
      <td><form:input path="password" type = "password"/></td>
     </tr>
     <tr>
      <td> </td>
      <td><input type="submit" value="submit" /></td>
     </tr>
    
     
    </table>
    </form:form>
    
  </div>
 </center>
</body>
</html>