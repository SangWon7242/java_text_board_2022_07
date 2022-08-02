package com.psw.exam.board.interceptor;

import com.psw.exam.board.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
