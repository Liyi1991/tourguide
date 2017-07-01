<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>${destination.destination}</title>



<link href="${pageContext.request.contextPath}/resources/CSS/1.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/resources/CSS/2.css" rel="stylesheet" type="text/css"/>

<script language="javascript" src="${pageContext.request.contextPath}/resources/JS/3.js" type="text/javascript"></script>

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
                        <input id="head-search-word" name="query" autocomplete="off" type="text" cols=50  placeholder="search destinations/scenes/notes" />
                    </div>
                    <div class="s-button"><input type="submit" value=" " /></div>
                </form>
            </div>
        </div>
    </div>
</div> 


<div id="container" data-cs-t="目的地详情页">

<div class="row row-primary">
    <div class="wrapper">
       
<div class="crumb">
    <div class="item"><a href="" target="_blank">Destinations</a><em>&gt;</em></div>
             
     <div class="item">
        <div class="drop">
            <span class="hd">${destination.destination}<i></i></span>
            <div class="bd">
                <i class="arrow"><b></b></i>
                <div class="col">
                   
                    <ul class="clearfix">
                        <li><a href="destinationScene?destinationID=${destination.destinationID}" target="_blank">Scenes in ${destination.destination}</a></li>
                        
                        <li><a href="destinationNote?destinationID=${destination.destinationID}" target="_blank">Notes about ${destination.destination}</a></li>
                        
                    </ul>
                </div>
               
            </div>
        </div>
       
    </div>
</div>



<div class="nav-block" id="top_menu" style="background-color: #ffffff">
    <div class="nav-bd">
        <ul class="nav-box clearfix">
                        <li class="nav-overview city-guide">
                <a href="">
                    <span class="img"><img src="${pageContext.request.contextPath}/resources/image/Icons/nav-icon-overview.png"></span>
                    <span class="tit">Profile</span>
                </a>
            </li>
                        <li class="nav-scenic">
                <a href="destinationScene?destinationID=${destination.destinationID}">
                    <span class="img"><img src="${pageContext.request.contextPath}/resources/image/Icons/nav-icon-scenic.png"></span>
                    <span class="tit">Scenes</span>
                </a>
            </li>
           
            <li class="nav-travels ">
                <a href="destinationNote?destinationID=${destination.destinationID}">
                    <span class="img"><img src="${pageContext.request.contextPath}/resources/image/Icons/nav-icon-travels.png"></span>
                    <span class="tit">Travel Notes</span>
                </a>
            </li>
                           

           </ul>
         <div class="overview-drop hide">
            <div class="drop-bd">
            
                 <dl class="clearfix">
                    <dt><a href="" target="_blank">Location</a></dt>
                    <dd>
                                                <a href="" target="_blank">${destination.district} > ${destination.state}</a>
                       
                                                                    </dd>
                </dl>
                
                 <dl class="clearfix">
                    <dt><a href="" target="_blank">Profile</a></dt>
                    <dd>
                                                <a href="" target="_blank">${destination.profile}</a>
                       
                                                                    </dd>
                </dl>
                
                 <dl class="clearfix">
                    <dt><a href="" target="_blank">BestVisitTime</a></dt>
                    <dd>
                                                <a href="" target="_blank">${destination.bestVisitTime}</a>
                                                                    
                                                                     </dd>
                </dl>
                
                 <dl class="clearfix">
                    <dt><a href="" target="_blank">WearingGuide</a></dt>
                    <dd>
                                                <a href="" target="_blank">${destination.wearingGuide}</a>
                                                                        
                                                                    </dd>
                </dl>
                
                <dl class="clearfix">
                    <dt><a href="" target="_blank">Telecommunication</a></dt>
                    <dd>
                                                <a href="" target="_blank">${destination.telecommunication}</a>
                                                                     
                                                                    </dd>
                </dl>
                              
                                
                
                               
                               
             </div>
                 </div>
     </div>
</div>

<script language="JavaScript" type="text/javascript">
    (function(){
        var navigation = $('#top_menu'),
            offset_top = navigation.offset().top,
            h = navigation.outerHeight(true),
            intervalod,
            navdrop = $('.city-guide'),
            overviewdrop = $('.overview-drop');
        navigation.after('<div id="fill_area" style="height: '+h+'px;display:none;"></div>');
        function setFixed(){
            
                        var scrolltop = $(document).scrollTop(),
                    fillarea = $('#fill_area');
            if(scrolltop  > offset_top + 60){
                fillarea.show();
                navigation.addClass('nav-fixed');
            }else{
                fillarea.hide();
                navigation.removeClass('nav-fixed');
            }
                    }
        $(window).scroll(function(){
            setFixed();
        });
      
        navdrop.mouseenter(function(ev) {
            clearTimeout(intervalod);
            overviewdrop.removeClass('hide');
        });
        navdrop.mouseleave(function(ev) {
            intervalnd = setTimeout(function(){
                overviewdrop.addClass('hide');
            }, 200);
        });
   
        overviewdrop.mouseenter(function(ev) {
            clearTimeout(intervalnd);
            overviewdrop.removeClass('hide');
        });
        overviewdrop.mouseleave(function(ev) {
            intervalod = setTimeout(function(){
                overviewdrop.addClass('hide');
            }, 100);
        });
        setFixed();
    })();
</script>

<script language="JavaScript" type="text/javascript">
    $(function () {
       
        $('.drop').mouseenter(function(ev) {
            var target = $(ev.currentTarget);
            clearTimeout(target.data('hideTimer'));
            target.addClass('open');
            target.children('bd').fadeIn(200);
        });
        $('.drop').mouseleave(function(ev) {
            var target = $(ev.currentTarget);
            target.data("hideTimer", setTimeout(function() {
                target.removeClass('open');
                target.children('bd').fadeOut(200);
            }, 200));
        });
    });
</script>

<link href="${pageContext.request.contextPath}/resources/CSS/4.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
    #header{
        margin-bottom: 0;
    }
    .crumb .item a{
        color:#666;
        font-size:12px;
    }
    .crumb {
        width: 980px;
        font:12px Arial,"Microsoft Yahei","\5FAE\8F6F\96C5\9ED1",Tahoma,Helvetica,STHeiti,"Hiragino Sans GB";
        height: 20px;
        padding: 15px 0;
        color: #666;
        line-height: 20px;
        margin: 0 auto;
    }
</style>

    
    </div>
</div>



<div class="row row-travels">
    <div class="wrapper">
    
         <div class="hd">
            <ul class="tabs">
                <li  class="on"><a href="">Hottest Scenes</a></li>
              
                            </ul>
            
        </div>
        
			
  <div class="post-list">
    <ul>
           
            <c:if test="${not empty sceneList}">
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
            <span class="status"><i class="icon_comment"></i>${listValue.sceneReviewNum} comments</span>
        </li>
        	
			
			
			</c:forEach>
	     </c:if>
			
                             
         </ul>
</div>
           
           
         <div align="right" class="m-pagination">
        <span class="count">Total<span> ${length} </span>Scenes </span>                        
            </div>

        
  </div>
</div>


  
    
    
</div>

</body>
</html>