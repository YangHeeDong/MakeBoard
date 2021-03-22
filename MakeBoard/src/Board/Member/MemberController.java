package Board.Member;

import java.util.ArrayList;

import Board.Controller;

public class MemberController extends Controller {

	static ArrayList<Member> members = new ArrayList<Member>();
	int memberLastNo = 1;

	// 재정의
	public void doCommand(String cmd) {
		if (cmd.equals("signup")) {
			addMember();

		} else if (cmd.equals("signin")) {
			loginCheck();

		} else if (cmd.equals("logout")) {
			if (isLogined()) {
				loginedMember =null;
				System.out.println("로그아웃 되었습니다.");
			}
		}else {
			System.out.println("알맞는 명령어를 입력해 주세요");
		}
	}
		
	// 로그인
	public void loginCheck() {
		if(loginedMember!=null) {
			System.out.println("이미 로그인 되어 있습니다.");
		}else {
			System.out.println("아이디 : ");
			String loginId = sc.nextLine();
			System.out.println("비밀번호 : ");
			String loginPw = sc.nextLine();

			boolean isFailed = true;
			for (int i = 0; i < members.size(); i++) {
				Member member = members.get(i);

				if (member.getLoginId().equals(loginId) && member.getLoginPw().equals(loginPw)) {
					// 로그인 성공
					isFailed = false;
					System.out.println(member.getNickname() + "님 환영합니다!!");
					loginedMember = member;
					break;
				}
			}
			// 로그인 실패
			if (isFailed) {
				System.out.println("잘못된 회원정보입니다.");
			}
		}
		
	}

	// 회원가입
	public void addMember() {
		if(loginedMember!=null) {
			System.out.println("이미 로그인 되어 있습니다.");
		}else {
			System.out.println("아이디를 입력해주세요 : ");
			String loginId = sc.nextLine();
			System.out.println("비밀번호를 입력해주세요 : ");
			String loginPw = sc.nextLine();
			System.out.println("닉네임을 입력해주세요 : ");
			String nick = sc.nextLine();

			Member member = new Member();
			member.setId(memberLastNo);
			member.setLoginId(loginId);
			member.setLoginPw(loginPw);
			member.setNickname(nick);
			members.add(member);
			memberLastNo++;

			System.out.println("회원가입이 완료되었습니다.");
		}
		
	}

}

