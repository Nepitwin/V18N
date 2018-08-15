package com.web.asekulsk.vaadin.example.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.web.asekulsk.vaadin.example.component.LanguageSelector;
import com.web.asekulsk.vaadin.example.util.HTML;
import com.web.asekulsk.vaadin.example.util.V18NTheme;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.i18n.support.TranslatableUI;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

import java.util.Locale;

/**
 * Vaadin login user interface.
 *
 * @author Andreas Sekulski
 */
@SpringUI(path = "/login")
@Theme("V18N")
public class LoginUI extends TranslatableUI {

    /**
     * Internationalizing
     */
    @Autowired
    I18N i18n;

    /**
     * Vaadin based spring web security setup
     */
    @Autowired
    private VaadinSharedSecurity vaadinSecurity;

    /**
     * Username as textfield
     */
    private TextField usernameField;

    /**
     * Password field.
     */
    private PasswordField passwordField;

    /**
     * Login button.
     */
    private Button login;

    /**
     * Sign in label text.
     */
    private Label labelSignIn;

    /**
     * Information label which contains static data.
     */
    private Label informationLabel;

    /**
     * Language selector to set supported languages.
     */
    @Autowired
    private LanguageSelector languageSelector;

    @Override
    public void setLocale(Locale locale) {
        updateMessageStrings();
    }

    @Override
    protected void initUI(VaadinRequest request) {
        FormLayout loginForm = new FormLayout();
        loginForm.setSizeUndefined();

        usernameField = new TextField();
        usernameField.setIcon(VaadinIcons.USER);

        passwordField = new PasswordField();
        passwordField.setIcon(VaadinIcons.PASSWORD);

        login = new Button();
        login.addStyleName(ValoTheme.BUTTON_PRIMARY + " " + V18NTheme.LOGIN_BUTTON);
        login.setDisableOnClick(true);
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addClickListener(e -> login());

        loginForm.addComponent(usernameField);
        loginForm.addComponent(passwordField);
        loginForm.addComponent(login);

        labelSignIn = new Label();
        labelSignIn.addStyleName(ValoTheme.LABEL_H1 + " " + V18NTheme.NO_MARGIN);

        VerticalLayout signInLayout = new VerticalLayout();
        signInLayout.setSizeFull();
        signInLayout.setMargin(false);
        signInLayout.addComponent(labelSignIn);
        signInLayout.addStyleName(V18NTheme.TEXT_ALIGN_CENTER);

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setSpacing(false);
        loginLayout.setSizeUndefined();
        loginLayout.addStyleName(V18NTheme.LOGIN_FORM);
        loginLayout.addComponent(signInLayout);
        loginLayout.addComponent(loginForm);
        loginLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

        // Get static html file from resources folder
        informationLabel = new Label();
        informationLabel.setContentMode(ContentMode.HTML);
        informationLabel.addStyleName(V18NTheme.TEXT_COLOR_LOGIN);
        informationLabel.setWidth("100%");

        VerticalLayout information = new VerticalLayout();
        information.addComponent(informationLabel);
        information.addComponent(loginLayout);

        VerticalLayout infoBox = new VerticalLayout();
        infoBox.setMargin(false);
        infoBox.setSpacing(false);
        infoBox.setSizeFull();
        infoBox.addComponent(information);
        infoBox.addStyleName(V18NTheme.INFO_BOX);

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.addComponent(infoBox);
        infoLayout.addComponent(languageSelector);
        infoLayout.setWidth("50%");
        infoLayout.setHeight("100%");
        infoLayout.setExpandRatio(infoBox, 0.95f);

        HorizontalLayout rootLayout = new HorizontalLayout();
        rootLayout.addComponent(infoLayout);
        rootLayout.setSizeFull();
        rootLayout.setComponentAlignment(infoLayout, Alignment.TOP_LEFT);
        rootLayout.addStyleName(V18NTheme.LOGIN_BACKGROUND);
        setContent(rootLayout);
        setSizeFull();

        // Set focus to username
        usernameField.focus();

        if (request.getParameter("logout") != null) {
            // Show log out notification
            Notification logOutNotification = new Notification(i18n.get("account", getLocale()),
                    i18n.get("account.logout", getLocale()),
                    Notification.Type.TRAY_NOTIFICATION);
            logOutNotification.setDelayMsec(2000);
            logOutNotification.setPosition(Position.TOP_CENTER);
            logOutNotification.show(Page.getCurrent());
        }
    }

    @Override
    protected void updateMessageStrings() {
        Locale locale = languageSelector.getLocale();

        getPage().setTitle(i18n.get("login", locale));
        usernameField.setPlaceholder(i18n.get("username", locale));
        passwordField.setPlaceholder(i18n.get("password", locale));
        login.setCaption(i18n.get("login", locale));
        labelSignIn.setValue(i18n.get("sign", locale));

        // Get static html file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        String informationHTML = "static/information";
        informationLabel.setValue(HTML.loadStaticHTML(classLoader, informationHTML, locale));
    }

    /**
     * Login method to try to log in.
     */
    private void login() {
        try {
            vaadinSecurity.login(usernameField.getValue(), passwordField.getValue(), true);
        } catch (AuthenticationException ex) {
            passwordField.focus();
            usernameField.selectAll();
            passwordField.setValue("");

            // Show log in failde notification
            Notification loginFailedNotification = new Notification("",
                    i18n.get("login.failed", languageSelector.getLocale()),
                    Notification.Type.ERROR_MESSAGE);
            loginFailedNotification.setDelayMsec(2000);
            loginFailedNotification.setPosition(Position.TOP_CENTER);
            loginFailedNotification.show(Page.getCurrent());

        } catch (Exception ex) {
            Notification.show(i18n.get("error.message.unknown", languageSelector.getLocale()),
                    ex.getMessage(),
                    Notification.Type.ERROR_MESSAGE);
            LoggerFactory.getLogger(getClass()).error("Unexpected error while logging in", ex);
        } finally {
            login.setEnabled(true);
        }
    }
}
