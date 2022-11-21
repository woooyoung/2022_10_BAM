package com.KoreaIT.java.BAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		System.out.printf("명령어 ) ");

		String command = sc.nextLine();

		System.out.printf("입력된 문장 : %s\n", command);

		int cmd_INT = sc.nextInt();

		System.out.printf("입력된 정수 : %d\n", cmd_INT);

		System.out.println("==프로그램 끝==");
		sc.close();
	}
}