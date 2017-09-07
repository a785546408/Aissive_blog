package com.jhdx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhdx.Dao.imp.UserDaoImp;
import com.jhdx.bean.User;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class RegesiterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegesiterServlet() {
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
        SmartUpload su = new SmartUpload(); //��������
        su.initialize(getServletConfig(),request,response);  //��ʼ������
        su.setCharset("utf-8");    //���ñ����ʽ
        try {
			su.upload();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      //׼����ʼ�ϴ�
        Files files = su.getFiles();   //��ȡ���е��ļ�
        File file = files.getFile(0);     //��ȡ��һ���ļ�
        String filePath = "upload//"+file.getFileName();
        try {
			file.saveAs(filePath);
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String username = su.getRequest().getParameter("blog_username");
        String password = su.getRequest().getParameter("blog_password");
        String nickname = su.getRequest().getParameter("blog_nickname");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setImgurl(filePath);
        UserDaoImp.getInstance().InsertUser(user);
        response.sendRedirect("Login.jsp");
		out.flush();
		out.close();
	}

}
