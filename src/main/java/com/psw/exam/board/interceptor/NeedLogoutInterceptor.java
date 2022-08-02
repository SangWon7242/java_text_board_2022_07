package com.psw.exam.board.interceptor;

import com.psw.exam.board.Rq;

public class NeedLogoutInterceptor implements Interceptor {

  @Override
  public boolean run(Rq rq) {
    if (rq.isLogined() == false) {
      return true;
    }

    switch (rq.getUrlPath()) {
      case "/usr/member/login":
      case "/usr/member/join":
      case "/usr/member/findLoginId":
      case "/usr/member/findLoginPw":
        System.out.printf("이미 로그인 되었습니다.\n");
        return false;
    }

    return true;
  }
}
