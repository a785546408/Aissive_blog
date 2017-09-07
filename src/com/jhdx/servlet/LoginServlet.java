package com.jhdx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.jhdx.Dao.imp.UserDaoImp;
import com.jhdx.bean.User;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
		if ("YZ".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			boolean flag = UserDaoImp.getInstance().Login(user);
			user = UserDaoImp.getInstance().GetUser(user);
			if (flag) {
				request.getSession().setAttribute("user", user);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("flag", flag);
			JSONObject obj = JSONObject.fromObject(map);
			out.write(obj.toString());
		} else if("taren".equals(action)) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			request.getSession().setAttribute("taren", userid);
			response.sendRedirect("tarenzhuye.jsp");
		} else if("user".equals(action)){
			int userid = Integer.parseInt(request.getParameter("userid"));
			User user = UserDaoImp.getInstance().getuser(userid);
			JSONObject obj = JSONObject.fromObject(user);
			out.write(obj.toString());
		} else if("TZ".equals(action)){
			if (request.getSession().getAttribute("user") != null) {
				response.sendRedirect("gerenzhuye.jsp");
			}else {
				response.sendRedirect("Login.jsp");
			}
		}
		out.flush();
		out.close();
	}

}
