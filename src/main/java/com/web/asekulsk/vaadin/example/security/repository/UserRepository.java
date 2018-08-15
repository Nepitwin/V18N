package com.web.asekulsk.vaadin.example.security.repository;

import com.web.asekulsk.vaadin.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User management read only repository.
 *
 * @author Andreas Sekulski
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username.
     *
     * @param name Username to find.
     * @return NULL if user not found otherwise user.
     */
    User findByUsername(String name);
}
