package com.psw.exam.board;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  private int memberLastId;
  private List<Member> members;

  UsrMemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (members.size() > 0) {
      memberLastId = members.get(members.size() - 1).id;
    }

  }
  void makeTestData() {
    for (int i = 0; i < 3; i++) {
      int id = i + 1;
      members.add(new Member(id, "user" + id, "user" + id));
    }
  }

  public void actionJoin(Rq rq) {
    System.out.println("- 회원 가입 -");
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine();
    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.sc.nextLine();
    System.out.printf("로그인 비밀번호 확인 : ");
    String loginConfirm = Container.sc.nextLine();

    if (loginPw.equals(loginConfirm) == false ) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    int id = ++memberLastId;

    Member member = new Member(id, loginId, loginPw);

    members.add(member);

    System.out.printf("%s님. 가입을 환영합니다.\n", member.loginId);
    System.out.printf("%d번 회원이 생성 되었습니다.\n", member.id);
  }

  public void actionLogin(Rq rq) {
    System.out.printf("로그인 아이디 : ");
    String loginId = Container.sc.nextLine().trim();

    if(loginId.length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    Member member = getMemberByLoginId(loginId);

    if ( member == null ) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 비밀번호 : ");
    String loginPw = Container.sc.nextLine().trim();

    if(loginPw.length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }

    if(member.loginPw.equals(loginPw) == false) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    System.out.printf("%s님 환영합니다.\n", member.loginId);
  }

  private Member getMemberByLoginId(String loginId) {
    for(Member member : members ) {
      if ( member.loginId.equals(loginId)) {
        return member;
      }
    }
    return null;
  }
}
