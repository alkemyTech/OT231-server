package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IContactInfo;
import com.alkemy.ong.application.util.IEmail;
import com.alkemy.ong.domain.Organization;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WelcomeEmailTemplate implements IEmail {

  private final Organization organization;
  private final IContactInfo contactInfo;

  @Override
  public String getSubject() {
    return organization.getName();
  }

  @Override
  public String getTo() {
    return contactInfo.getEmail();
  }

  @Override
  public String getContentType() {
    return "text/html";
  }

  @Override
  public String getContent() {
    return "<div style=\"display: flex; flex-direction: column; margin: auto; margin-top: 5vh; "
           + " width: 60vh; height: 80vh; text-align: center; "
           + " box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px; "
           + " font-family: 'Edu NSW ACT Foundation', cursive; box-sizing: border-box;\">"

           + "<div style=\"width:100%;display:flex; justify-content:center; align-items:center; "
           + " height: 20%; background-color: rgb(125, 56, 191);\">\n"
           + " <img src=\"/" + organization.getImage() + "\" style=\"margin-right: 10px;\" "
           + " width=\"100px\" height=\"100px\" alt=\"\">\n"
           + " <h1 style=\"font-size: 3rem; color: #fff;\">" + organization.getName() + "</h1>"
           + " </div>"

           + " <div style=\"margin: 25px auto; height: 60%; width: 90%; display: flex; "
           + " flex-direction: column; justify-content: center;\">"
           + " <h2 style=\"font-size: 2rem;\">Hello " + contactInfo.getFullName() + "! </h2>"

           + " <p style=\"font-size: 1.5rem;\">" + organization.getWelcomeText() + " </p>"
           + " </div>"

           + " <div style=\"width: 100%; display: flex; height: 15%; background-color: #111; "
           + " color:#ccc; line-height: 1.6rem; justify-content: center;\">"

           + " <div style=\"display: flex; flex-direction: column; width: 45%; \">"
           + " <p> Follow us:</p>"
           + " <a style=\"text-decoration: none; color:#ccc;\" href=\""
           + organization.getSocialMedia().getFacebookUrl() + "\" target=\"_blank\">"
           + "  &#128216; Facebook </a>"
           + " <a style=\"text-decoration: none; color:#ccc;\" href=\""
           + organization.getSocialMedia().getInstagramUrl() + "\" target=\"_blank\">"
           + "  &#128213; Instagram </a>"
           + " <a style=\"text-decoration: none; color: #ccc;;\" href=\""
           + organization.getSocialMedia().getLinkedIndUrl() + "\" target=\"_blank\">"
           + " &#128188; LinkedIN </a> "
           + "</div>"

           + " <div style=\"margin:auto 0;background-color:#30353B;height:90%;width:1px;\"></div>"

           + " <div style=\"display: flex; flex-direction: column; width: 45%; color:#ccc\">"
           + " <p> Contact us:</p>"
           + " <span> &#127750; Address: " + organization.getAddress() + " </span>"
           + " <span> &#128241; Phone: " + organization.getPhone() + " </span>"
           + " <span>&#128231; Email  " + organization.getEmail() + " </span>"
           + "</div>"

           + "</div>"

           + "</div>";
  }

}
