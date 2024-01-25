package com.KoreaIT.java.Jsp_AM.service;

import com.KoreaIT.java.Jsp_AM.dao.ArticleDao;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

public class ArticleService {

	private static ArticleDao articleDao;
	
	public static SecSql list(int itemsInAPage, int limitFrom) {
		return articleDao.list(itemsInAPage, limitFrom);
	}

	public static SecSql countlist() {
		return articleDao.countlist();
	}

	public static SecSql dowrite(int loginedMemberId, String title, String body) {
		return articleDao.dowrite(loginedMemberId, body, body);
	}
}
