package com.psw.exam.board;

import com.psw.exam.board.container.Container;
import com.psw.exam.board.util.Util;

import java.util.Map;

public class Rq {
  private String url;
  private Map<String, String> params;
  private String urlPath;

  public Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public int getIntParam(String paramsName, int defaultValue) {

    if ( params.containsKey(paramsName) == false ) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramsName));
    }
    catch ( NumberFormatException e) {
      return defaultValue;
    }

  }

  public String getParam(String paramsName, String defaultValue) {

    if ( params.containsKey(paramsName) == false ) {
      return defaultValue;
    }

    return params.get(paramsName);
  }

  public String getUrlPath() {
    return urlPath;
  }

  public void setSessionAttr(String key, Object value) {
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();

    session.removeAttribute(key);
  }
}
