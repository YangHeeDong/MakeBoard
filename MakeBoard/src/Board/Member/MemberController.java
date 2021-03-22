package Board.Member;

import java.util.ArrayList;

import Board.Controller;

public class MemberController extends Controller {

	static ArrayList<Member> members = new ArrayList<Member>();
	int memberLastNo = 1;

	// ������
	public void doCommand(String cmd) {
		if (cmd.equals("signup")) {
			addMember();

		} else if (cmd.equals("signin")) {
			loginCheck();

		} else if (cmd.equals("logout")) {
			if (isLogined()) {
				loginedMember =null;
				System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
			}
		}else {
			System.out.println("�˸´� ��ɾ �Է��� �ּ���");
		}
	}
		
	// �α���
	public void loginCheck() {
		if(loginedMember!=null) {
			System.out.println("�̹� �α��� �Ǿ� �ֽ��ϴ�.");
		}else {
			System.out.println("���̵� : ");
			String loginId = sc.nextLine();
			System.out.println("��й�ȣ : ");
			String loginPw = sc.nextLine();

			boolean isFailed = true;
			for (int i = 0; i < members.size(); i++) {
				Member member = members.get(i);

				if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
					// �α��� ����
					isFailed = false;
					System.out.println(member.getNickname() + "�� ȯ���մϴ�!!");
					loginedMember = member;
					break;
				}
			}
			// �α��� ����
			if (isFailed) {
				System.out.println("�߸��� ȸ�������Դϴ�.");
			}
		}
		
	}

	// ȸ������
	public void addMember() {
		if(loginedMember!=null) {
			System.out.println("�̹� �α��� �Ǿ� �ֽ��ϴ�.");
		}else {
			System.out.println("���̵� �Է����ּ��� : ");
			String loginId = sc.nextLine();
			System.out.println("��й�ȣ�� �Է����ּ��� : ");
			String loginPw = sc.nextLine();
			System.out.println("�г����� �Է����ּ��� : ");
			String nick = sc.nextLine();

			Member member = new Member();
			member.setId(memberLastNo);
			member.setLoginId(loginId);
			member.setLoginPw(loginPw);
			member.setNickname(nick);
			members.add(member);
			memberLastNo++;

			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		}
		
	}

}

