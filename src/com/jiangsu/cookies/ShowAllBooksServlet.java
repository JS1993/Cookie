package com.jiangsu.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllBooksServlet extends HttpServlet {

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
		
		out.write("本站有以下好书：<br>");
		
		Map<String,Book> books = DBUtil.findAllBooks();
		for(Map.Entry<String, Book> book:books.entrySet()){
			String url =request.getContextPath()+"/servlet/ShowBookDetail?id="+book.getKey();
			out.write("<a href='"+response.encodeURL(url)+"' target='_blank'>"+book.getValue().getName()+"</a><br>");
		}
		
		out.write("<hr>您最近浏览过的书有：<br>");
		Cookie[] cookies = request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){  //判断是否有historyBookId的cookie
			String value = cookies[i].getValue();  //1-2-3
			String[] ids = value.split("-");
			for(int j=0;j<ids.length;j++){
				Book book = DBUtil.findBookById(ids[j]);  //根据id得到书
				out.print(book.getName()+"<br>");
			}
		}
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
