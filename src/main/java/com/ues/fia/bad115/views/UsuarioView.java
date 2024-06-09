package com.ues.fia.bad115.views;

import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.UsuarioService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.ues.fia.bad115.clase.Usuario;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.icon.Icon;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.ZoneId;

import com.ues.fia.bad115.service.MoraService;
import com.ues.fia.bad115.service.PrestamoService;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.clase.Mora;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.dialog.Dialog;

@Route("usuarios")
@PageTitle(value = "Usuarios | Biblioteca Central de Centro America")
public class UsuarioView extends VerticalLayout {
    NavBar navegacion = new NavBar();
    TextField campoBusqueda = new TextField();
    Button buscar = new Button("Buscar");
    Button crearButton = new Button("Crear Usuario");
    List<Usuario> usuario;
    List<Prestamo> prestamo;
    List<Mora> mora;
    private UsuarioService usuarioService;
    private PrestamoService prestamoService;
    private MoraService moraService;
    Notification notification = new Notification();
    Grid<Prestamo> tablaPrestamos = new Grid<>(Prestamo.class);
    Grid<Mora> tablaMoras = new Grid<>(Mora.class);

    public UsuarioView(UsuarioService usuarioService, PrestamoService prestamoService, MoraService moraService) {
        setClassName("login");
        this.usuarioService = usuarioService;
        this.prestamoService = prestamoService;
        this.moraService = moraService;
        H1 titulo = new H1("Buscar Usuario");
        titulo.setClassName("titulo");
        campoBusqueda.setPlaceholder("Buscar por email o carnet");
        campoBusqueda.getElement().getThemeList().add("dark");
        campoBusqueda.setClearButtonVisible(true);
        buscar.getElement().getThemeList().add("primary");
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        crearButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("crear-usuario"));
        });
        HorizontalLayout busqueda = new HorizontalLayout();
        HorizontalLayout opciones = new HorizontalLayout();
        HorizontalLayout userDetails = new HorizontalLayout();
        userDetails.setWidth("100%");
        busqueda.add(campoBusqueda, buscar);
        opciones.setJustifyContentMode(JustifyContentMode.BETWEEN);
        opciones.getStyle().set("padding", "0px 30px");
        opciones.getStyle().set("width", "100%");
        opciones.add(busqueda, crearButton);
        add(navegacion, titulo, opciones);
        buscar.addClickListener(e -> {
            String searchTerm = campoBusqueda.getValue();
            if (searchTerm.isEmpty()) {
                Notification.show("Ingrese un término de búsqueda", 3000, Notification.Position.BOTTOM_START);
            } else {
                usuario = usuarioService.searchByName(searchTerm);
                System.out.println(usuario);
                if (usuario.isEmpty()) {
                    H3 subtitulo = new H3("No se encontraron resultados");
                    subtitulo.getStyle().setColor("white");
                    userDetails.remove();
                    userDetails.add(subtitulo);
                    Notification.show("No se encontraron resultados", 3000, Notification.Position.BOTTOM_START);
                } else {
                    userDetails.add(userDetails(usuario.get(0)));
                    prestamo = prestamoService.getPrestamosByUsuario(usuario.get(0).getId());
                    mora = moraService.findMorasByEstado(1);
                    userDetails.add(prestamos(usuario.get(0).getId()));
                    userDetails.add(moras(mora, usuario.get(0).getId()));
                    add(userDetails);
                    Notification.show("Usuario encontrado", 3000, Notification.Position.BOTTOM_START);
                }
            }
        });

    }

    private VerticalLayout userDetails(Usuario usuario) {
        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().set("text-align", "center");
        layout.getStyle().set("justify-content", "center");
        layout.setAlignItems(getDefaultHorizontalComponentAlignment());
        H3 subtitulo = new H3("Detalles del usuario");
        String estado = usuario.getCarnet().getEstado() == 1 ? "Vigente" : "Vencido";
        subtitulo.getStyle().setColor("white");
        NativeLabel nombre = new NativeLabel("Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
        NativeLabel email = new NativeLabel("Email: " + usuario.getEmail());
        NativeLabel carnet = new NativeLabel("Carnet: " + usuario.getCarnet().getId());
        NativeLabel telefono = new NativeLabel("Teléfono: " + usuario.getTelefono());
        NativeLabel tipo = new NativeLabel("Tipo de usuario: " + usuario.getTipousuario());
        NativeLabel estadoCarnet = new NativeLabel("Estado carnet: " + estado);
        String activo = usuario.getActivo() == 1 ? "Sí" : "No";
        NativeLabel activoLabel = new NativeLabel("Activo: " + activo);
        layout.add(subtitulo, nombre, email, carnet, telefono, tipo, estadoCarnet, activoLabel);
        return layout;
    }

    private VerticalLayout prestamos(int id) {

        List<Prestamo> prestamos = prestamoService.getPrestamosByUsuario(id);
        DataProvider<Prestamo, Void> dataProvider = DataProvider.fromCallbacks(
                query -> {
                    return prestamoService.getPrestamosByUsuario(id).stream();
                },
                query -> {
                    return prestamoService.getPrestamosByUsuario(id).size();
                });
        tablaPrestamos.setDataProvider(dataProvider);
        tablaPrestamos.setItems(prestamos);
        List<Grid.Column<Prestamo>> columnas = Arrays.asList(
                tablaPrestamos.getColumnByKey("id"),
                tablaPrestamos.getColumnByKey("usuario"),
                tablaPrestamos.getColumnByKey("recurso"),
                tablaPrestamos.addColumn(prestamo -> prestamo.getRecurso().getTitulo()).setHeader("Recurso"),
                tablaPrestamos.getColumnByKey("fecha"),
                tablaPrestamos.addColumn(
                        prestamo -> prestamo.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .setHeader("Fecha"),
                tablaPrestamos.getColumnByKey("devolucion"),
                tablaPrestamos.addColumn(prestamo -> prestamo.getDevolucion() == null ? "No devuelto" : "Devuelto")
                        .setHeader("Estado"));
        Grid.Column<Prestamo> idColumn = tablaPrestamos.getColumnByKey("id");
        Grid.Column<Prestamo> idusuarioColumn = tablaPrestamos.getColumnByKey("usuario");
        Grid.Column<Prestamo> idrecursoColumn = tablaPrestamos.getColumnByKey("recurso");
        Grid.Column<Prestamo> devolucionColumn = tablaPrestamos.getColumnByKey("devolucion");
        Grid.Column<Prestamo> fechaColumn = tablaPrestamos.getColumnByKey("fecha");
        fechaColumn.setVisible(false);
        idColumn.setVisible(false);
        idusuarioColumn.setVisible(false);
        idrecursoColumn.setVisible(false);
        devolucionColumn.setVisible(false);
        tablaPrestamos.setColumnOrder(columnas);
        tablaPrestamos.getElement().getThemeList().add("dark");
        tablaPrestamos.addComponentColumn(prestamo -> {
            Icon detalles = new Icon(VaadinIcon.LIST_UL);
            detalles.getStyle().set("cursor", "pointer");
            detalles.addClickListener(e -> {
                detallesPrestramos(prestamo);
            });

            Icon devolver = new Icon(VaadinIcon.CHECK);
            devolver.getStyle().set("cursor", "pointer");
            devolver.addClickListener(e -> {
                if (prestamo.getDevolucion() == null) {
                    prestamo.setDevolucion(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                    prestamoService.savePrestamo(prestamo);
                    Notification.show("Recurso devuelto", 3000, Notification.Position.BOTTOM_START);
                }
            });
            if (prestamo.getDevolucion() != null) {
                devolver.setVisible(false);
            }
            HorizontalLayout layoutBotones = new HorizontalLayout(detalles, devolver);
            layoutBotones.setSizeFull();
            layoutBotones.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            layoutBotones.setSpacing(true);
            VerticalLayout cajabotones = new VerticalLayout(layoutBotones);
            cajabotones.setAlignItems(Alignment.CENTER);
            cajabotones.setPadding(false);
            cajabotones.setSpacing(false);
            return cajabotones;
        }).setHeader("Detalles").setAutoWidth(true);
        VerticalLayout layout = new VerticalLayout();
        H3 subtitulo = new H3("Préstamos del usuario");
        subtitulo.getStyle().setColor("white");
        layout.add(subtitulo, tablaPrestamos);
        return layout;
    }

    private VerticalLayout moras(List<Mora> moras, int usuario) {
        List<Mora> morasUsuario = new ArrayList<>();
        for (int i = 0; i < moras.size(); i++) {
            Mora mora = moras.get(i);
            if (mora.getPrestamo().getUsuario().getId() == usuario) {
                morasUsuario.add(mora);
            }
        }

        DataProvider<Mora, Void> dataProvider = DataProvider.fromCallbacks(
                query -> {
                    return morasUsuario.stream();
                },
                query -> {
                    return morasUsuario.size();
                });
        tablaMoras.setDataProvider(dataProvider);
        tablaMoras.setItems(morasUsuario);
        DecimalFormat df = new DecimalFormat("#.00");
        List<Grid.Column<Mora>> columnas = Arrays.asList(
                tablaMoras.getColumnByKey("id"),
                tablaMoras.getColumnByKey("prestamo"),
                tablaMoras.addColumn(mora -> mora.getPrestamo().getRecurso().getTitulo()).setHeader("Recurso"),
                tablaMoras.getColumnByKey("fechamora"),
                tablaMoras
                        .addColumn(mora -> mora.getFechamora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .setHeader("Fecha"),
                tablaMoras.getColumnByKey("cantidad"),
                tablaMoras.addColumn(mora -> "$" + df.format(mora.getCantidad())).setHeader("Monto"),
                tablaMoras.getColumnByKey("estado"));
        Grid.Column<Mora> idColumn = tablaMoras.getColumnByKey("id");
        Grid.Column<Mora> idPrestamoColumn = tablaMoras.getColumnByKey("prestamo");
        Grid.Column<Mora> cantidadColumn = tablaMoras.getColumnByKey("cantidad");
        Grid.Column<Mora> estadoColumn = tablaMoras.getColumnByKey("estado");
        Grid.Column<Mora> fechaColumn = tablaMoras.getColumnByKey("fechamora");
        fechaColumn.setVisible(false);
        estadoColumn.setVisible(false);
        cantidadColumn.setVisible(false);
        idColumn.setVisible(false);
        idPrestamoColumn.setVisible(false);
        tablaMoras.setColumnOrder(columnas);
        tablaMoras.getElement().getThemeList().add("dark");
        tablaMoras.addComponentColumn(mora -> {
            Icon detalles = new Icon(VaadinIcon.LIST_UL);
            detalles.getStyle().set("cursor", "pointer");
            detalles.addClickListener(e -> {
                detallesMoras(mora);
            });
            Icon pagar = new Icon(VaadinIcon.CHECK);
            pagar.getStyle().set("cursor", "pointer");
            pagar.addClickListener(e -> {
                if (mora.getEstado() == 1) {
                    mora.setEstado(0);
                    moraService.saveMora(mora);
                    Notification.show("Mora pagada", 3000, Notification.Position.BOTTOM_START);
                }
            });
            if (mora.getEstado() == 0) {
                pagar.setVisible(false);
            }

            HorizontalLayout layoutBotones = new HorizontalLayout(detalles, pagar);
            layoutBotones.setSizeFull();
            layoutBotones.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            layoutBotones.setSpacing(true);

            VerticalLayout cajabotones = new VerticalLayout(layoutBotones);
            cajabotones.setAlignItems(Alignment.CENTER);
            cajabotones.setPadding(false);
            cajabotones.setSpacing(false);
            return cajabotones;
        }).setHeader("Detalles").setAutoWidth(true);
        VerticalLayout layout = new VerticalLayout();
        H3 subtitulo = new H3("Moras Activas del usuario");
        subtitulo.getStyle().setColor("white");
        layout.add(subtitulo, tablaMoras);
        return layout;
    }

    private void detallesPrestramos(Prestamo prestamo) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
        dialog.getElement().getThemeList().add("light");
        Button cerrar = new Button("Cerrar");
        cerrar.getElement().getThemeList().add("primary");
        cerrar.getStyle().set("cursor", "pointer");
        cerrar.addClickListener(e -> {
            dialog.close();
        });
        VerticalLayout layout = new VerticalLayout();
        layout.add(new H3("Detalles del préstamo"));
        layout.add(new NativeLabel("ID Recurso: " + prestamo.getRecurso().getId()));
        layout.add(new NativeLabel("Recurso: " + prestamo.getRecurso().getTitulo()));
        layout.add(new NativeLabel("Estado: " + (prestamo.getDevolucion() == null ? "No devuelto" : "Devuelto")));
        layout.add(new NativeLabel(
                "Fecha de préstamo: " + prestamo.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        if (prestamo.getDevolucion() != null) {
            layout.add(new NativeLabel("Fecha de devolución: "
                    + prestamo.getDevolucion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        }
        dialog.add(layout, cerrar);
        dialog.open();
    }

    private void detallesMoras(Mora mora) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
        dialog.getElement().getThemeList().add("light");
        Button cerrar = new Button("Cerrar");
        cerrar.getElement().getThemeList().add("primary");
        cerrar.getStyle().set("cursor", "pointer");
        cerrar.addClickListener(e -> {
            dialog.close();
        });

        Span prestamos = new Span("Ver préstamo");
        prestamos.getStyle().set("cursor", "pointer");
        prestamos.getStyle().set("color", "blue");
        prestamos.addClickListener(e -> {
            detallesPrestramos(mora.getPrestamo());
        });
        VerticalLayout layout = new VerticalLayout();
        layout.add(new H3("Detalles de la mora"));
        DecimalFormat df = new DecimalFormat("#.00");
        layout.add(new NativeLabel("ID Recurso: " + mora.getPrestamo().getRecurso().getId()));
        layout.add(new NativeLabel("Recurso: " + mora.getPrestamo().getRecurso().getTitulo()));
        layout.add(new NativeLabel("Fecha de mora: "
                + mora.getFechamora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        layout.add(new NativeLabel("Monto: $" + df.format(mora.getCantidad())));
        layout.add(new NativeLabel("Estado: " + (mora.getEstado() == 1 ? "Activa" : "Pagada")));
        layout.add(prestamos);
        dialog.add(layout, cerrar);
        dialog.open();
    }

    private void actulizarTablaPrestamos(List<Prestamo> prestamos) {
        tablaPrestamos.setItems(prestamos);
    }

}
