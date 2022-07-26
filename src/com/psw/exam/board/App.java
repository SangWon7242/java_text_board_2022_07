package com.psw.exam.board;

import java.util.Scanner;

public class App {
  void run() {

    Scanner sc = Container.sc;
    Session session = Container.getSession();


    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      Member loginedMember = (Member) session.getAttribute("loginedMember");

      String promptName = "명령";

      if ( loginedMember != null ) {
        promptName = loginedMember.loginId;
      }


      System.out.printf("%s) ", promptName);
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("exit")) {
        break;
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.usrArticleController.actionList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.usrArticleController.actionDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.usrArticleController.actionWrite(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.usrArticleController.actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.usrArticleController.actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.usrMemberController.actionJoin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.usrMemberController.actionLogin(rq);
      } else {
        System.out.printf("입력 된 명령어 : %s\n", cmd);
      }
    }

    System.out.println("== 프로그램 끝 ==");

    sc.close();
  }


}
