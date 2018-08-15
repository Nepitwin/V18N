package com.web.asekulsk.vaadin.example.security.util;

import com.web.asekulsk.vaadin.example.security.model.User;
import com.web.asekulsk.vaadin.example.security.type.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Security context utility class to check if user has granted roles for example.
 *
 * @author Andreas Sekulski
 */
public final class SecurityContextUtils {

    /**
     * @return Checks if user logged in TRUE otherwise FALSE.
     */
    public static boolean isLoggedIn() {
        Authentication authentication = authentication();

        if (authentication == null) {
            return false;
        }

        return authentication.isAuthenticated();
    }

    /**
     * Checks if authenticated user has given role.
     *
     * @param type Role to check for user.
     * @return FALSE if user not authenticated or has not granted rights otherwise FALSE.
     */
    public static boolean hasRole(RoleType type) {
        GrantedAuthority grantedAuthority;
        Authentication authentication = authentication();

        if (authentication == null) {
            return false;
        }

        grantedAuthority = new SimpleGrantedAuthority(type.name());

        return authentication.getAuthorities().contains(grantedAuthority);
    }

    /**
     * @return Get current logged user if authentication is established otherwise guest user.
     */
    public static User getUser() {
        Authentication authentication = authentication();

        if (authentication == null) {
            return new User();
        }

        return (User) authentication.getPrincipal();
    }

    /**
     * @return Get list from all role types by user if authenticated otherwise empty role list.
     */
    public static List<RoleType> getRoleTypes() {
        Collection<GrantedAuthority> authorities;
        Authentication authentication = authentication();

        if (authentication == null) {
            return new ArrayList<>();
        }

        authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();

        return authorities.stream().map(x -> RoleType.findByType(x.getAuthority())).collect(Collectors.toList());
    }

    /**
     * Try to get authentication service token.
     *
     * @return Authentication token to get user date.
     * @throws SecurityContextNotFoundException If authentication can not be established.
     */
    private static Authentication authentication() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (securityContext == null) {
            throw new SecurityContextNotFoundException("SecurityContext Not Found Exception");
        }

        final Authentication authentication = securityContext.getAuthentication();

        if (authentication == null) {
            throw new SecurityContextNotFoundException("SecurityContext Authentication Not Found Exception");
        }

        return authentication;
    }

}
