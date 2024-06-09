package com.ues.fia.bad115.views;

import com.ues.fia.bad115.component.NavBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@Route("usuarios")
@PageTitle(value = "Usuarios | Biblioteca Central de Centro America")
public class UsuarioView extends VerticalLayout {
    NavBar navegacion = new NavBar();

    public UsuarioView() {
        setClassName("login");
        

        add(navegacion);
    }
    
}
