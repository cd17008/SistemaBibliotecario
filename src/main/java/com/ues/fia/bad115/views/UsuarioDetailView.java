package com.ues.fia.bad115.views;

import com.vaadin.flow.router.Route;
import com.ues.fia.bad115.component.NavBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Route("crear-usuario")
public class UsuarioDetailView extends VerticalLayout {
    NavBar navegacion = new NavBar();

    public UsuarioDetailView() {
        addClassName("login");

        add(navegacion);
    }

}
