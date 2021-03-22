package Board.Article;
import java.util.ArrayList;

import Board.Controller;
import Board.Member.MemberController;
import Board.Reply.Reply;
import Board.Reply.ReplyController;

public class ArticleController extends Controller {

	static ArrayList<Article> articles = new ArrayList<Article>();
	MemberController mc = new MemberController();
	ReplyController rc = new ReplyController();
	int articleLastNo = 1;
	
	// ������
	public void doCommand(String cmd) {

	 	if (cmd.equals("add")) {
			if(isLogined()) {
				addArticle();					
			}

		} else if (cmd.equals("list")) {
			printArticleList(articles);

		} else if (cmd.equals("update")) {
			updateArticle();

		} else if (cmd.equals("delete")) {
			deleteArticle();

		} else if (cmd.equals("read")) {
			if(isLogined()) {
				readArticle();
			}
		} else if (cmd.equals("search")) {
			searchArticle();

		} else {
			System.out.println("�˸´� ��ɾ �Է��� �ּ���");
		}
	}

	// �Խù� ã��
	public void searchArticle() {
		if(isLogined()) {
			System.out.println("�˻� �׸��� �������ּ��� (1. ����, 2. ����, 3. ���� + ����, 4. �ۼ���) :");
			int searchTarget = Integer.parseInt(sc.nextLine());

			System.out.println("�˻� Ű���带 �Է����ּ��� :");
			String keyword = sc.nextLine();

			ArrayList<Article> searchedList = new ArrayList<Article>();

			for (int i = 0; i < articles.size(); i++) {
				Article article = articles.get(i);

				String target;

				if (searchTarget == 1) {
					target = article.getTitle();
				} else if (searchTarget == 2) {
					target = article.getBody();
				} else if (searchTarget == 3) {
					target = article.getTitle() + article.getBody();
				} else {
					target = article.getNickname();
				}

				if (target.contains(keyword)) {
					searchedList.add(article);
				}
			}

			printArticleList(searchedList);
		}
		

	}

	// �Խù� ����
	public void deleteArticle() {
		if(isLogined()) {
			System.out.println("������ �Խù� ��ȣ :");
			int targetId = Integer.parseInt(sc.nextLine()); // ������ ���� ��ȯ

			Article article = getArticleByNumber(targetId);

			if (article == null) {
				System.out.println("���� �Խù��Դϴ�.");
			} else {
				articles.remove(article);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			}
		}
	}

	// �Խù� �߰�
	public void addArticle() {
		if(isLogined()) {
			System.out.println("������ �Է����ּ��� : ");
			String title = sc.nextLine();

			System.out.println("������ �Է����ּ��� : ");
			String body = sc.nextLine();

			Article art = new Article();
			art.setTitle(title);
			art.setBody(body);
			art.setNickname(loginedMember.getNickname());
			art.setNumber(articleLastNo);
			articles.add(art);

			System.out.println("�Խù��� ��ϵǾ����ϴ�.");
			articleLastNo++;
		}
	}

	// ��ȣ�� �Խù� ã�� �޼���
	public Article getArticleByNumber(int aid) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.getNumber() == aid) {
				return article;
			}
		}

		return null;
	}

	// ��̸���Ʈ ���
	public void printArticleList(ArrayList<Article> articles) {

		for (int i = 0; i < articles.size(); i++) {

			Article article = articles.get(i);

			System.out.println("��ȣ : " + article.getNumber());
			System.out.println("���� : " + article.getTitle());
			System.out.println("��ϳ�¥ : " + article.getCreateTime());
			System.out.println("��ȸ�� : " + article.getHit());
			System.out.println("�ۼ��� : " + article.getNickname());
			System.out.println("============================");
		}

	}

	// �Խù� ����
	public void updateArticle() {
		if(isLogined()) {
			System.out.println("������ �Խù� ��ȣ :");
			int targetId = Integer.parseInt(sc.nextLine()); // ������ ���� ��ȯ

			Article article = getArticleByNumber(targetId);

			if (article == null) {
				System.out.println("���� �Խù��Դϴ�.");
			} else {
				System.out.println("������ : ");
				article.setTitle(sc.nextLine());
				System.out.println("������ : ");
				article.setBody(sc.nextLine());
				
				System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			}
		}
	}

	// �Խù� �󼼺��� �޼���
	public void readArticle() {
		System.out.println("�󼼺����� �Խù� ��ȣ :");
		int targetId = Integer.parseInt(sc.nextLine()); // ������ ���� ��ȯ

		Article article = getArticleByNumber(targetId);

		if (article == null) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {

			int hit = article.getHit();
			article.setHit(hit + 1);

			printArticle(article);

			// �󼼺��� ���μ���
			readProcess(article);
		}

	}

	public void printArticle(Article article) {

		System.out.println("��ȣ : " + article.getNumber());
		System.out.println("���� : " + article.getTitle());
		System.out.println("���� : " + article.getBody());
		System.out.println("��ϳ�¥ : " + article.getCreateTime());
		System.out.println("��ȸ�� : " + article.getHit());
		System.out.println("�ۼ��� : " + article.getNickname());

		// ��� ���
		System.out.println("============= ��� ============");
		ArrayList<Reply> replies = rc.getReplies();
		for (int i = 0; i < replies.size(); i++) {

			Reply reply = replies.get(i);

			if (article.getNumber() == reply.getPid()) {
				System.out.println("���� : " + reply.getBody());
				System.out.println("�ۼ��� : " + reply.getNickname());
				System.out.println("�ۼ��� : " + reply.getCreateTime());
			}
		}
		System.out.println("==============================");

	}

	// �� �޼���
	public void readProcess(Article article) {

		while (true) {
			System.out.println("�󼼺��� ����� �������ּ���(1. ��� ���, 2. ���ƿ�, 3. �������) : ");
			int cmd = Integer.parseInt(sc.nextLine());

			if (cmd == 1) {
				rc.addReply(article);
				printArticle(article);

			} else if (cmd == 2) {
				article.setHit(article.getHit()+1);

			} else if (cmd == 3) {
				break;
			} else {
				System.out.println("�˸��� ��ȣ�� �Է��� �ּ���");
			}
		}
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

}

