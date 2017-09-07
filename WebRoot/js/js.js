function b(){
	h = $(window).height();
	t = $(document).scrollTop();
	if(t > h){
		$('#gotop').show();
	}else{
		$('#gotop').hide();
	}
}

function esc(){
	$.ajax({
		type : "POST",
		url : "LoginEscServlet",
		dataType : "text",
		success : function(data){
			if (data == "success") {
				alert("111");
				window.location.href=window.location.pathname;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("222");
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
	});
}

$(document).ready(function(e) {
	b();

	$('#gotop').click(function(){
		$(document).scrollTop(0);	
	})
	
jichu();
	

});

function jichu(){
	$.ajax({
		type : "POST",
		url : "articleServlet?action=upd",
		dataType : "json",
		data : {"":""},
		success : function(data) {
			var tbody = document.getElementById("lanmu_update");
			var str = "";
			for(i in data){
		    var j = parseInt(i)+parseInt(1);
			str+="<li><span><strong>"+j+"</strong></span><a href=\"articleServlet?action=TZ&article_id="+data[i].articleId+"\">"+data[i].title+"</a></li>";
			}
			tbody.innerHTML="";
			tbody.innerHTML=str;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
	});
	
	$.ajax({
		type : "POST",
		url : "articleServlet?action=hot",
		dataType : "json",
		data : {"":""},
		success : function(data) {
			var tbody = document.getElementById("hot");
			var str = "";
			for(i in data){
		    var j = parseInt(i)+parseInt(1);
			str+="<li><span><a href=\"/\">"+data[i].typename+"</a></span><a href=\"articleServlet?action=TZ&article_id="+data[i].articleId+"\">"+data[i].title+"</a></li>";
			}
			tbody.innerHTML="";
			tbody.innerHTML=str;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
	});
}

$(window).scroll(function(e){
	b();		
})
