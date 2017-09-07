<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
<base href="<%=basePath %>">
<title>黑色时间轴个人博客模板</title>
<meta name="keywords" content="黑色模板,个人网站模板,个人博客模板,博客模板,css3,html5,网站模板" />
<meta name="description" content="这是一个有关黑色时间轴的css3 html5 网站模板" />
<link href="css/styles.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/animation.css" rel="stylesheet">
<link href="css/input.css" rel="stylesheet">
<!-- 返回顶部调用 begin -->
<link href="css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/js.js"></script><link>
<script type="text/javascript" src="jquery.flexText.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->

<script type="text/javascript">

<!--textarea限制字数-->
    function keyUP(t){
        var len = $(t).val().length;
        if(len > 139){
            $(t).val($(t).val().substring(0,140));
        }
    }
    
        function keyDOWN(t){
        var len = $(t).val().length;
        if(len > 2999){
            $(t).val($(t).val().substring(0,3000));
        }
    }

$(function(){
  getType();
  $('.content').flexText();<!--textarea高度自适应-->
});

function getType(){
  $.ajax({
        type : "POST",
        url : "CategoryServlet",
        dataType : "json",
        success : function(data){
        var type = document.getElementById("typeselect");
        var str = "";
        for(i in data){
        str +="<option value =\""+data[i].id+"\">"+data[i].name+"</option>";
        }
        type.innerHTML=str;
        },
        error : function (XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
        },
  });
}
</script>
</head>
<body>
 <c:choose>
   <c:when test="${empty sessionScope.user}">  
    <c:redirect url="Login.jsp"></c:redirect> 
   </c:when>
</c:choose>

<header>
  <nav id="nav">
    <ul>
      <li><a href="index.jsp" >网站首页</a></li>
      <li><a href="LoginServlet?action=TZ" title="个人博客模板">个人博客模板</a></li>
      <c:choose>
      <c:when test="${empty sessionScope.user}">
      <li style="float: right"><a title="JS 实例代码演示">${sessionScope.user.nickname },欢迎光临!</a><a href="javascript:void(0);" id="esc" onclick="esc()" title="退出">退出</a></li>
      </c:when>
      <c:otherwise>
      <li style="float: right"><a title="JS 实例代码演示">登录啊,没素质!<a href="" target="_blank" title="退出">注册啊,老铁!</a></li>
      </c:otherwise>
      </c:choose>
    </ul>
    <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题--> 
  </nav>
</header>
<!--header end-->
<div id="mainbody">

<div class="commentAll">
    <!--评论区域 begin-->
    <div class="reviewArea clearfix" style="width:50%;margin:0 auto">
    <div>&nbsp;</div>
    <div style="width:100px;margin:0 auto"><h1 style="color:white">发表博文</h1></div>
    <div>&nbsp;</div>
    <div>&nbsp;</div>
      <form action="articleServlet?action=publish" method="post">
        <textarea  name="title" id="title" class="content comment-input" placeholder="文章标题&hellip;" onkeyup="keyUP(this)" style="resize:none;;height:20px"></textarea>
        <div>
        <span style="color:white">文章类别：</span><select name="typeselect" id="typeselect" style="background: #444444 0px 0px;font-family: "Helvetica Neue", Helvetica, Arial, sans-serif; font-size: 14px; border: #000000 1px solid; padding: 5px;"></select>
        </div>
        <div>&nbsp;</div>
        <div>
          <textarea  name="content" id="content" class="content comment-input" placeholder="文章内容&hellip;" onkeyup="keyDOWN(this)" style="resize:none;;height:200px"></textarea>
        </div>
        <input type="hideen" name="userid" value="${sessionScope.user.id }" style="display:none"/>
        <div id="wrapper"><input type="submit" id="button2" value="发表" /></div>
      </form>
    </div>
    <!--评论区域 end-->
   
</div>
      
</div>
<!--mainbody end-->
<footer>
  <div class="footer-mid">
    <section class="flickr">
      <h2>摄影作品</h2>
      <ul>
        <li><a href="images/01.jpg" target="_blank"><img src="images/01.jpg"></a></li>
        <li><a href="images/02.jpg" target="_blank"><img src="images/02.jpg"></a></li>
        <li><a href="images/03.jpg" target="_blank"><img src="images/03.jpg"></a></li>
        <li><a href="images/04.jpg" target="_blank"><img src="images/04.jpg"></a></li>
        <li><a href="images/05.jpg" target="_blank"><img src="images/05.jpg"></a></li>
        <li><a href="images/06.jpg" target="_blank"><img src="images/06.jpg"></a></li>
        <li><a href="images/07.jpg" target="_blank"><img src="images/07.jpg"></a></li>
        <li><a href="images/08.jpg" target="_blank"><img src="images/08.jpg"></a></li>
        <li><a href="images/09.jpg" target="_blank"><img src="images/09.jpg"></a></li>
      </ul>
    </section>
  </div>
  <div class="footer-bottom">
    <p>Copyright 2013 Design by <a href="http://www.yangqq.com">DanceSmile</a></p>
  </div>
</footer>
<!-- jQuery仿腾讯回顶部和建议 代码开始 -->
<div id="tbox"> <a id="togbook" href="/e/tool/gbook/?bid=1"></a> <a id="gotop" href="javascript:void(0)"></a> </div>
<!-- 代码结束 -->

</body>
</html>
