<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<base href="<%=basePath %>">
<head>
<title>view-黑色时间轴个人博客模板</title>
<meta name="keywords" content="黑色模板,个人网站模板,个人博客模板,博客模板,css3,html5,网站模板" />
<meta name="description" content="这是一个有关黑色时间轴的css3 html5 网站模板" />
<link href="css/styles.css" rel="stylesheet">
<link href="css/view.css" rel="stylesheet">


<!-- 返回顶部调用 begin -->
<link href="css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="jquery.flexText.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->

		
<script type="text/javascript">  
function onloadArticle(id){

	               $.ajax({
							type : "POST",
							url : "articleServlet?action=viewcount",
							dataType : "json",
							data : {"articleId" : id},
							success : function(data){
								$.ajax({
									type : "POST",
									url : "articleServlet?action=one",
									dataType : "json",
									data : {
										"articleId" : id
									},
									success : function(data) {
										var tbody = document
												.getElementById("index_view");
										var str = "";
										str += "<h2 class=\"t_nav\"><a>网站首页</a><a>"
												+ data.typename
												+ "</a></h2>"
												+ "<h1 class=\"c_titile\">"
												+ data.title
												+ "</h1>"
												+ "<p class=\"box\">发布时间："
												+ data.createTime.substring(0,10)
												+ "<span>编辑："
												+ "<a href=\"LoginServlet?action=taren&userid="+data.userId+"\">"+data.nickname+"</a>"
												+ "</span>阅读（"
												+ data.viewCount
												+ "）<a href=\"javascipt:void(0);\" style=\"color:pink;float:right\" onclick=\"cang("+data.articleId+")\">收藏♥</a></p>"
												+ "<ul id=\"articleInfo\">"
												+ "<p><img src=\"images/post1.jpg\"></p>"
												+ "<p>"
												+ data.content
												+ "</p>"
												+ "</ul>"
												+ "<div class=\"otherlink\">"
												+ "<h2>相关文章</h2>"
												+ "<ul>"
												+ "<li><a href=\"/newstalk/mood/2013-07-24/518.html\" title=\"我希望我的爱情是这样的\">我希望我的爱情是这样的有种情谊，不是爱情，也算不得友情有种情谊，不是爱情，也算不得友情</a></li>"
												+ "<li><a href=\"/newstalk/mood/2013-07-02/335.html\" title=\"有种情谊，不是爱情，也算不得友情\">有种情谊，不是爱情，也算不得友情有种情谊，不是爱情，也算不得友情有种情谊，不是爱情，也算不得友情</a></li>"
												+ "<li><a href=\"/newstalk/mood/2013-07-01/329.html\" title=\"世上最美好的爱情\">世上最美好的爱情</a></li>"
												+ "<li><a href=\"/news/read/2013-06-11/213.html\" title=\"爱情没有永远，地老天荒也走不完\">爱情没有永远，地老天荒也走不完</a></li>"
												+ "<li><a href=\"/news/s/2013-06-06/24.html\" title=\"爱情的背叛者\">爱情的背叛者</a></li>"
												+ "</ul>" + "</div>";
										tbody.innerHTML = str;
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert(XMLHttpRequest.status);
										alert(XMLHttpRequest.readyState);
										alert(textStatus);
									},
								});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.status);
						alert(XMLHttpRequest.readyState);
						alert(textStatus);
					},
				});

		display(id);
	}
	
	function cang(id){
	   $.ajax({
	   type : "POST",
	   url : "articleServlet?action=user",
	   dataTypr : "text",
	   success : function(data){
           if(data == "false"){
           alert("登录啊");
           }else{
           $.ajax({
              type : "POST",
	          url : "articleServlet?action=cang",
	          dataTypr : "text",
	          data : {"articleid":id},
	          success : function(data){
	           if(data == "success"){
	           alert("收藏成功!");
	           }
	          },
	          error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				}
           });
           }

	   },
	   error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
	   }
	   });
}

	function display(id) {
		$.ajax({ //拉取评论并显示，但是有个不大不小的bug，默认子评论在数据库中的顺序必须在父评论之后，虽然实际情况也应该是这样
					type : "POST",
					url : "CommentServlet?action=display",
					dataType : "json",
					data : {
						"articleid" : id
					},
					success : function(data) {
						if (data != null) {
							$("#comment").html("");
							var str = "";
							var str_c = "";
							for (i in data) {
								if (data[i].farcommentid == 0) {
									str += "<div id=\"comment"+data[i].commentid+"\">"
											+ "<div class=\"line\"></div>"
											+ "<dl>"
											+ "<dt><img src=\"images/s8.jpg\">"
											+ "<dt>"
											+ "<dd>"
											+ data[i].nickname
											+ "<time>"
											+ data[i].commenttime
											+ "</time>"
											+ "<a href=\"javascript:void(0);\" class=\"comment-show\" style=\"float:right\" onclick=\"reply('"
											+ data[i].nickname
											+ "',"
											+ data[i].userid
											+ ","
											+ data[i].commentid
											+ ")\">回复</a>"
											+ "</dd>"
											+ "<dd>在 <a href=\"http://www.yangqq.com/jstt/bj/2013-07-28/530.html#comments\" class=\"title\">"
											+ data[i].title
											+ "</a>中评论：</dd>"
											+ "<dd>"
											+ data[i].comment
											+ "</dd>" + "</dl></div>";
									$("#comment").append(str);
									str = "";
								} else {
									str_c += "<div id=\"comment"+data[i].commentid+"\">"
											+ "<dl style=\"margin-left:72px!important;\">"
											+ "<dt><img src=\"images/s7.jpg\">"
											+ "<dt>"
											+ "<dd>"
											+ data[i].nickname
											+ "<time>"
											+ data[i].commenttime
											+ "</time>"	
											+ "<a href=\"javascript:void(0);\" class=\"comment-show\" style=\"float:right\" onclick=\"reply('"
											+ data[i].nickname
											+ "',"
											+ data[i].userid
											+ ","
											+ data[i].commentid
											+ ")\">回复</a>"
											+ "</dd>"
											+ "<dd>在 <a href=\"http://www.yangqq.com/jstt/bj/2013-07-28/530.html#comments\" class=\"title\">"
											+ data[i].title
											+ "</a>中评论：</dd>"
											+ "<dd>"
											+ data[i].comment
											+ "</dd>" + "</dl></div>";
									$("#comment" + data[i].farcommentid + "")
											.append(str_c);
									str_c = "";
								}
							}

							str += "<div class=\"line\"></div>";
							$("#comment").append(str);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.status);
						alert(XMLHttpRequest.readyState);
						alert(textStatus);
					},
				});
	}

	function publish1(userid, artid) {
		var comment = $("#text_comment").val();
		$.ajax({
			type : "POST",
			url : "CommentServlet?action=publish_comment",
			dataType : "json",
			data : {
				"articleid" : artid,
				"userid" : userid,
				"comment" : comment
			},
			success : function(data) {
				$("#text_comment").val("");
				alert("发表成功!");
				display(artid);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},

		});
	}

	function publish2(userid, artid, commentid) {
		var comment = $("#text_comment2").val();
		$.ajax({
			type : "POST",
			url : "CommentServlet?action=publish_comment2",
			dataType : "json",
			data : {
				"articleid" : artid,
				"userid" : userid,
				"comment" : comment,
				"farcommentid" : commentid
			},
			success : function(data) {
				Hide();
				alert("发表成功!");
				display(artid);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},

		});
	}
</script>
<script type="text/javascript">
function reply(nickname,userid,commentid){
	var str = "<div style=\"float:left\" id=\"xiaopinglun\">"
				+ "<textarea  name=\"title\" id=\"text_comment2\" class=\"content comment-input\" placeholder=\"@"+nickname+"&hellip;\" onkeyup=\"keyUP(this)\" style=\"resize:none;;height:150px;width:400px\"></textarea>"
				+ "<input type=\"button\" id=\"btn_comment2\" value=\"发表评论\"  onclick=\"publish2(${sessionScope.user.id},${sessionScope.articleId},"+commentid+")\"/>"
				+ "<a href=\"javascript:void(0);\" style=\"color:red;font-size:30px\" onclick=\"Hide()\">&nbsp;x</a>"
				+ "</div>";
    $("#comment"+commentid+"").append(str);
	}
	
function reply2(nickname,userid,farcommentid,commentid){
	var str = "<div style=\"float:left\" id=\"xiaopinglun\">"
				+ "<textarea  name=\"title\" id=\"text_comment2\" class=\"content comment-input\" placeholder=\"@"+nickname+"&hellip;\" onkeyup=\"keyUP(this)\" style=\"resize:none;;height:150px;width:400px\"></textarea>"
				+ "<input type=\"button\" id=\"btn_comment2\" value=\"发表评论\"  onclick=\"publish2(${sessionScope.user.id},${sessionScope.articleId},"+commentid+")\"/>"
				+ "<a href=\"javascript:void(0);\" style=\"color:red;font-size:30px\" onclick=\"Hide()\">&nbsp;x</a>"
				+ "</div>";
    $("#comment"+farcommentid+"").append(str);
	}

function Hide(){
$("#xiaopinglun").remove();
}	

</script>
</head>
<body onload="onloadArticle(${sessionScope.articleId})">

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
  <div class="blogs">
    <div id="index_view">
    </div>
    <aside>
      <div class="search">
        <form  method="post" action="articleServlet?action=allart"> 
          <span class="searchform"><input type="text" name="search" id ="search" ></span>
          <input type="submit" style="width:40px;height:30px;font-family:楷体 ;color:red;background:black;border:0px" value="搜索"/>
        </form>
      </div>
      <div class="sunnav">
        <ul>
          <li><a href="/web/" target="_blank" title="">这里是文章分类未实现</a></li>
          <li><a href="/newshtml5/" target="_blank" title="">HTML5 / CSS3</a></li>
          <li><a href="/jstt/" target="_blank" title="">技术探讨</a></li>
          <li><a href="/news/s/" target="_blank" title="">慢生活</a></li>
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
      <div class="visitors">
      <c:choose>
        <c:when test="${empty sessionScope.user }">
                     <h2>没登录还想评论?想多了把，兄弟！</h2>
                     <h2 class="t_nav"><a href="Login.jsp">登录</a></h2>
        </c:when>
        <c:otherwise>
        <div style="float:left">
        <textarea  name="title" id="text_comment" class="content comment-input" placeholder="发表评论&hellip;" onkeyup="keyUP(this)" style="resize:none;;height:150px;width:600px"></textarea>
        <input type="button" id="btn_comment1" value="发表评论"  onclick="publish1(${sessionScope.user.id},${sessionScope.articleId})"/>
        </div>
              <h2>最新评论</h2>
              <div id="comment"></div>
        </c:otherwise>
      </c:choose>
    </div>
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