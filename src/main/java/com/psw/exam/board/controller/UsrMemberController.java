package com.psw.exam.board.controller;

import com.psw.exam.board.Rq;
import com.psw.exam.board.container.Container;
import com.psw.exam.board.dto.Member;
import com.psw.exam.board.service.MemberService;

public class UsrMemberController {

  private MemberService memberService;
  public UsrMemberController() {
    memberService = Container.getMemberService();
    memberService.makeTestData();
  }

  public void actionJoin(Rq rq) {
    System.out.println("- 회원 가입 -");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine();
    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.getSc().nextLine();
    System.out.printf("로그인 비밀번호 확인 : ");
    String loginConfirm = Container.getSc().nextLine();

    if (loginPw.equals(loginConfirm) == false ) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    int id = memberService.join(loginId, loginPw);

    Member member = memberService.getMemberByLoginId(loginId);

    System.out.printf("%s님. 가입을 환영합니다.\n", member.getLoginId());
    System.out.printf("%d번 회원이 생성 되었습니다.\n", member.getId());
  }

  public void actionLogin(Rq rq) {
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.getSc().nextLine().trim();

    if(loginId.length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);

    if ( member == null ) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.getSc().nextLine().trim();

    if(loginPw.length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    if(member.getLoginPw().equals(loginPw) == false) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    rq.login(member);

    System.out.printf("%s님 환영합니다.\n", member.getLoginId());
  }

  public void actionLogout(Rq rq) {
    rq.logout();

    System.out.println("로그아웃 되었습니다.");
  }
}
