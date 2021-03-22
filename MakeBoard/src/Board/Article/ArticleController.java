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
	
	// 재정의
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
			System.out.println("알맞는 명령어를 입력해 주세요");
		}
	}

	// 게시물 찾기
	public void searchArticle() {
		if(isLogined()) {
			System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) :");
			int searchTarget = Integer.parseInt(sc.nextLine());

			System.out.println("검색 키워드를 입력해주세요 :");
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

	// 게시물 삭제
	public void deleteArticle() {
		if(isLogined()) {
			System.out.println("삭제할 게시물 번호 :");
			int targetId = Integer.parseInt(sc.nextLine()); // 문자의 숫자 변환

			Article article = getArticleByNumber(targetId);

			if (article == null) {
				System.out.println("없는 게시물입니다.");
			} else {
				articles.remove(article);
				System.out.println("삭제가 완료되었습니다.");
			}
		}
	}

	// 게시물 추가
	public void addArticle() {
		if(isLogined()) {
			System.out.println("제목을 입력해주세요 : ");
			String title = sc.nextLine();

			System.out.println("내용을 입력해주세요 : ");
			String body = sc.nextLine();

			Article art = new Article();
			art.setTitle(title);
			art.setBody(body);
			art.setNickname(loginedMember.getNickname());
			art.setNumber(articleLastNo);
			articles.add(art);

			System.out.println("게시물이 등록되었습니다.");
			articleLastNo++;
		}
	}

	// 번호로 게시물 찾기 메서드
	public Article getArticleByNumber(int aid) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.getNumber() == aid) {
				return article;
			}
		}

		return null;
	}

	// 어레이리스트 출력
	public void printArticleList(ArrayList<Article> articles) {

		for (int i = 0; i < articles.size(); i++) {

			Article article = articles.get(i);

			System.out.println("번호 : " + article.getNumber());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getCreateTime());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("============================");
		}

	}

	// 게시물 수정
	public void updateArticle() {
		if(isLogined()) {
			System.out.println("수정할 게시물 번호 :");
			int targetId = Integer.parseInt(sc.nextLine()); // 문자의 숫자 변환

			Article article = getArticleByNumber(targetId);

			if (article == null) {
				System.out.println("없는 게시물입니다.");
			} else {
				System.out.println("새제목 : ");
				article.setTitle(sc.nextLine());
				System.out.println("새내용 : ");
				article.setBody(sc.nextLine());
				
				System.out.println("수정이 완료되었습니다.");
			}
		}
	}

	// 게시물 상세보기 메서드
	public void readArticle() {
		System.out.println("상세보기할 게시물 번호 :");
		int targetId = Integer.parseInt(sc.nextLine()); // 문자의 숫자 변환

		Article article = getArticleByNumber(targetId);

		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {

			int hit = article.getHit();
			article.setHit(hit + 1);

			printArticle(article);

			// 상세보기 프로세스
			readProcess(article);
		}

	}

	public void printArticle(Article article) {

		System.out.println("번호 : " + article.getNumber());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("등록날짜 : " + article.getCreateTime());
		System.out.println("조회수 : " + article.getHit());
		System.out.println("작성자 : " + article.getNickname());

		// 댓글 출력
		System.out.println("============= 댓글 ============");
		ArrayList<Reply> replies = rc.getReplies();
		for (int i = 0; i < replies.size(); i++) {

			Reply reply = replies.get(i);

			if (article.getNumber() == reply.getPid()) {
				System.out.println("내용 : " + reply.getBody());
				System.out.println("작성자 : " + reply.getNickname());
				System.out.println("작성일 : " + reply.getCreateTime());
			}
		}
		System.out.println("==============================");

	}

	// 상세 메서드
	public void readProcess(Article article) {

		while (true) {
			System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 목록으로) : ");
			int cmd = Integer.parseInt(sc.nextLine());

			if (cmd == 1) {
				rc.addReply(article);
				printArticle(article);

			} else if (cmd == 2) {
				article.setHit(article.getHit()+1);

			} else if (cmd == 3) {
				break;
			} else {
				System.out.println("알맞은 번호를 입력해 주세요");
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

