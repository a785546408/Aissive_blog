package com.jhdx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.test.JSONAssert;

import com.jhdx.Dao.imp.ArticleDaoImp;
import com.jhdx.Dao.imp.CategoryDaoImp;
import com.jhdx.bean.Article;
import com.jhdx.bean.Category;
import com.jhdx.bean.User;
import com.jhdx.filter.TimeFilter;

public class articleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public articleServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if ("all".equals(action)) {
			int id = Integer.parseInt(request.getParameter("userId").toString());
			ArrayList<Article> list = ArticleDaoImp.getInstance().SelectAll(id);
			JSONArray obj = JSONArray.fromObject(list);
			out.write(obj.toString());
		} else if ("one".equals(action)) {
			int id = Integer.parseInt(request.getParameter("articleId").toString());
			Article at = ArticleDaoImp.getInstance().SelectOne(id);
			JSONObject obj = JSONObject.fromObject(at);
			out.write(obj.toString());
		} else if ("TZ".equals(action)) {
			int articleId = Integer.parseInt(request.getParameter("article_id").toString());
			request.getSession().setAttribute("articleId", articleId);
			response.sendRedirect("ArticleInfo.jsp");
		} else if ("Type".equals(action)) {
			ArrayList<Category> list = CategoryDaoImp.getInstance().SelectAll();
			JSONArray obj = JSONArray.fromObject(list);
			out.write(obj.toString());
		} else if ("Article".equals(action)) {
			int id = Integer.parseInt(request.getParameter("category_id").toString());
			ArrayList<Article> list = ArticleDaoImp.getInstance().SelectTypeArticle(id);
			JSONArray obj = JSONArray.fromObject(list);
			out.write(obj.toString());
		} else if ("upd".equals(action)) {
			ArrayList<Article> list = ArticleDaoImp.getInstance().SelectTop9Time();
			JSONArray obj = JSONArray.fromObject(list);
			out.write(obj.toString());
		} else if ("hot".equals(action)){
			ArrayList<Article> list = ArticleDaoImp.getInstance().SelectHot();
			JSONArray obj = JSONArray.fromObject(list);
			out.write(obj.toString());
		} else if ("publish".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userid = Integer.parseInt(request.getParameter("userid").toString());
			int categoryid = Integer.parseInt(request.getParameter("typeselect").toString());
			String createdTime = TimeFilter.getDate();
			String updatedTime = TimeFilter.getDate();
			int status = 1;
			Article art = new Article();
			art.setUserId(userid);
			art.setCategoryId(categoryid);
			art.setTitle(title);
			art.setContent(content);
			art.setStatus(status);
			art.setCreateTime(createdTime);
			art.setUpdateTime(updatedTime);
			ArticleDaoImp.getInstance().InsertArt(art);
			response.sendRedirect("gerenzhuye.jsp");
		} else if ("allart".equals(action)) {
			String keywords = request.getParameter("search");
			request.getSession().setAttribute("search", keywords);
			ArticleDaoImp dao = ArticleDaoImp.getInstance();
			keywords = request.getSession().getAttribute("search").toString();
			System.out.println(request.getSession().getAttribute("search"));
			ArrayList<Article> list = dao.SelectFYType(keywords, 1, 5);
			request.getSession().setAttribute("ResultArt", list);
			int totalpage = dao.NUMLIKE(keywords);
			request.getSession().setAttribute("totalpage", (int)Math.ceil(totalpage/5.0));
			request.getSession().setAttribute("pageindex", 1);
			response.sendRedirect("ResultArt.jsp");
		} else if ("t".equals(action)) {
			ArticleDaoImp dao = ArticleDaoImp.getInstance();
			String keywords = request.getSession().getAttribute("search").toString();
			if (Integer.parseInt(request.getParameter("pageindex"))<1) {
				request.getSession().setAttribute("pageindex", 1);
				ArrayList<Article> list = dao.SelectFYType(keywords,Integer.parseInt(request.getSession().getAttribute("pageindex").toString()),5);
				request.getSession().setAttribute("ResultArt", list);
				response.sendRedirect("ResultArt.jsp");
			} else if (Integer.parseInt(request.getParameter("pageindex"))>Integer.parseInt(request.getSession().getAttribute("totalpage").toString())) {
				request.getSession().setAttribute("pageindex", Integer.parseInt(request.getSession().getAttribute("totalpage").toString()));
				ArrayList<Article> list = dao.SelectFYType(keywords,Integer.parseInt(request.getSession().getAttribute("pageindex").toString()),5);
				request.getSession().setAttribute("ResultArt", list);
				response.sendRedirect("ResultArt.jsp");
			}else{
				request.getSession().setAttribute("pageindex", Integer.parseInt(request.getParameter("pageindex")));
				ArrayList<Article> list = dao.SelectFYType(keywords,Integer.parseInt(request.getSession().getAttribute("pageindex").toString()),5);
				request.getSession().setAttribute("ResultArt", list);
				response.sendRedirect("ResultArt.jsp");
			}
	} else if ("viewcount".equals(action)){
		    int id = Integer.parseInt(request.getParameter("articleId"));
		    ArticleDaoImp.getInstance().UpdateViewCount(id);
		    JSONObject obj = JSONObject.fromObject(id);
		    out.write(obj.toString());
	} else if ("user".equals(action)){
		    boolean flag = false;
		    if (request.getSession().getAttribute("user") != null) {
				flag = true;
			}
		    out.write(flag+"");
	} else if ("cang".equals(action)){
		int articleid = Integer.parseInt(request.getParameter("articleid"));
		int userid = ((User)request.getSession().getAttribute("user")).getId();
		String Time = TimeFilter.getDate();
		ArticleDaoImp.getInstance().SCang(userid, articleid, Time);
		out.write("success");
	} else if ("collection".equals(action)) {
		int userid = Integer.parseInt(request.getParameter("userId"));
		ArrayList<Article> list = ArticleDaoImp.getInstance().collectionList(userid);
		JSONArray obj = JSONArray.fromObject(list);
		out.write(obj.toString());
	} else if ("del".equals(action)){
		int id = Integer.parseInt(request.getParameter("articleid"));
		ArticleDaoImp.getInstance().delArt(id);
		out.write("success");
	}

		out.flush();
		out.close();

	}
}
