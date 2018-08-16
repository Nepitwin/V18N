/*
 * Copyright 2018 Andreas Sekulski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.web.asekulsk.vaadin.example.spring.security.repository;

import com.web.asekulsk.vaadin.example.spring.security.model.User;
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
