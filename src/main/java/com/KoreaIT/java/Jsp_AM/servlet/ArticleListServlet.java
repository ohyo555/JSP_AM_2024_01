package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.exception.SQLErrorException;
import com.KoreaIT.java.Jsp_AM.service.ArticleService;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;
import com.KoreaIT.java.Jsp_AM.dao.ArticleDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

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

			int page = 1;

			if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			int itemsInAPage = 10;
			int limitFrom = (page - 1) * itemsInAPage;
			
			SecSql countsql = ArticleService.countlist();
			
			int totalCnt = DBUtil.selectRowIntValue(conn, countsql);
			int totalPage = (int) Math.ceil(totalCnt / (double) itemsInAPage);			

			SecSql listsql = ArticleService.list(itemsInAPage, limitFrom);
			
			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, listsql);

			request.setAttribute("page", page);
			request.setAttribute("totalCnt", totalCnt);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("itemsInAPage", itemsInAPage);
			request.setAttribute("articleRows", articleRows);
			response.getWriter().append(articleRows.toString());
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

		} catch (SQLException e) {
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