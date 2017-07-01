<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${scene.scene}'s scenery</title>
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
#footer {
    background-color:#eeeeee;
    width:350px;
    float:left;
    padding:10px;	 	 
}
</style>
</head>
<body>
   <div id="header">
<h1>${scene.scene}</h1>
</div>

<div id="nav">
</div>

<div id="section">

<p>
${scene.profile}
</p>
<p>
Phone: ${scene.phone}
</p>
<p>
Website: ${scene.website}
</p>
<p>
Transportation: ${scene.transportation}
</p>
<p>
Ticket: ${scene.ticketFee}
</p>
<p>
OpenTime: ${scene.openTime}
</p>
<p>
EstimatedTimeConsumption: ${scene.estimatedStayPeriod}
</p>

</div>

<div id="section">

<h2>Reviews</h2>
			<c:forEach var="sceneValue" items="${sceneList}">
			  
				<p>${sceneValue.username}
				${sceneValue.review}
                ${sceneValue.publishTime}
				
				</p>
					
             <br/>
			</c:forEach>

</div>

<div id="footer">

<h2>${scene.scene}</h2>
	   <form action="submitreview?username=${username}&sceneID=${scene.sceneID}" method=post>
		
		<p><textarea name="text" rows="20" cols="70">Enter review here...</textarea></p>
		 <input type="submit" value="submit"/>
		
		</form>

</div>



</body>
</html>