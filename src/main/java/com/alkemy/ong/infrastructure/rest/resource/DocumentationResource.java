package com.alkemy.ong.infrastructure.rest.resource;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentationResource {

  @GetMapping("/api/docs")
  public void swaggerRedirect(HttpServletResponse response) {
    response.setHeader("Location", "/swagger-ui.html");
    response.setStatus(302);
  }

}
