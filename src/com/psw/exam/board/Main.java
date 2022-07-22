package com.psw.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static int articleLastId = 0;
  static List<Article> articles = new ArrayList<>();
  static void makeTestData() {
    for (int i = 0; i < 100; i++) {
      int id = i + 1;
      articles.add(new Article(id, "제목" + id, "내용" + id));
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    makeTestData();

    if (articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getParams();

      if (rq.getUrlPath().equals("exit")) {
        break;
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite(rq, sc);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        actionUsrArticleModify(rq, sc);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        actionUsrArticleDelete(rq, sc);
      } else {
        System.out.printf("입력 된 명령어 : %s\n", cmd);
      }
    }

    System.out.println("== 프로그램 끝 ==");

    sc.close();
  }

  private static void actionUsrArticleDelete(Rq rq, Scanner sc) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(id - 1);

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    articles.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", article.id);
  }

  private static void actionUsrArticleModify(Rq rq, Scanner sc) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(id - 1);

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.printf("새 제목 : ");
    article.title = sc.nextLine();
    System.out.printf("새 내용 : ");
    article.body = sc.nextLine();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  private static void actionUsrArticleDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(id - 1);

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("- 게시물 상세 내용 -");
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.body);
  }

  private static void actionUsrArticleList(Rq rq) {
    System.out.println("- 게시물 리스트 -");
    System.out.printf("------------------\n");
    System.out.printf("번호 / 제목\n");
    System.out.printf("------------------\n");

    Map<String, String> params = rq.getParams();

    // 검색 시작
    List<Article> filteredArticles = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }

    List<Article> sortedArticles = filteredArticles;

    boolean orderByIdDesc = true;

    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    for (Article article : sortedArticles) {
      System.out.printf("%d / %s\n", article.id, article.title);
    }

  }

  private static void actionUsrArticleWrite(Rq rq, Scanner sc) {
    System.out.println("- 게시물 등록 -");
    System.out.printf("제목 : ");
    String title = sc.nextLine();
    System.out.printf("내용 : ");
    String body = sc.nextLine();

    int id = ++articleLastId;

    Article article = new Article(id, title, body);

    articles.add(article);

    System.out.println("생성된 게시물 객체 : " + article);
    System.out.printf("%d번 게시물이 입력되었습니다.\n", article.id);
  }

}
