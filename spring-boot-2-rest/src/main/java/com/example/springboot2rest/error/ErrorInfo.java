package com.example.springboot2rest.error;

import javax.servlet.http.HttpServletRequest;

public class ErrorInfo {

    public final StringBuffer url;
    public final String uri;
    public final String ex;

    public ErrorInfo(final HttpServletRequest req, final Exception ex) {
        this.url = req.getRequestURL();
        this.uri = req.getRequestURI();
        this.ex = ex.getLocalizedMessage();
    }
}