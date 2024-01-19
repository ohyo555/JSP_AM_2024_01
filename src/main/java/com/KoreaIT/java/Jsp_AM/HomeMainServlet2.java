package com.KoreaIT.java.Jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/main2")	
public class HomeMainServlet2 extends HttpServlet {

	private String i;
	private CharSequence Gugudan;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append(gugudan(i)).append(request.getContextPath());
	}

	private CharSequence gugudan(String i) {
		
		String dan = i;
		
//		System.out.printf("==%d단==",i);
		for(int a = 1; a <= 9; a++) {
			
			int answer = Integer.parseInt(dan) * a;
			String num = Integer.toString(a);
			String Gugudan = dan + " * " + num + " = " + answer;
//			String[] g = 
//			System.out.printf("%d * %d = %d",dan,a,answer);
			
		}
		return Gugudan;
	}


}
