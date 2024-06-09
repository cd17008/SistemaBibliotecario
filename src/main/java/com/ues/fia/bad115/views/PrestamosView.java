package com.ues.fia.bad115.views;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.PrestamoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route("Prestamos")
@PageTitle(value = "Prestamos | Biblioteca Central de Centro America")
public class PrestamosView extends VerticalLayout{
    private PrestamoService prestamoService;
    NavBar navegacion = new NavBar();
    Grid<Prestamo> tablaPrestamos = new Grid<>(Prestamo.class);
    TextField comboPrestamos = new TextField ("Filtrar");
    //Filtrar por fecha de prestamo
    DatePicker fechaPrestamo = new DatePicker("Fecha de prestamo");
    //Filtrar por fecha de devolucion
    DatePicker fechaDevolucion = new DatePicker("Fecha de devolucion");

    @Autowired
    public PrestamosView (PrestamoService prestamoService) {

        comboPrestamos.setPlaceholder("Usuario o Recurso");
        comboPrestamos.getElement().getStyle().setColor("white");
        comboPrestamos.getElement().getThemeList().add("dark");

        fechaPrestamo.getElement().getStyle().set("align-self", "center");
        fechaPrestamo.setClearButtonVisible(true);
        fechaPrestamo.getElement().getThemeList().add("dark");

        fechaDevolucion.getElement().getStyle().set("align-self", "center");
        fechaDevolucion.setClearButtonVisible(true);
        fechaDevolucion.getElement().getThemeList().add("dark");


        this.prestamoService = prestamoService;
        List<Prestamo> prestamos = prestamoService.getPrestamos();
        H2 titulo = new H2("Prestamos");
        titulo.setClassName("titulo");
        setClassName("login");
        DataProvider<Prestamo, Void> dataProvider = DataProvider.fromCallbacks(
                query -> {
                    return prestamoService.getPrestamos().stream();
                },
                query -> {
                    return prestamoService.getPrestamos().size();
                });
        tablaPrestamos.setDataProvider(dataProvider);

        List<Grid.Column<Prestamo>> columnas = Arrays.asList(
                tablaPrestamos.getColumnByKey("id"),
                tablaPrestamos.getColumnByKey("usuario"),
                tablaPrestamos.addColumn(prestamo -> prestamo.getUsuario().getNombre())
                .setHeader("Usuario"),
                tablaPrestamos.getColumnByKey("recurso"),
                tablaPrestamos.addColumn(prestamo -> prestamo.getRecurso().getTitulo())
                .setHeader("Recurso"),
                tablaPrestamos.getColumnByKey("fecha"),
                tablaPrestamos.getColumnByKey("devolucion"));
                        
                //Modificar la columna fecha para que muestre el formato de fecha correcto

        Grid.Column<Prestamo> idColumn = tablaPrestamos.getColumnByKey("id");
        Grid.Column<Prestamo> prestamoColumn = tablaPrestamos.getColumnByKey("usuario");
        Grid.Column<Prestamo> recursoColumn = tablaPrestamos.getColumnByKey("recurso");
        prestamoColumn.setVisible(false);
        recursoColumn.setVisible(false);
        if (idColumn != null) {
            idColumn.setVisible(false);
        }
        idColumn.setVisible(false);
        tablaPrestamos.setItems(prestamos);
        tablaPrestamos.setColumnOrder(columnas);

        tablaPrestamos.getElement().getThemeList().add("dark");
        tablaPrestamos.getElement().getStyle().setWidth("80%");
        tablaPrestamos.getStyle().set("align-self", "center");
        tablaPrestamos.getStyle().setMargin("3%");

        tablaPrestamos.addComponentColumn(prestamo -> {
            Icon detalles = new Icon(VaadinIcon.LIST_UL);
            detalles.getStyle().set("cursor", "pointer");
            detalles.addClickListener(e -> {
                detallesPrestamo(prestamo);
            });
            Icon eliminar = new Icon(VaadinIcon.TRASH);
            eliminar.getStyle().set("cursor", "pointer");
            eliminar.addClickListener(e -> {
                confirmarEliminar(prestamo);
                actulizarTabla();
            });

            HorizontalLayout layoutBotones = new HorizontalLayout(detalles, eliminar);
            layoutBotones.setSizeFull();
            layoutBotones.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            layoutBotones.setSpacing(true);

            VerticalLayout cajabotones = new VerticalLayout(layoutBotones);
            cajabotones.setAlignItems(Alignment.CENTER);
            cajabotones.setPadding(false);
            cajabotones.setSpacing(false);

            return cajabotones;
        }).setHeader("Detalle").setAutoWidth(true);

        comboPrestamos.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                tablaPrestamos.setItems(prestamoService.findPrestamos(e.getValue()));
            } else {
                actulizarTabla();
            }
        });

        // fechaPrestamo.addValueChangeListener(e -> {
        //     if (e.getValue() != null) {
        //         tablaPrestamos.setItems(prestamoService.findPrestamosFecha(e.getValue()));
        //     } else {
        //         actulizarTabla();
        //     }
        // });

        add(navegacion, titulo, comboPrestamos, tablaPrestamos);
    }

    private void actulizarTabla() {
        tablaPrestamos.setItems(prestamoService.findPrestamos(comboPrestamos.getValue()));
    }

    private void detallesPrestamo(Prestamo prestamo) {
        Dialog dialogo = new Dialog();
        dialogo.setCloseOnOutsideClick(true);
        dialogo.setCloseOnEsc(true);
        dialogo.getElement().getThemeList().add("light");
        LocalDate fecha = prestamo.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate devolucion = prestamo.getDevolucion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        VerticalLayout detallesLayout = new VerticalLayout();
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.USER),
                new Span("Usuario: " + prestamo.getUsuario().getNombre() + " "
                        + prestamo.getUsuario().getApellido())));
        //Agregar email de usuario y telefono
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.ENVELOPE),
                new Span("Email: " + prestamo.getUsuario().getEmail())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.PHONE),
                new Span("Telefono: " + prestamo.getUsuario().getTelefono())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.DOLLAR),
                new Span("Titutlo: " + prestamo.getRecurso().getTitulo())));
        //Agregar decripcion de recurso, autor y categoria
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.BOOK),
                new Span("Descripcion: " + prestamo.getRecurso().getDescripcion())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.USER),
                new Span("Autor: " + prestamo.getRecurso().getAutor().getNombre())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.TAG),
                new Span("Categoria: " + prestamo.getRecurso().getSubcategoria().getNombre())));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.CALENDAR),
                new Span("Fecha de prestamo: " + fecha)));
        detallesLayout.add(new HorizontalLayout(new Icon(VaadinIcon.CLOCK),
                new Span("Fecha de devolucion: " + devolucion)));
        Button cerrar = new Button("Cerrar", new Icon(VaadinIcon.CLOSE));
        cerrar.getStyle().set("cursor", "pointer");
        cerrar.addClickListener(e -> {
            dialogo.close();
        });
        detallesLayout.add(cerrar);
        dialogo.add(detallesLayout);
        dialogo.open();
    }

    //Dialogo de confirmacion para eliminar un prestamo
    private void confirmarEliminar(Prestamo prestamo) {
        Dialog dialogo = new Dialog();
        dialogo.setCloseOnOutsideClick(true);
        dialogo.setCloseOnEsc(true);
        dialogo.getElement().getThemeList().add("light");
        VerticalLayout detallesLayout = new VerticalLayout();
        detallesLayout.add(new Span("¿Estas seguro de eliminar el prestamo?"));
        Button confirmar = new Button("Confirmar", new Icon(VaadinIcon.CHECK));
        confirmar.getStyle().set("cursor", "pointer");
        confirmar.addClickListener(e -> {
            prestamoService.deletePrestamo(prestamo.getId());
            actulizarTabla();
            dialogo.close();
        });
        Button cancelar = new Button("Cancelar", new Icon(VaadinIcon.CLOSE));
        cancelar.getStyle().set("cursor", "pointer");
        cancelar.addClickListener(e -> {
            dialogo.close();
        });
        detallesLayout.add(new HorizontalLayout(confirmar, cancelar));
        dialogo.add(detallesLayout);
        dialogo.open();
    }
}
