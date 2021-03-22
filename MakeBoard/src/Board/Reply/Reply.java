package Board.Reply;

import java.util.Date;

public class Reply {
	
	private int id; // ´ñ±Û ¹øÈ£
	private int pid; // ºÎ¸ð±Û ¹øÈ£
	private String nickname; // ´ñ±Û ÀÛ¼ºÀÚ
	private String body; // ´ñ±Û ³»¿ë
	private Date CreateTime = new Date(); // ´ñ±Û µî·ÏÀÏ
	
	public int getId() {
		return id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}


	
}

