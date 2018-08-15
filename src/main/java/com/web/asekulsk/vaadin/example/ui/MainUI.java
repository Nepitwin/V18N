package com.web.asekulsk.vaadin.example.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.security.VaadinSecurity;

/**
 * ToDo Create UI to show ADMIN and USER View
 *
 * @author Andreas Sekulski
 */
@SpringUI(path = "/")
@Theme("V18N")
public class MainUI extends UI {

    /**
     * I18N
     */
    @Autowired
    private I18N i18n;

    @Autowired
    private VaadinSecurity vaadinSecurity;

    @Override
    protected void init(VaadinRequest request) {

        setLocale(VaadinSession.getCurrent().getLocale());

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        Panel panel = new Panel(i18n.get("login", getLocale()));
        panel.setSizeFull();
        panel.setContent(new Label("Hello Label 123456"));

        layout.addComponent(new Button("Logout", e -> vaadinSecurity.logout()));
        layout.addComponent(panel);

        this.setResponsive(true);
        this.setSizeFull();

        setContent(layout);
    }
}
