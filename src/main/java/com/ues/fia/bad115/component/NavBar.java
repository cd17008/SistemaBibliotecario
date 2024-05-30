package com.ues.fia.bad115.component;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("styles.css")
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
