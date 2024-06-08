package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Usuario;
import com.ues.fia.bad115.component.NavBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


import com.ues.fia.bad115.service.UsuarioService;

@Route(value = "principal")
@PageTitle(value = "Menu Principal | Biblioteca Central de Centro America")

public class PrincipalView extends VerticalLayout {
    private UsuarioService usuarioService;
    String email = (String) VaadinSession.getCurrent().getSession().getAttribute("username");
    String nombre;
    NavBar navegacion = new NavBar();

    public PrincipalView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        setClassName("login");
        Image background = new Image("images/principal.png", "Biblioteca Central");
        background.setWidth("100%");
        background.setHeight("auto");
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(email);
            nombre = " " + usuario.getNombre();
        } catch (Exception e) {
            nombre = "";
        }
        H1 title = new H1("¡Bienvenido" + nombre + "!");
        title.getStyle().setColor("white");

        H2 subtitulo = new H2("¿Qué deseas consultar?");
        subtitulo.getStyle().setColor("white");

        // Botones
        Button usuariosButton = new Button("USUARIOS");
        Button recursosButton = new Button("RECURSOS");
        Button prestamosButton = new Button("PRESTAMOS");
        Button morasButton = new Button("MORAS");

        // Estilo para los botones
        usuariosButton.addClassName("main-button");
        recursosButton.addClassName("main-button");
        prestamosButton.addClassName("main-button");
        morasButton.addClassName("main-button");

        // Layout de botones
        HorizontalLayout buttonsLayout = new HorizontalLayout(usuariosButton, recursosButton, prestamosButton,
                morasButton);
        buttonsLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        buttonsLayout.addClassName("main-buttons");

        add(navegacion, title, subtitulo, buttonsLayout);
    }

}
