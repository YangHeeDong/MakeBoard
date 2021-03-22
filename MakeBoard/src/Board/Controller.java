package Board;

import java.util.Scanner;

import Board.Member.Member;

public abstract class Controller { // 추상클래스
	
	// 같은 패키지 + 상속 관계
	protected Scanner sc = new Scanner(System.in);
	protected static Member loginedMember = null;
	static Controller controller = null;
	
	public abstract void doCommand(String cmd); // 추상메서드

	//로그인 체크
	public boolean isLogined() {
		if(loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}		
		
		return true;
	}
}
