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

package com.web.asekulsk.vaadin.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.vaadin.spring.i18n.MessageProvider;
import org.vaadin.spring.i18n.ResourceBundleMessageProvider;

/**
 * I18N configuration class from vaaadin i18n addon.
 *
 * @author Andreas Sekulski
 */
@Configuration
@EnableWebMvc
public class VaadinI18NConfiguration {

    /**
     * Message bean provider to access i18n property files.
     *
     * @return Bundler for i18n handling.
     */
    @Bean
    MessageProvider messageProvider() {
        return new ResourceBundleMessageProvider("i18n.messages");
    }
}
