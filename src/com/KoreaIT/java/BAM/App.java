package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.MemberController;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class App {

	public List<Article> articles;;
	public List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.equals("article list")) {
				articleController.showList();
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);
			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("==프로그램 끝==");
		sc.close();

	}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");

		articles.add(new Article(1, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "제목3", "내용3", 33));
	}

}
