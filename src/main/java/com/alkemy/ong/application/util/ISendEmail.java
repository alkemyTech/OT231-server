package com.alkemy.ong.application.util;

public interface ISendEmail {

  String execute(IEmail email);

  String getType();

}
