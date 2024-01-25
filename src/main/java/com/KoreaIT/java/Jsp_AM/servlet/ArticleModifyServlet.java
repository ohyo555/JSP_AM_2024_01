package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.exception.SQLErrorException;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB연결
		try {
			Class.forName(Config.getDbDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			HttpSession session = request.getSession();

			int id = Integer.parseInt(request.getParameter("id"));
			int writer = Integer.parseInt(request.getParameter("writer"));
		
			System.out.println(writer);
			
			if (session.getAttribute("loginedMemberId") != null) {
				
				int loginedMemberId = (int) session.getAttribute("loginedMemberId");
				Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
//				System.out.println(loginedMemberId);
			
				if (writer == loginedMemberId) {
					
					SecSql sql = SecSql.from("SELECT *");
					sql.append("FROM article");
					sql.append("WHERE id = ?;", id);
					
					Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

					request.setAttribute("articleRow", articleRow);
					request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

				} else {
					response.getWriter()
					.append(String.format("<script>alert('수정 및 삭제는 작성자만 가능합니다.'); location.replace('list');</script>"));
				}
				
			} else {
				response.getWriter()
				.append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('list');</script>"));
			}
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (SQLErrorException e) {
				e.getOrigin().printStackTrace();
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}