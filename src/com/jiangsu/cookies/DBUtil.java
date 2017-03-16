package com.jiangsu.cookies;

import java.util.HashMap;
import java.util.Map;

public class DBUtil {

	private static Map<String, Book> books = new HashMap<String, Book>();
	
	static{
		books.put("1", new Book("1", "���μ�", 50, "��ж�"));
		books.put("2", new Book("2", "��������", 60, "�޹���"));
		books.put("3", new Book("3", "��¥��", 70, "��ѩ��"));
		books.put("4", new Book("4", "ˮ䰴�", 80, "ʩ����"));
	}
	
	public static Map<String,Book> findAllBooks(){
		return books;
	}
	
	public static Book findBookById(String id){
		return books.get(id);
	}

}
