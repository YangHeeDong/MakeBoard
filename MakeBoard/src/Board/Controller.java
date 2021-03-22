package Board;

import java.util.Scanner;

import Board.Member.Member;

public abstract class Controller { // �߻�Ŭ����
	
	// ���� ��Ű�� + ��� ����
	protected Scanner sc = new Scanner(System.in);
	protected static Member loginedMember = null;
	static Controller controller = null;
	
	public abstract void doCommand(String cmd); // �߻�޼���

	//�α��� üũ
	public boolean isLogined() {
		if(loginedMember == null) {
			System.out.println("�α����� �ʿ��� ����Դϴ�.");
			return false;
		}		
		
		return true;
	}
}
