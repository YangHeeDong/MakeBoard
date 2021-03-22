package Board.Member;

import java.util.Date;

public class Member {

	private int id;  // 회원 식별코드
	private String loginId; // 회원 아이디
	private String loginPw; // 회원 비밀번호
	private String nickname; // 회원 이름
	private Date CreateTime = new Date(); // 회원 등록일자

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}

