package com.web.asekulsk.vaadin.example.security.repository;

import com.web.asekulsk.vaadin.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by basakpie on 2017. 5. 11..
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

}
