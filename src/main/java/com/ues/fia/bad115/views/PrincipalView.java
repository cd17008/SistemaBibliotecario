package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Usuario;
import com.ues.fia.bad115.component.NavBar;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


import com.ues.fia.bad115.service.UsuarioService;

@Route(value = "principal")
@PageTitle(value = "Menu Principal | Biblioteca Central de Centro America")

public class PrincipalView extends VerticalLayout {
    @SuppressWarnings("unused")
    private UsuarioService usuarioService;
    String email = (String) VaadinSession.getCurrent().getSession().getAttribute("username");
    String nombre;
    NavBar navegacion = new NavBar();

    public PrincipalView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        setClassName("principal-view");

        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(email);
            nombre = " " + usuario.getNombre();
        } catch (Exception e) {
            nombre = "";
        }

        H1 title = new H1("¡Bienvenido" + nombre + "!");
        title.getStyle().setColor("white");

        H3 subtitulo = new H3("¿Qué deseas consultar?");
        subtitulo.getStyle().setColor("white");

        // Botones
        Button usuariosButton = new Button("USUARIOS", new Icon(VaadinIcon.USERS));
        Button recursosButton = new Button("RECURSOS", new Icon(VaadinIcon.BOOK));
        Button prestamosButton = new Button("PRESTAMOS", new Icon(VaadinIcon.ARROW_RIGHT));
        Button reporButton = new Button("REPORTES", new Icon(VaadinIcon.CHART));
        //Button morasButton = new Button("MORAS");

        // Estilo para los botones
        usuariosButton.getStyle().set("font-size", "2em");
        recursosButton.getStyle().set("font-size", "2em");
        prestamosButton.getStyle().set("font-size", "2em");
        reporButton.getStyle().set("font-size", "2em");

        usuariosButton.addClassName("main-button");
        usuariosButton.addClickListener(e -> {
            usuariosButton.getUI().ifPresent(ui -> ui.navigate("usuarios"));
        });
        recursosButton.addClassName("main-button");
        recursosButton.addClickListener(e -> {
            recursosButton.getUI().ifPresent(ui -> ui.navigate("recursos"));
        });
        prestamosButton.addClassName("main-button");
        prestamosButton.addClickListener(e -> {
            prestamosButton.getUI().ifPresent(ui -> ui.navigate("prestamos"));
        });
        /*morasButton.addClassName("main-button");
        morasButton.addClickListener(e -> {
            morasButton.getUI().ifPresent(ui -> ui.navigate("moras"));
        });*/
        reporButton.addClassName("main-button");
        reporButton.addClickListener(e -> {
            reporButton.getUI().ifPresent(ui -> ui.navigate("reportes"));
        });

        // Layout de botones
        /*HorizontalLayout buttonsLayout = new HorizontalLayout(usuariosButton, recursosButton, prestamosButton,
                morasButton, reporButton);
        buttonsLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        buttonsLayout.addClassName("main-buttons");*/
        HorizontalLayout firstRow = new HorizontalLayout(usuariosButton, recursosButton);
        HorizontalLayout secondRow = new HorizontalLayout(prestamosButton, reporButton);

        firstRow.setJustifyContentMode(JustifyContentMode.CENTER);
        secondRow.setJustifyContentMode(JustifyContentMode.CENTER);

        add(navegacion, title, subtitulo, firstRow, secondRow);
    }

}
