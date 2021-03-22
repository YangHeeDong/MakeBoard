package Board;

import java.util.Scanner;

import Board.Article.ArticleController;
import Board.Member.MemberController;

public class Main {

	static Controller controller;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			if (controller == null || controller.loginedMember == null) {
				System.out.println("명령어를 입력해주세요 :");
			} else {
				System.out.println(
						"명령어를 입력해주세요[" + controller.loginedMember.getNickname()+"] :");
			}
			//명령어
			//article add, article list, article update, article delete, article search
			//member signup, member signin, member logout
			
			String cmd = sc.nextLine();
			
			// 모듈 분석 - split 문자열 자르기
			String[] cmdBits = cmd.split(" ");
			
			String module = cmdBits[0];
			String func = cmdBits[1];
			if(module.equals("article")||module.equals("member")) {
				if(module.equals("article")) {
					controller = new ArticleController();		
				} else if(module.equals("member")) {
					controller = new MemberController();				
				}
			}else {
				System.out.println("알맞는 명령어를 입력해 주세요");
				continue;
			}
			
			
			controller.doCommand(func);
			
			if (cmd.equals("exit")) {
				System.out.println("프로그램이 종료되었습니다.");
				break;
			} 
		}
	}

	
}
