package com.web.asekulsk.vaadin.example.security.service;

import com.web.asekulsk.vaadin.example.security.model.User;
import com.web.asekulsk.vaadin.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security user detail service to handle user management.
 *
 * @author Andreas Sekulski
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    /**
     * User repository to handle user entities.
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("Username Not Found Exception : " + username);
        }
        return user;
    }
}
