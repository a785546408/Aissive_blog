<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
<base href="<%=basePath %>">
<title>view-黑色时间轴个人博客模板</title>
<meta name="keywords" content="黑色模板,个人网站模板,个人博客模板,博客模板,css3,html5,网站模板" />
<meta name="description" content="这是一个有关黑色时间轴的css3 html5 网站模板" />
<link href="css/styles.css" rel="stylesheet">
<link href="css/view.css" rel="stylesheet">
<link href="css/pagination.css" rel="stylesheet">
<!-- 返回顶部调用 begin -->
<link href="css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->

</head>
<body>
    <c:if test="${empty sessionScope.ResultArt}" var="result">
       <c:redirect url="articleServlet?action=allart"></c:redirect>
    </c:if>
<header>
  <nav id="nav">
    <ul>
      <li><a href="index.jsp" >网站首页</a></li>
      <li><a href="LoginServlet?action=TZ" title="个人博客模板">个人博客模板</a></li>
      <c:choose>
      <c:when test="${empty sessionScope.user}">
            <li style="float: right"><a title="JS 实例代码演示" href="Login.jsp">登录啊,没素质!<a href="Regesiter.jsp" title="退出">注册啊,老铁!</a></li>
      </c:when>
      <c:otherwise>
            <li style="float: right"><a title="JS 实例代码演示">${sessionScope.user.nickname },欢迎光临!</a><a id="esc" onclick="esc()" href="javascript:void(0);" title="退出">退出</a></li> 
      </c:otherwise>
      </c:choose>
    </ul>
    <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题--> 
  </nav>
</header>
<!--header end-->
<div id="mainbody">
  <div class="blogs">
    <div class="newlist">
  <h2><span>
      <a href="/jstt/div/">div+css</a>

      <a href="/jstt/bj/">心得笔记</a>

      <a href="/jstt/asp/">ASP</a>

      <a href="http://www.ip3q.com/">IP查询</a>

      <a href="/news/jsex/">JS经典实例</a>

      <a href="/jstt/html5/">html5资讯</a>
</span>您当前的位置：<a href="/index.html">首页</a>&nbsp;>&nbsp;<a href="/jstt/">技术探讨</a></h2>
     <ul id="Result"> 
     <c:forEach items="${sessionScope.ResultArt}" var="gg" varStatus="j">
     <p class="ptit"><b><a href="articleServlet?action=TZ&article_id=${gg.articleId }" title=" li列表在网页中常见应用例举（二）"> ${gg.title }</a></b></p>
     <p class="ptime">发布时间：${gg.createTime } 作者：${gg.nickname }  分类：${gg.typename } </p>
     <a href="articleServlet?action=TZ&article_id=${gg.articleId }" title=" li列表在网页中常见应用例举（二）"><img src="images/s${j.count}.jpg" class="imgdow" alt=" li列表在网页中常见应用例举（二）"></a> <p class="pcon">${fn:substring(gg.content,0,200)} <a href="articleServlet?action=TZ&article_id=${gg.articleId }"  title=" li列表在网页中常见应用例举（二）">详细信息</a></P>
     <div class="line"></div>
     </c:forEach>
     </ul> 
     <a href="articleServlet?action=t&pageindex=1">首页</a> <a href="articleServlet?action=t&pageindex=${pageindex-1} ">上一页</a> ${pageindex }/${totalpage } <a href="articleServlet?action=t&pageindex=${pageindex+1 }">下一页</a> <a href="articleServlet?action=t&pageindex=${totalpage }">尾页</a> 
</div> 

    <!--bloglist end-->
    <aside>
      <div class="search">
        <form  method="post" action="articleServlet?action=allart"> 
          <span class="searchform"><input type="text" name="search" id ="search" ></span>
          <input type="submit" style="width:40px;height:30px;font-family:楷体 ;color:red;background:black;border:0px" value="搜索"/>
        </form>
      </div>
      <div class="sunnav">
        <ul>
          <li><a href="/web/" target="_blank" title="网站建设">网站建设</a></li>
          <li><a href="/newshtml5/" target="_blank" title="HTML5 / CSS3">HTML5 / CSS3</a></li>
          <li><a href="/jstt/" target="_blank" title="技术探讨">技术探讨</a></li>
          <li><a href="/news/s/" target="_blank" title="慢生活">慢生活</a></li>
        </ul>
      </div>
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