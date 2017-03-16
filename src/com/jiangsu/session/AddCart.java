package com.jiangsu.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jiangsu.cookies.Book;
import com.jiangsu.cookies.DBUtil;

public class AddCart extends HttpServlet {

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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		Book book = DBUtil.findBookById(id);
		
		//从session中取出list(购物车)
		List<Book> list = (List<Book>)request.getSession().getAttribute("cart");
		if(list==null){
			list = new ArrayList<Book>();
		}
		list.add(book);
		session.setAttribute("cart", list);
		
		out.print("购买成功！2秒后返回");
		String url = request.getContextPath()+"/servlet/ShowAllBooksx";
		response.setHeader("refresh", "2;url="+response.encodeURL(url)));
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

		doGet(request, response);
	}
}
