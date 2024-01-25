package com.KoreaIT.java.Jsp_AM.dao;

import com.KoreaIT.java.Jsp_AM.util.SecSql;

public class ArticleDao {

	public static SecSql countlist() {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");
		
		return sql;
	}

	public static SecSql list(int itemsInAPage, int limitFrom) {
		
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article AS a");
		sql.append("INNER JOIN `member` AS m");
		sql.append("ON a.writer = m.id");
		sql.append("ORDER BY a.id DESC");
		sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);
		
		return sql;
	}

	public SecSql dowrite(int loginedMemberId, String title, String body) {
		SecSql sql = SecSql.from("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("writer = ?,", loginedMemberId);
		sql.append("title = ?,", title);
		sql.append("`body` = ?;", body);
		
		return sql;
	}

	
	
}
