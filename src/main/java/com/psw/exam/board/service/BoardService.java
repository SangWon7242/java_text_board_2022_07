package com.psw.exam.board.service;

import com.psw.exam.board.container.Container;
import com.psw.exam.board.dto.Board;
import com.psw.exam.board.repository.BoardRepository;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.getBoardRepository();
  }
  public Board getBoardById(int id) {
    return boardRepository.getBoardById(id);
  }

  public void makeTestData() {
    make("공지사항", "공지사항");
    make("자유게시판", "자유게시판");
  }

  private int make(String code, String name) {
    return boardRepository.make(code, name);
  }
}
