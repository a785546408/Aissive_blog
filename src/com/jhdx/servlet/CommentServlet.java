package com.jhdx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jhdx.Dao.imp.CommentDaoImp;
import com.jhdx.bean.Comment;
import com.jhdx.filter.TimeFilter;

public class CommentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CommentServlet() {
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
		if ("display".equals(action)) {
			int artid = Integer.parseInt(request.getParameter("articleid"));
			ArrayList<Comment> list = CommentDaoImp.getInstance().SelectCom(artid);
			JSONArray obj = JSONArray.fromObject(list);
			System.out.println(obj);
			out.write(obj.toString());
		} else if("publish_comment".equals(action)) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			String comment = request.getParameter("comment");
			String commenttime = TimeFilter.getDate();
			Comment com = new Comment();
			com.setUserid(userid);
			com.setArticleid(articleid);
			com.setComment(comment);
			com.setCommenttime(commenttime);
			CommentDaoImp.getInstance().InsertMajorComment(com);
			JSONObject obj = JSONObject.fromObject(com);
			out.write(obj.toString());
		} else if("publish_comment2".equals(action)) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			int farcommentid = Integer.parseInt(request.getParameter("farcommentid"));
			String comment = request.getParameter("comment");
			String commenttime = TimeFilter.getDate();
			Comment com = new Comment();
			com.setUserid(userid);
			com.setArticleid(articleid);
			com.setComment(comment);
			com.setCommenttime(commenttime);
			com.setFarcommentid(farcommentid);
			CommentDaoImp.getInstance().InsertMajorComment(com);
			JSONObject obj = JSONObject.fromObject(com);
			out.write(obj.toString());
		}
		
		out.flush();
		out.close();
	}

}
