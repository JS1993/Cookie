package com.jiangsu.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiangsu.cookies.Book;
import com.jiangsu.cookies.DBUtil;

public class ShowAllBooks extends HttpServlet {

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
		out.print("本站有以下好书： <br>");
		Map<String,Book> books = DBUtil.findAllBooks();
		for(Map.Entry<String, Book> book:books.entrySet()){
			String url = request.getContextPath()+"/servlet/AddCart?id="+book.getKey()+"'target='_blank'>"+book.getValue().getName();
			out.print("<a href='"+response.encodeURL(url)+"</a><br>");
		}
		String url2 = request.getContextPath()+"/servlet/ShowCart";
		out.print("<a href='"+response.encodeURL(url2)+"'>查看购物车</a>");
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
