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
<link href="css/animation.css" rel="stylesheet">
<!-- 返回顶部调用 begin -->
<link href="css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<script type="text/javascript">
function laqucang(id){
	$.ajax({
		type : "POST",
		url : "articleServlet?action=collection",
		dataType : "json",
        data : {"userId":id},
        success : function(data) {
             var str = "";
             for (i in data) {  
            str += "<li>"+
            "<div class=\"arrow_box\">"+
            "<div class=\"ti\"></div>"+
            "<div class=\"ci\"></div>"+
            "<h2 class=\"title\">"+"<a href=\"articleServlet?action=TZ&article_id="+data[i].articleId+"\">"+data[i].title+"</a></h2>"+
            "<ul class=\"textinfo\">"+
              "<img src=\"images/s1.jpg\">"+
              "<p>"+ data[i].content.substring(0,140)+"...</p>"+
            "</ul>"+
            "<ul class=\"details\">"+
              "<li class=\"likes\"><a href=\"#\">10</a></li>"+
              "<li class=\"comments\"><a href=\"#\">34</a></li>"+
              "<li class=\"icon-time\"><a href=\"#\">2013-8-7</a></li>"+
            "</ul>"+
          "</div>"+
        "</li> ";
        }  
         $("#tbody_a").html(str);
        },
        error : function (XMLHttpRequest, textStatus, errorThrown) {
        alert("您还没有收藏任何文章！");
        },
	});
}
</script>
</head>
<body onload="laqucang(${sessionScope.user.id})">
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
      <li style="float: right"><a title="JS 实例代码演示">${sessionScope.user.nickname },欢迎光临!</a><a href="javascript:void(0);" id="esc"  onclick="esc()" title="退出">退出</a></li>
    </ul>
    <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题--> 
  </nav>
</header>
<!--header end-->
<div id="mainbody">
  <div class="blank"></div>
  <div class="blogs">
    <ul class="bloglist" id="tbody_a">
    </ul>
    <aside>
      <div class="tuijian">
        <h2>栏目更新</h2>
          <ol id="lanmu_update">
        </ol>
      </div>
      <div class="toppic">
        <h2>图文并茂</h2>
        <ul>
          <li><a href="/"><img src="images/k01.jpg">腐女不可怕，就怕腐女会画画！
            <p>伤不起</p>
            </a></li>
          <li><a href="/"><img src="images/k02.jpg">问前任，你还爱我吗？无限戳中泪点~
            <p>感兴趣</p>
            </a></li>
          <li><a href="/"><img src="images/k03.jpg">世上所谓幸福，就是一个笨蛋遇到一个傻瓜。
            <p>喜欢</p>
            </a></li>
        </ul>
      </div>
      <div class="clicks">
        <h2>热门点击</h2>
        <ol id="hot">
        </ol>
      </div>
      <div class="search">
        <form  method="post" action="articleServlet?action=allart"> 
          <span class="searchform"><input type="text" name="search" id ="search" ></span>
          <input type="submit" style="width:40px;height:30px;font-family:楷体 ;color:red;background:black;border:0px" value="搜索"/>
        </form>
      </div>
      <div class="viny">
        <dl>
          <dt class="art"><img src="images/JC.png" alt="专辑"></dt>
          <dd class="icon-song"><span></span>说散就散</dd>
          <dd class="icon-artist"><span></span>歌手：JC</dd>
          <dd class="icon-album"><span></span>所属专辑：《说散就散》</dd>
          <dd class="icon-like"><span></span><a href="/">喜欢</a></dd>
          <dd class="music">
            <audio src="images/nf.mp3" controls></audio>
          </dd>
          <!--也可以添加loop属性 音频加载到末尾时，会重新播放-->
        </dl>
      </div>
    </aside>
  </div>
  <!--blogs end--> 
      
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
