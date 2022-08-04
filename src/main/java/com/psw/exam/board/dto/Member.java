package com.psw.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
  private int id;
  private String regDate;
  private String updateDate;
  private String loginId;
  private String loginPw;
}
