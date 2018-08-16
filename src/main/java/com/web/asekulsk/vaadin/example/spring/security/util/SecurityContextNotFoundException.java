package com.web.asekulsk.vaadin.example.spring.security.util;

import org.springframework.security.core.AuthenticationException;

/**
 * Security context not found exception class if security not works.
 *
 * @author Andreas Sekulski
 */
public class SecurityContextNotFoundException extends AuthenticationException {

    /**
     * Constructor to create security context not found exception.
     *
     * @param msg Message to thrown.
     */
    public SecurityContextNotFoundException(String msg) {
        super(msg);
    }
}
