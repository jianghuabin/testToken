package com.lsd.testToken.servlet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsd.testToken.util.Auth;
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

		this.doPost(request, response);
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");
		//System.out.println(act);
		Message message = new Message(1,"ok");
		if("signin".equals(act)) {
			Map<String,Object> claims = new HashMap<String,Object>();
			//System.out.println(request.getParameter("username"));
			if(!"admin".equals(request.getParameter("username"))){
				message = new Message(-1, "用户名错误");
				String json = JSONUtil.object2json(message);
				System.out.println(json);
//				response.setCharacterEncoding("text/html;charset=utf-8");
				response.getWriter().write(json.toString());
			}
			else if(!"111111".equals(request.getParameter("password")))
			{
				//System.out.println(request.getParameter("passwrod"));
				message = new Message(-1, "密码错误");
				String json = JSONUtil.object2json(message);
				System.out.println(json);
//				response.setCharacterEncoding("text/html;charset=utf-8");
				response.getWriter().write(json.toString());
			}
			else
			{
				claims.put("id", 314190001);
				claims.put("username", request.getParameter("username"));
				claims.put("password",request.getParameter("passwrod"));
				claims.put("role","teacher");
				
				String s = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Auth.key).setExpiration(new Date(System.currentTimeMillis()+Auth.expire)).compact();
				claims.put("token",s);
				message.setData(claims);
				claims.put("token",request.getParameter("passwrod"));
				message.setData(claims);
				String json = JSONUtil.object2json(message);
				System.out.println(json);
//				response.setCharacterEncoding("text/html;charset=utf-8");
				response.getWriter().write(json.toString());
			}
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
