package Board.Member;

import java.util.Date;

public class Member {

	private int id;  // ȸ�� �ĺ��ڵ�
	private String loginId; // ȸ�� ���̵�
	private String loginPw; // ȸ�� ��й�ȣ
	private String nickname; // ȸ�� �̸�
	private Date CreateTime = new Date(); // ȸ�� �������

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

