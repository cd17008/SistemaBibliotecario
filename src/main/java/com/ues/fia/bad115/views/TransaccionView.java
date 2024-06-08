package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Transaccion;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.TransaccionService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Route("transacciones")
@PageTitle(value = "Transacciones | Biblioteca Central de Centro America")
public class TransaccionView extends VerticalLayout {
    private TransaccionService transaccionService;
    NavBar navegacion = new NavBar();
    Grid<Transaccion> tablaTransacciones = new Grid<>(Transaccion.class);
    ComboBox<String> comboTransacciones = new ComboBox<>("Filtrar");

    @Autowired
    public TransaccionView(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
        List<Transaccion> transacciones = transaccionService.getTransacciones();
        H1 titulo = new H1("Transacciones");
        titulo.setClassName("titulo");
        setClassName("login");
        DataProvider<Transaccion, Void> dataProvider = DataProvider.fromCallbacks(
                query -> {
                    return transaccionService.getTransacciones().stream();
                },
                query -> {
                    return transaccionService.getTransacciones().size();
                });
        tablaTransacciones.setDataProvider(dataProvider);
        List<String> tipos = Arrays.asList("Salida", "Entrada");
        comboTransacciones.setItems(tipos);
        comboTransacciones.setPlaceholder("Seleccione un tipo");
        comboTransacciones.getElement().getStyle().setColor("white");
        comboTransacciones.getElement().getThemeList().add("dark");

        List<Grid.Column<Transaccion>> columnas = Arrays.asList(
                tablaTransacciones.getColumnByKey("id"),
                tablaTransacciones.getColumnByKey("tipo"),
                tablaTransacciones.getColumnByKey("fecha"),
                tablaTransacciones.getColumnByKey("monto"),
                tablaTransacciones.getColumnByKey("idusuario"),
                tablaTransacciones.getColumnByKey("descripcion"));
        Grid.Column<Transaccion> idColumn = tablaTransacciones.getColumnByKey("id");
        Grid.Column<Transaccion> idusuarioColumn = tablaTransacciones.getColumnByKey("idusuario");
        idColumn.setVisible(false);
        idusuarioColumn.setVisible(false);
        tablaTransacciones.setItems(transacciones);
        tablaTransacciones.setColumnOrder(columnas);

        tablaTransacciones.getElement().getThemeList().add("dark");
        tablaTransacciones.getElement().getStyle().setWidth("80%");
        tablaTransacciones.getStyle().set("align-self", "center");
        tablaTransacciones.getStyle().setMargin("3%");

        tablaTransacciones.addComponentColumn(transaccion -> {
            Icon editar = new Icon(VaadinIcon.EDIT);
            editar.addClickListener(e -> {
                System.out.println(transaccion.getMonto());
            });
            editar.getStyle().set("cursor", "pointer");
            Icon detalles = new Icon(VaadinIcon.LIST_UL);
            detalles.getStyle().set("cursor", "pointer");
            detalles.addClickListener(e -> {
                detallesTransaccion(transaccion);
            });
            Icon eliminar = new Icon(VaadinIcon.TRASH);
            eliminar.getStyle().set("cursor", "pointer");
            eliminar.addClickListener(e -> {
                transaccionService.deleteTransaccion(transaccion.getId());
                actulizarTabla();
            });

            HorizontalLayout layoutBotones = new HorizontalLayout(detalles);
            layoutBotones.setSizeFull();
            layoutBotones.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            layoutBotones.setSpacing(true);

            VerticalLayout cajabotones = new VerticalLayout(layoutBotones);
            cajabotones.setAlignItems(Alignment.CENTER);
            cajabotones.setPadding(false);
            cajabotones.setSpacing(false);

            return cajabotones;
        }).setHeader("Detalle").setAutoWidth(true);

        comboTransacciones.getStyle().set("align-self", "center");
        comboTransacciones.setClearButtonVisible(true);

        comboTransacciones.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                tablaTransacciones.setItems(transaccionService.getTransaccionTipo(e.getValue()));
            } else {
                actulizarTabla();
            }
        });

        add(navegacion, titulo, comboTransacciones, tablaTransacciones);
    }

    private void actulizarTabla() {
        tablaTransacciones.setItems(transaccionService.getTransacciones());
    }

    private void detallesTransaccion(Transaccion transaccion) {
        Dialog dialogo = new Dialog();
        dialogo.setCloseOnOutsideClick(true);
        dialogo.setCloseOnEsc(true);
        dialogo.getElement().getThemeList().add("light");
        LocalDate fecha = transaccion.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime hora = transaccion.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        System.out.println(transaccion.getFecha());
        VerticalLayout detallesLayout = new VerticalLayout();
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.USER),
                new Span("Usuario: " + transaccion.getIdusuario().getNombre() + " "
                        + transaccion.getIdusuario().getApellido())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.DOLLAR),
                new Span("Monto: $" + transaccion.getMonto())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.CALENDAR),
                new Span("Fecha: " + fecha)));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.CLOCK),
                new Span("Hora: " + hora)));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.FILE_TEXT),
                new Span("Descripción: " + transaccion.getDescripcion())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.CLIPBOARD_TEXT),
                new Span("Tipo: " + transaccion.getTipo())));
        Button cerrar = new Button("Cerrar", new Icon(VaadinIcon.CLOSE));
        cerrar.getStyle().set("cursor", "pointer");
        cerrar.addClickListener(e -> {
            dialogo.close();
        });
        detallesLayout.add(cerrar);
        dialogo.add(detallesLayout);
        dialogo.open();

    }

}
