package com.web.asekulsk.vaadin.example.security.util;

import org.springframework.security.core.AuthenticationException;

public class SecurityContextNotFoundException extends AuthenticationException {

    public SecurityContextNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public SecurityContextNotFoundException(String msg) {
        super(msg);
    }
}