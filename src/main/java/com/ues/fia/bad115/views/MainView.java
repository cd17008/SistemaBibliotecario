package com.ues.fia.bad115.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.awt.*;

@Route(value = "main")
@CssImport(value = "styles.css")
@PageTitle(value = "Menu Principal | Biblioteca Central de Centro America")
public class MainView extends VerticalLayout {

    public MainView() {
        getStyle().setBackground(String.valueOf(Color.darkGray));

    }

}
