package Board.Reply;

import java.util.ArrayList;

import Board.Controller;
import Board.Article.Article;

public class ReplyController extends Controller {
	static ArrayList<Reply> replies = new ArrayList<Reply>();
	int replyLastNo = 1;
	
	public void doCommand(String cmd) {
		
	}
	
	// ��� �߰�
	public void addReply(Article article) {
		System.out.println(" ��� ������ �Է����ּ��� : ");
		int pid = article.getNumber();
		String body = sc.nextLine();

		Reply reply = new Reply();
		reply.setBody(body);
		reply.setId(replyLastNo);
		reply.setNickname(loginedMember.getNickname());
		reply.setPid(pid);
		replies.add(reply);
	}

	public ArrayList<Reply> getReplies() {
		return replies;
	}

	public void setReplies(ArrayList<Reply> replies) {
		this.replies = replies;
	}
	
	
}

