package com.psw.exam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
  int id;
  String loginId;
  String loginPw;
}
