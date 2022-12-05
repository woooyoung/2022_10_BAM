package com.KoreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.util.Util;

public class ArticleController {

	private List<Article> articles;
	private Scanner sc;

	public ArticleController(List<Article> articles, Scanner sc) {
		this.sc = sc;
		this.articles = articles;
	}

	public void doWrite() {
		int id = articles.size() + 1;
		String regDate = Util.getTimeAndDateStr();
		String updateDate = regDate;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	public void showList() {

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}
		System.out.println("번호     /    제목      /     조회");
		String tmpTitle = null;
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);
			if (article.title.length() > 4) {
				tmpTitle = article.title.substring(0, 4);
				System.out.printf("%3d    /   %6s    /   %5d\n", article.id, tmpTitle + "...", article.hit);
				continue;
			}
			System.out.printf("%3d    /   %6s    /   %5d\n", article.id, article.title, article.hit);
		}

	}

	public void showDetail(String command) {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		foundArticle.increaseHitCount();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	public void doModify(String command) {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		System.out.printf("제목 : ");
		String newTitle = sc.nextLine();
		System.out.printf("내용 : ");
		String newBody = sc.nextLine();

		foundArticle.title = newTitle;
		foundArticle.body = newBody;
		foundArticle.updateDate = Util.getTimeAndDateStr();

		System.out.printf("%d번 게시물을 수정했습니다\n", id);

	}

	public void doDelete(String command) {
		String[] commandDiv = command.split(" ");
		int id = Integer.parseInt(commandDiv[2]);

		int foundIndex = getArticleIndexById(id);

		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		articles.remove(foundIndex);
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);

	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

}
