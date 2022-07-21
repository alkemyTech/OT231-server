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
    return "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:s"
        + "chemas-microsoft-com:office:office\"><head><meta charset=\"utf-8\"><meta name=\"viewport"
        + "\" content=\"width=device-width,initial-scale=1\"><meta name=\"x-apple-disable-message-r"
        + "eformatting\"><title></title><!--[if mso]><style>table {border-collapse:collapse;border-"
        + "spacing:0;border:none;margin:0;}div, td {padding:0;}div {margin:0 !important;}</style><n"
        + "oscript><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDo"
        + "cumentSettings></xml></noscript><![endif]--><style>table, td, div, h1, p {font-family: A"
        + "rial, sans-serif;}@media screen and (max-width: 530px) {.unsub {display: block;padding: "
        + "8px;margin-top: 14px;border-radius: 6px;background-color: #555555;text-decoration: none "
        + "!important;font-weight: bold;}.col-lge {max-width: 100% !important;}}@media screen and ("
        + "min-width: 531px) {.col-sml {max-width: 27% !important;}.col-lge {max-width: 73% !import"
        + "ant;}}</style></head><body style=\"margin:0;padding:0;word-spacing:normal;background-col"
        + "or:#e6e6e6;\"><div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"t"
        + "ext-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-"
        + "color:#e6e6e6;\"><table role=\"presentation\" style=\"width:100%;border:none;border-spac"
        + "ing:0;\"><tr><td align=\"center\" style=\"padding:0;\"><!--[if mso]><table role=\"presen"
        + "tation\" align=\"center\" style=\"width:600px;\"><tr><td><![endif]--><table role=\"prese"
        + "ntation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left"
        + ";font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\"><tr><td s"
        + "tyle=\"padding:30px 30px 20px 30px;text-align:center;font-size:24px;font-weight:bold;\">"
        + "<img src=\"" + organization.getImage() + "\" width=\"180\" alt=\"Logo\" style=\"width:16"
        + "5px;max-width:80%;height:auto;border:none;text-decoration:none;color:#ffffff;\"></td></t"
        + "r><tr><td style=\"padding:30px;background-color:#ffffff;\"><h1 style=\"margin-top:0;marg"
        + "in-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;"
        + "\">Welcome to " + organization.getName() + ", " + contactInfo.getFullName() + "!</h1>"
        + "<p style=\"margin:0;\">" + organization.getWelcomeText() + "</p></td>"
        + "</tr><tr><td style=\"padding:20px 30px;font-size:0;background-color:#ffffff;border-botto"
        + "m:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\"><div style=\"display:inline-bl"
        + "ock;width:100%;vertical-align:top;font-family:Arial,sans-serif;font-size:16px;line-heigh"
        + "t:22px;\"><p style=\"margin:0;text-align: center;\"><a href=\"https://example.com/\" sty"
        + "le=\"background: #9ac9fb; text-decoration: none; padding: 10px 25px; color: #ffffff; bor"
        + "der-radius: 4px; display:inline-block; mso-padding-alt:0;\"><!--[if mso]><i style=\"lett"
        + "er-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span "
        + "style=\"mso-text-raise:10pt;font-weight:bold;\">VISIT OUR WEB PAGE</span><!--[if mso]><i"
        + " style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]--></a></p></div"
        + "></td></tr><tr><td style=\"padding:30px;background-color:#ffffff;\"><p style=\"margin:0;"
        + "\">If you want to reach out, feel free to contact the team via social media or whatsapp,"
        + " we will gladly answer your questions :)</p><br><p style=\"margin:0;\">Our Best, </p>"
        + "<p style=\"margin:0; font-weight: bold;\">" + organization.getName() + " Team</p>"
        + "</td></tr><tr><td style=\"padding:30px;text-align:center;font-size:12px;background-color"
        + ": #9ac9fb;color:#414141;\"><p style=\"margin:0 0 8px 0;\">"
        + "<a href=\"" + organization.getSocialMedia().getFacebookUrl() + "\" style=\"text-decorati"
        + "on:none;\"><img src=\"http://cdn.pemres01.net/tpl/welcome-kitten-coupon/img/fb-2.png\" w"
        + "idth=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#ffffff;\"></a> "
        + "<a href=\"" + organization.getSocialMedia().getInstagramUrl() + "\" style=\"text-decorat"
        + "ion:none;\"><img src=\"http://cdn.pemres01.net/tpl/welcome-kitten-coupon/img/instagram-2"
        + ".png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#ffffff;"
        + "\"></a><a href=\"https://api.whatsapp.com/send?phone=" + organization.getPhone()
        + "\" style=\"text-decoration:none;\"><img src=\"http://cdn.pemres01.net/tpl/welcome-kitte"
        + "n-coupon/img/what-2.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-bl"
        + "ock;color:#ffffff;\"></a><p style=\"margin:0;font-size:14px;line-height:20px;font-style:"
        + " italic;\">" + organization.getName() + ", " + organization.getAddress() + ", CABA, "
        + "Argentina.</p></td></tr></table><!--[if mso]></td></tr></table><![endif]--></td></tr>"
        + "</table></div></body></html>";
  }

}
