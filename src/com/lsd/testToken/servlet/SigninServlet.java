package com.lsd.testToken.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsd.testToken.util.JSONUtil;
import com.lsd.testToken.util.Message;

public class SigninServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SigninServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		String act = request.getParameter("act");
		System.out.println(act);
		Message message = new Message(1,"ok");
		if("signin".equals(act)) {
			Map<String,Object> claims = new HashMap<String,Object>();
			claims.put("token","111523");
			message.setData(claims);
			String json = JSONUtil.object2json(message);
			System.out.println(json);
//			response.setCharacterEncoding("text/html;charset=utf-8");
			response.getWriter().write(json.toString());
		}
		if("set".equals(act)) {
			System.out.println("--------------");
			String token = request.getHeader("Authorization");
			System.out.println("token在这"+token);
			Map<String,Object> claims = new HashMap<String,Object>();
			String json = JSONUtil.object2json(message);
			System.out.println(json);
//			response.setCharacterEncoding("text/html;charset=utf-8");
			response.getWriter().write(json.toString());
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
