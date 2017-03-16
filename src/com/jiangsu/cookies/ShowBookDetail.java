package com.jiangsu.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBookDetail extends HttpServlet {


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
		
		//得到图书信息
		String id = request.getParameter("id");
		Book book = DBUtil.findBookById(id);
		
		//输出图书信息
		PrintWriter out = response.getWriter();
		out.write(book.toString());
		
		//设置Cookie
		String historyBookId = organizedId(id,request);
		Cookie ck = new Cookie("historyBookId",historyBookId);
		ck.setPath("/");
		ck.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(ck);
	}

	/**
	 * 客户端cookie                  showAllBooks    showBookDetail
	 * 无cookie                          1          historyBookID=1
	 * 有cookie但无historyBookId          1          historyBookID=1
	 * 有cookie，有historyBookId          2          historyBookID=2-1
	 * historyBookID=1-2                 2         historyBookID=2-1
	 * historyBookID=1-2-3              2          historyBookID=2-1-3
	 * historyBookID=1-2-3              4          historyBookID=4-1-2
	 */
	private String organizedId(String id, HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies==null){
			return id;
		}
		
		Cookie historyBook = null;
		
		//查找有没有name叫做historyBookId的cookie
		for(int i = 0;i<cookies.length;i++){
			if("historyBookId".equals(cookies[i].getName())){
				historyBook = cookies[i];
			}
		}
		
		//如果没有historyBookId的cookie，还返回id
		if(historyBook==null){
			return id;
		}
		
		//得到历史集合
		String value = historyBook.getValue();//2-1-3
		String[] values = value.split("-");
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(values));
		
		if(list.size()<3){
			if(list.contains(id)){
				list.remove(id);   //如果包含当前的id则删除
			}
		}else{
			if(list.contains(id)){
				list.remove(id);
			}else{ //有三本书的id 删除掉最后一本
				list.removeLast();
			}
		}
		
		list.addFirst(id);//最新的书的id放在最前面
		
		//拼接cookie字符串
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i<list.size();i++){
			if(i>0){
				sb.append("-");
			}
			sb.append(list.get(i));
		}
		
		
		return sb.toString();
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
