package com.ues.fia.bad115.component;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("styles.css")

public class NavBar extends HorizontalLayout {

    public NavBar() {
        setWidth("100%");
        getStyle().set("background-color", "#4A5C79");

        H3 titulo = new H3("BIBLIOTECA CENTRAL DE CENTRO AMERICA");
        titulo.getStyle().set("color", "white");
        titulo.addClassName("navbar");

        HorizontalLayout atajos = atajos();
        atajos.setJustifyContentMode(JustifyContentMode.END);
        atajos.setWidthFull();

        add(titulo, atajos);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
    }

    private HorizontalLayout atajos() {
        Anchor home = new Anchor("home", "Inicio");
        Anchor recursos = new Anchor("recursos", "Recursos");
        Anchor usuarios = new Anchor("usuarios", "Usuarios");
        Anchor autores = new Anchor("autores", "Autores ");

        home.getStyle().set("color", "white");
        recursos.getStyle().set("color", "white");
        usuarios.getStyle().set("color", "white");
        autores.getStyle().set("color", "white");

        HorizontalLayout toolbar = new HorizontalLayout(home, recursos, usuarios, autores);
        toolbar.setSpacing(true);
        return toolbar;
    }
}

/*
public class NavBar extends VerticalLayout {
    public NavBar() {
        H1 titulo = new H1("BIBLIOTECA CENTRAL DE CENTRO AMERICA");
        titulo.getStyle().setColor("white");
        titulo.addClassName("navbar");
        add(titulo, atajos());
    }

    private HorizontalLayout atajos() {
        Anchor home = new Anchor("home", "Inicio");
        Anchor recursos = new Anchor("recursos", "Recursos");
        Anchor usuarios = new Anchor("usuarios", "Usuarios");
        Anchor autores = new Anchor("autores", "Autores");
        home.getStyle().set("color", "white");
        recursos.getStyle().set("color", "white");
        usuarios.getStyle().set("color", "white");
        autores.getStyle().set("color", "white");
        var toolbar = new HorizontalLayout(home, recursos, usuarios, autores);
        return toolbar;
    }

}
*/