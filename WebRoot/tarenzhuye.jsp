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
function laqu(id){
    /*初始化*/
    var counter = 0; /*计数器*/
    var pageStart = 0; /*offset*/
    var pageSize = 5; /*size*/
    var isEnd = false;/*结束标志*/

    getUser(id);
    /*首次加载*/
    getData(pageStart, pageSize,id);
    
    /*监听加载更多*/  
    $(window).scroll(function(){
        if(isEnd == true){
            return;
        }

        // 当滚动到最底部以上100像素时， 加载新内容
        // 核心代码
        if ($(document).height() - $(this).scrollTop() - $(this).height()<1){
            counter ++;
            pageStart = counter * pageSize;
            
            getData(pageStart, pageSize,id);
        }
    });
}
function getUser(id){
$.ajax({
type : "POST",
url : "LoginServlet?action=user",
dataType : "json",
data : {"userid":id},
success : function(data){
str="";
str +=  "<h1>我的名片</h1>"+
      "<p>网名："+data.nickname+"</p>"+
      "<p>职业：Web前端设计师、网页设计</p>"+
      "<p>电话：13662012345</p>"+
      "<p>Email：dancesmiling@qq.com</p>";
      debugger;
      $("#ccc").html(str);
},
error : function(XMLHttpRequest, textStatus, errorThrown){
alert("404");
},
});
}
function getData(offset,size,id){
	$.ajax({
		type : "POST",
		url : "articleServlet?action=all",
		dataType : "json",
        data : {"userId":id},
        success : function(data) {
            var sum = data.length;
             var str = "";
             if(sum - offset < size ){
                size = sum - offset;
            }
             for (var i=offset; i< (offset+size); i++) {  
            var tbody = document.getElementById("tbody_a");
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
         $("#tbody_a").append(str);
        if ( (offset + size) >= sum){
         isEnd = true;//没有更多了
        }
        },
        error : function (XMLHttpRequest, textStatus, errorThrown) {
        alert("您还没有发表任何文章！");
        },
	});
}
</script>
</head>
<body onload="laqu(${sessionScope.taren})">


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
            <li style="float: right"><a title="JS 实例代码演示">${sessionScope.user.nickname },欢迎光临!</a><a href="javascript:void(0);" id="esc" onclick="esc()" title="退出">退出</a></li> 
      </c:otherwise>
      </c:choose>
      </ul>
    <script src="js/silder.js"></script><!--获取当前页导航 高亮显示标题--> 
  </nav>
</header>
<!--header end-->
<div id="mainbody">
  <div class="info">
    <figure> <img src="images/art.jpg"  alt="Panama Hat">
      <figcaption><strong>渡人如渡己，渡已，亦是渡</strong> 当我们被误解时，会花很多时间去辩白。 但没有用，没人愿意听，大家习惯按自己的所闻、理解做出判别，每个人其实都很固执。与其努力且痛苦的试图扭转别人的评判，不如默默承受，给大家多一点时间和空间去了解。而我们省下辩解的功夫，去实现自身更久远的人生价值。其实，渡人如渡己，渡已，亦是渡人。</figcaption>
    </figure>
    <div class="card">
    <div id="ccc"></div>
      <ul class="linkmore">
        <li><a href="/" class="talk" title="发表博文"></a></li>
        <li><a href="/" class="address" title="联系地址"></a></li>
        <li><a href="/" class="email" title="给我写信"></a></li>
        <li><a href="/" class="photos" title="生活照片"></a></li>
        <li><a href="/" class="heart" title="收藏"></a></li>
      </ul>
    </div>
  </div>
  <!--info end-->
  <div class="blank"></div>
  <div class="blogs">
    <ul class="bloglist" id="tbody_a">
    </ul>
    <div class="js-load-more">下划加载更多...</div>
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
