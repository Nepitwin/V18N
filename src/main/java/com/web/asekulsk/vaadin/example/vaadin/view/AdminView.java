package com.web.asekulsk.vaadin.example.vaadin.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Sample user view to visualize sample data.
 *
 * @author Andreas Sekulski
 */
@SpringView(name = AdminView.VIEW_NAME)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AdminView extends HorizontalLayout implements View {

    /**
     * Defined unique view name to identify.
     */
    public static final String VIEW_NAME = "AdminView";

    /**
     * Label name from view can be used for non I18N or I18N link usage.
     */
    public static final String VIEW_DESCRIPTION = "menu.admin";

    /**
     * View menu icon.
     * <br>
     * Icons from https://vaadin.com/icons
     */
    public static final VaadinIcons VIEW_ICON = VaadinIcons.TAXI;

    /**
     * Main panel from view.
     */
    private final Panel container = new Panel();

    /**
     * ToDo := I18N
     * Home panel label name.
     */
    private static final String HOME_PANEL_NAME = "Administration Interface";

    @PostConstruct
    void init() {
        this.setSizeFull();
        this.setMargin(true);

        container.setCaption(HOME_PANEL_NAME);
        container.setIcon(VaadinIcons.HOME_O);
        container.setSizeFull();
        container.setContent(panelContent());

        this.addComponent(container);
        this.setExpandRatio(container, 1);
    }

    /**
     * Generates panel administration content.
     *
     * @return Component content from administration context.
     */
    private Component panelContent() {

        // ToDo := Mini sample

        return new Label("Hello");
    }
}

