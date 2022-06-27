package com.alkemy.ong.application.util;

public interface ISendEmail {

  void execute(IEmail email);

  String getType();

}
