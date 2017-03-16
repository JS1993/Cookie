package com.jiangsu.cookies;

import java.util.HashMap;
import java.util.Map;

public class DBUtil {

	private static Map<String, Book> books = new HashMap<String, Book>();
	
	static{
		books.put("1", new Book("1", "西游记", 50, "吴承恩"));
		books.put("2", new Book("2", "三国演义", 60, "罗贯中"));
		books.put("3", new Book("3", "红楼梦", 70, "曹雪芹"));
		books.put("4", new Book("4", "水浒传", 80, "施耐庵"));
	}
	
	public static Map<String,Book> findAllBooks(){
		return books;
	}
	
	public static Book findBookById(String id){
		return books.get(id);
	}

}
