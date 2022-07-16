package com.alkemy.ong.infrastructure.util;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HeaderOnPagedResourceRetrieval {

  public void addLinkHeaderOnPagedResourceRetrieval(
      UriComponentsBuilder uriBuilder, HttpServletResponse response,
      String resource, int page, int totalPages, int size) {

    uriBuilder.path(resource);
    StringBuilder linkHeader = new StringBuilder();

    if (hasNextPage(page, totalPages)) {
      String uriNextPage = constructNextPageUri(uriBuilder, page, size);
      linkHeader.append(createLinkHeader(uriNextPage, "next"));
    }
    if (hasPreviousPage(page)) {
      String uriPrevPage = constructPrevPageUri(uriBuilder, page, size);
      appendCommaIfNecessary(linkHeader);
      linkHeader.append(createLinkHeader(uriPrevPage,"prev"));
    }
    response.addHeader("Link", linkHeader.toString());
  }

  String constructNextPageUri(UriComponentsBuilder uriBuilder, int page, int size) {
    return uriBuilder.replaceQueryParam("page", page + 1)
            .replaceQueryParam("size", size)
            .build()
            .encode()
            .toUriString();
  }


  String constructPrevPageUri(UriComponentsBuilder uriBuilder, int page, int size) {
    return uriBuilder.replaceQueryParam("page", page - 1)
            .replaceQueryParam("size", size)
            .build()
            .encode()
            .toUriString();
  }


  private boolean hasNextPage(int page, int totalPages) {
    return page < (totalPages - 1);
  }

  private boolean hasPreviousPage(int page) {
    return page > 0;
  }

  private void appendCommaIfNecessary(StringBuilder linkHeader) {
    if (linkHeader.length() > 0) {
      linkHeader.append(", ");
    }
  }

  private String createLinkHeader(String uri, String name) {
    return "<" + uri + ">; rel=" + name;
  }

}
