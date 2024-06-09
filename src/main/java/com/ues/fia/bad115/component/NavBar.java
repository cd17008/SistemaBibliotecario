package com.ues.fia.bad115.component;

import java.util.concurrent.ScheduledExecutorService;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import java.time.LocalDate;

@CssImport("styles.css")
public class NavBar extends VerticalLayout {

    private NativeLabel usuario;
    private ScheduledExecutorService scheduler;
    private Icon logOut = new Icon(VaadinIcon.SIGN_OUT);

    public NavBar() {
        setClassName("navbar");
        setWidth("100%");
        getStyle().set("background-color", "#1c2738");

        H3 titulo = new H3("BIBLIOTECA CENTRAL DE CENTRO AMERICA");
        titulo.getStyle().set("color", "white");
        titulo.addClassName("navbar");

        HorizontalLayout atajos = atajos();
        atajos.setJustifyContentMode(JustifyContentMode.END);
        atajos.setWidthFull();
        usuario = new NativeLabel();
        UI ui = UI.getCurrent();
        System.out.println("UI: " + ui);
        titulo.getStyle().set("margin-top", "10px");
        add(titulo, atajos);
        // startClock(ui);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.BETWEEN);

    }

    private HorizontalLayout atajos() {
        String username = (String) VaadinSession.getCurrent().getSession().getAttribute("username");
        try {
            int indice = username.indexOf("@");
            if (indice != -1) {
                username = username.substring(0, indice);
            }
        } catch (Exception e) {
            username = null;
        }
        usuario = new NativeLabel();
        usuario.setText("Usuario: " + username);
        Anchor home = new Anchor("principal", "Inicio");
        Anchor recursos = new Anchor("recursos", "Recursos");
        Anchor usuarios = new Anchor("usuarios", "Usuarios");
        Anchor prestamos = new Anchor("Prestamos", "Prestamos");
        Anchor reportes = new Anchor("reportes", "Reportes");

        logOut.addClickListener(e -> {
            VaadinSession.getCurrent().getSession().setAttribute("username", null);
            UI.getCurrent().navigate("login");
        });
        logOut.getStyle().set("cursor", "pointer");
        logOut.getStyle().set("padding", "1px");

        home.getStyle().set("color", "white");
        recursos.getStyle().set("color", "white");
        usuarios.getStyle().set("color", "white");
        prestamos.getStyle().set("color", "white");
        reportes.getStyle().set("color", "white");
        Anchor login = new Anchor("login", "Iniciar Sesión");
        login.getStyle().set("color", "#1c2738");
        login.getStyle().set("border", "1px solid white");
        login.getStyle().set("border-radius", "5px");
        login.getStyle().set("background-color", "white");
        login.getStyle().set("padding", "5px");
        HorizontalLayout toolbar = new HorizontalLayout(home, recursos, usuarios, prestamos, reportes);
        HorizontalLayout sesion;
        NativeLabel date = new NativeLabel();
        date = new NativeLabel();
        date.setText("Fecha del sistema: " + LocalDate.now().toString());
        if (username == null) {
            sesion = new HorizontalLayout(date, login);
        } else {
            sesion = new HorizontalLayout(date, usuario, logOut);
        }
        toolbar.setSpacing(true);
        toolbar.getStyle().set("margin-right", "10px");
        sesion.setSpacing(true);
        sesion.getStyle().set("margin-left", "auto");
        HorizontalLayout atajos = new HorizontalLayout(toolbar, sesion);
        atajos.setMargin(true);
        atajos.getStyle().set("padding", "0px 10px");
        atajos.setWidthFull();
        return atajos;
    }

    /*
     * private void startClock(UI ui) {
     * scheduler = Executors.newSingleThreadScheduledExecutor();
     * scheduler.scheduleAtFixedRate(() -> {
     * try {
     * LocalTime now = LocalTime.now();
     * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
     * String formattedTime = now.format(formatter);
     * System.out.println("Updating time: " + formattedTime); // Para depurar
     * 
     * ui.access(() -> {
     * try {
     * VaadinSession session = VaadinSession.getCurrent();
     * if (session != null) {
     * System.out.println("Session is not null");
     * if (session.hasLock()) {
     * System.out.println("Session has lock");
     * if (time.isAttached()) {
     * System.out.println("Time component is attached");
     * time.setText(formattedTime);
     * System.out.println("Time updated in UI: " + formattedTime); // Para depurar
     * } else {
     * System.out.println("Time component is not attached to the UI");
     * }
     * } else {
     * System.out.println("Vaadin session is not locked");
     * }
     * } else {
     * System.out.println("Vaadin session is null");
     * }
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * });
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }, 0, 1, TimeUnit.SECONDS);
     * }
     */

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        if (scheduler != null) {
            scheduler.shutdown();
        }
        super.onDetach(detachEvent);
    }
}
