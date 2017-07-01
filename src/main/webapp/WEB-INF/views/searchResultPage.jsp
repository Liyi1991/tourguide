<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/CSS/1.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/resources/CSS/2.css" rel="stylesheet" type="text/css"/>

<script language="javascript" src="${pageContext.request.contextPath}/resources/JS/3.js" type="text/javascript"></script>

<title>Search result for:${query}</title>
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
             
             <div class="head-search" id="head-search">
                <form name="head-search-form" id="head-search-form" action="search" method="post">
                    <div class="s-input">
                        <input id="head-search-word" name="query" autocomplete="off" type="text" cols=50 placeholder="search destinations/scenes/notes" />
                    </div>
                    <div class="s-button"><input type="submit" value="  " /></div>
                </form>
            </div>
        
             
            
        </div>
    </div>
</div> 

          <c:if test="${not empty destinationList}">
<div class="row row-travels">
    <div class="wrapper">
    
         <div class="hd">
            <ul class="tabs">
                <li  class="on">Destinations</li>
              
                            </ul>
            
        </div>
        
			
  <div class="post-list">
    <ul>
           
            
			<c:forEach var="listValue" items="${destinationList}">
			  
				
				<li class="post-item clearfix">
            <div class="post-cover">
                <a href="destination?destinationID=${listValue.destinationID}" target="_blank">
                    <img class="lazy" src="${pageContext.request.contextPath}/resources/image/${listValue.image}" width="250" height="185" >
                </a>
                                <b class="icon_baozang"></b>
              </div>
           
            <h2 class="post-title yahei">
                                <a href="destination?destinationID=${listValue.destinationID}" target="_blank">${listValue.destination} </a>
             </h2>
             
             <div class="post-author">
                <span class="author">
                    
                    Location：${listValue.district} America
                </span>
                <span class="post-date">
                    
                    <span class="post-date">${listValue.state}</span>
                </span>
            </div>
            <div class="post-content">
                 ${listValue.profile} 
            </div>
            
        </li>
        	
			</c:forEach>
	          
         </ul>
</div>
           
           
         <div align="right" class="m-pagination">
        <span class="count">Total<span> ${delength} </span>Destinations </span>                        
            </div>

        
  </div>
</div>
           </c:if>
           
           
           
              <c:if test="${not empty sceneList}">
<div class="row row-travels">
    <div class="wrapper">
    
         <div class="hd">
            <ul class="tabs">
                <li  class="on">Scenes</li>
              
                            </ul>
            
        </div>
        
			
  <div class="post-list">
    <ul>
           
            
			<c:forEach var="listValue" items="${sceneList}">
			  
				
				<li class="post-item clearfix">
            <div class="post-cover">
                <a href="scene?sceneID=${listValue.sceneID}" target="_blank">
                    <img class="lazy" src="${pageContext.request.contextPath}/resources/image/${listValue.image}" width="250" height="185" >
                </a>
                                <b class="icon_baozang"></b>
              </div>
           
            <h2 class="post-title yahei">
                                <a href="scene?sceneID=${listValue.sceneID}" target="_blank">${listValue.scene} </a>
             </h2>
            
            <div class="post-content">
                 ${listValue.profile} 
            </div>
            <span class="status"><i class="icon_comment"></i> comment number: ${listValue.sceneReviewNum}</span>
        </li>
        	
			
			
			</c:forEach>
	     
			
                             
         </ul>
</div>
           
           
         <div align="right" class="m-pagination">
        <span class="count">Total <span>${scenelength} </span>Scenes </span>                        
            </div>

        
  </div>
</div>

              </c:if>




     <c:if test="${not empty noteList}">
<div class="row row-travels">
    <div class="wrapper">
    
         <div class="hd">
            <ul class="tabs">
                <li  class="on">Travel Notes</li>

                            </ul>
            
        </div>
        

			
  <div class="post-list">
    <ul>
           
            
			<c:forEach var="listValue" items="${noteList}">
				
				<li class="post-item clearfix">
            <div class="post-cover">
                <a href="note?noteID=${listValue.noteID}" target="_blank">
                    <img class="lazy" src="${pageContext.request.contextPath}/resources/image/${listValue.image}" width="250" height="185" >
                </a>
                                <b class="icon_baozang"></b>
              </div>
           
            <h2 class="post-title yahei">
                                <a href="note?noteID=${listValue.noteID}" target="_blank">${listValue.noteTitle} </a>
             </h2>
            <div class="post-author">
                <span class="author">
                    <a href="" target="_blank" rel="nofollow"><img src=""></a>
                    Author：<a href="" target="_blank" rel="nofollow">${listValue.username}</a>
                </span>
                <span class="post-date">
                    
                   
                    <span class="post-date">${listValue.publishTime}</span>
                </span>
            </div>
            <div class="post-content">
                 ${listValue.noteContent} 
            </div>
            <span class="status"><i class="icon_comment"></i>comment number: ${listValue.noteReviewNum}</span>
        </li>
        	
			
			
			</c:forEach>
	     
			
                             
         </ul>
</div>
       
           
           
         <div align="right" class="m-pagination">
        <span class="count">Total<span> ${notelength} </span>Notes </span>                        
            </div>
             
        
  </div>
</div>
             
       </c:if>
    


	
</body>
</html>