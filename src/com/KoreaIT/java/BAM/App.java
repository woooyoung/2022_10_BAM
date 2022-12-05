package com.KoreaIT.java.BAM;

import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();

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

			String[] cmdDiv = command.split(" "); 
			// article ~~
			// member ~~
			// asdfasd awsdf
			// asdfsa

			if (cmdDiv.length == 1) {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}

			String controllerName = cmdDiv[0];
			String actionMethodName = cmdDiv[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}

			controller.doAction(command, actionMethodName);

//			if (command.equals("member join")) {
//				memberController.doJoin();
//			} else if (command.equals("article write")) {
//				articleController.doWrite();
//			} else if (command.equals("article list")) {
//				articleController.showList();
//			} else if (command.startsWith("article detail ")) {
//				articleController.showDetail(command);
//			} else if (command.startsWith("article modify ")) {
//				articleController.doModify(command);
//			} else if (command.startsWith("article delete ")) {
//				articleController.doDelete(command);
//			} else {
//				System.out.println("존재하지 않는 명령어입니다");
//			}
		}

		System.out.println("==프로그램 끝==");
		sc.close();

	}

}
