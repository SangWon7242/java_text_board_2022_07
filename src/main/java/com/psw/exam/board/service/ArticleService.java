package com.psw.exam.board.service;

import com.psw.exam.board.dto.Article;
import com.psw.exam.board.repository.ArticleRepository;

import java.util.List;

public class ArticleService {

  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = new ArticleRepository();
  }

  public int write(String title, String body) {
    return articleRepository.write(title, body);
  }

  public void makeTestData() {
    for (int i = 0; i < 100; i++) {
      String title = "제목" + (i + 1);
      String body = "내용" + (i + 1);
      write(title, body);
    }
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public void deleteArticleById(int id) {
    articleRepository.deleteArticleById(id);
  }

}
