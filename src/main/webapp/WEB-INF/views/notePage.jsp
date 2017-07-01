<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${note.noteTitle}</title>
<style>
#header {
    background-color:#eeeeee;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    height:300px;
    width:100px;
    float:left;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	 	 
}

</style>
</head>
<body>
<div id="header" xmlns="http://www.w3.org/1999/html">
    
    <div class="head-content clearfix" data-cs-t="studio">
        
        <div class="head-info">
        
             
            
            
            <c:if test="${not empty userObj}">     
            <div class="user-block">
                <ul>
                    <li class="ub-item ub-info drop-wrap">
                        <a href=""  class="drop-hd" rel="nofollow">${userObj.username}<i></i></a>
                        <ul class="drop-bd">
                            
       
                            
                            <li>
                                <a class="drop-ask" href="" rel="nofollow">
                                    <i></i>
                                    View My Notes
                                </a>
                            </li>
                            
                            <li><a href="logout.html"  rel="nofollow">Logout</a></li>
                        </ul>
                    </li>
           
                   </ul>
                </div> 
                </c:if>  
               
              <c:if test="${ empty userObj}">   
               <div class="user-block">
                <ul>
                   
                    <li class="lb-item lb-connect">
                        <button onclick="window.location.href='login.html'">Login</button>
                        <button onclick="window.location.href='register.html'">Register</button>
                        
                    </li>
                </ul>
            </div>
            </c:if>
            
            
            
             
            
        </div>
    </div>
</div> 
  <div id="header">
<h1>${note.noteTitle}</h1>
</div>

<div id="nav">
</div>


<div id="section">

<p>
Author: ${note.username}   Published date: ${note.publishTime}
</p>

<p>
 ${note.noteContent}
</p>
<c:forEach var="imageValue" items="${imageList}">
	<img src="${pageContext.request.contextPath}/resources/image/${note.image}/${imageValue}" width="250" height="185" >		  
<br>			
					
</c:forEach>
</div>

<div id="section">


<h2>Reviews</h2>
			<c:forEach var="noteValue" items="${noteList}">
			  
				<p>${noteValue.reviewUsername}       ${noteValue.reviewPublishTime}</p>
				<p>
				${noteValue.review}
              
				
				</p>
					
             <br/>
			</c:forEach>

</div>

<div id="section">

<h2>Write a Review:</h2>
		
		<form action="multipartFileUpload?username=${userObj.username}&noteID=${note.noteID}"  method="post" enctype="multipart/form-data">
	
		<p><textarea name="review" rows="20" cols="60">Enter review here...</textarea></p>
		<br>
		<input type="file" name="file">
		<br>
		<input type="file" name="file">
		<br>
		<input type="hidden" name="username" value="${userObj.username}">
		<br>
		<input type="hidden" name="noteID" value="${note.noteID}">
		<br>
		
		<input type="submit" value="submit">
		
	</form>

</div>



</body>
</html>