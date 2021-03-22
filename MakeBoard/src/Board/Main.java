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
				System.out.println("��ɾ �Է����ּ��� :");
			} else {
				System.out.println(
						"��ɾ �Է����ּ���[" + controller.loginedMember.getNickname()+"] :");
			}
			//��ɾ�
			//article add, article list, article update, article delete, article search
			//member signup, member signin, member logout
			
			String cmd = sc.nextLine();
			
			// ��� �м� - split ���ڿ� �ڸ���
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
				System.out.println("�˸´� ��ɾ �Է��� �ּ���");
				continue;
			}
			
			
			controller.doCommand(func);
			
			if (cmd.equals("exit")) {
				System.out.println("���α׷��� ����Ǿ����ϴ�.");
				break;
			} 
		}
	}

	
}
