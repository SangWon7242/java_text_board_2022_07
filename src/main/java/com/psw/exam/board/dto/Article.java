package com.psw.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
  private int id;
  private String regDate;
  private String updateDate;
  private int boardId;
  private int memberId;
  private String title;
  private String body;
}
