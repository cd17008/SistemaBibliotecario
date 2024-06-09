package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.RecursoService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;

import java.util.Arrays;
import java.util.List;

@Route(value = "recursos")
@PageTitle(value = "Recursos | Biblioteca Central de Centro America")
public class RecursoView extends VerticalLayout {
    private RecursoService recursoService;
    Grid<Recurso> tablaRecursos = new Grid<>(Recurso.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();

    public RecursoView(RecursoService recursoService) {
        setClassName("login");
        this.recursoService = recursoService;
        if (recursoService == null) {
            throw new IllegalArgumentException("RecursoService no puede ser null");
        }

        List<Recurso> recursos = recursoService.getRecursos();
        if (recursos == null) {
            throw new IllegalStateException("El servicio de recursos devolvió null");
        }
        H1 subtitulo = new H1("Recursos");
        subtitulo.getStyle().setColor("white");
        add(navegacion, subtitulo);

        List<Grid.Column<Recurso>> columnas = Arrays.asList(
                tablaRecursos.getColumnByKey("id"),
                tablaRecursos.getColumnByKey("titulo"),
                tablaRecursos.getColumnByKey("editorial"),
                tablaRecursos.addColumn(recurso -> recurso.getEditorial().getNombre()).setHeader("Editorial"),
                tablaRecursos.getColumnByKey("autor"),
                tablaRecursos
                        .addColumn(recurso -> recurso.getAutor().getNombre() + " " + recurso.getAutor().getApellido())
                        .setHeader("Autor"),
                tablaRecursos
                        .addColumn(subcategoria -> subcategoria.getSubcategoria().getCategoria().getDisponible() == 1
                                ? "Disponible"
                                : "No disponible")
                        .setHeader("Prestamo"),
                tablaRecursos.getColumnByKey("subcategoria"),
                tablaRecursos.getColumnByKey("descripcion"),
                tablaRecursos.getColumnByKey("idioma"),
                tablaRecursos.getColumnByKey("cover"),
                tablaRecursos.getColumnByKey("cantidad"),
                tablaRecursos.getColumnByKey("precio"),
                tablaRecursos.getColumnByKey("calificacion"),
                tablaRecursos.getColumnByKey("publicacion"));

        Grid.Column<Recurso> idColumn = tablaRecursos.getColumnByKey("id");
        idColumn.setVisible(false);
        Grid.Column<Recurso> coverColumn = tablaRecursos.getColumnByKey("cover");
        coverColumn.setVisible(false);
        Grid.Column<Recurso> autorColumn = tablaRecursos.getColumnByKey("autor");
        autorColumn.setVisible(false);
        Grid.Column<Recurso> subcategoriaColumn = tablaRecursos.getColumnByKey("subcategoria");
        subcategoriaColumn.setVisible(false);
        Grid.Column<Recurso> descripcionColumn = tablaRecursos.getColumnByKey("descripcion");
        descripcionColumn.setVisible(false);
        Grid.Column<Recurso> precioColumn = tablaRecursos.getColumnByKey("precio");
        precioColumn.setVisible(false);
        Grid.Column<Recurso> idiomaColumn = tablaRecursos.getColumnByKey("idioma");
        idiomaColumn.setVisible(false);
        Grid.Column<Recurso> editorialColumn = tablaRecursos.getColumnByKey("editorial");
        editorialColumn.setVisible(false);
        Grid.Column<Recurso> cantidadColumn = tablaRecursos.getColumnByKey("cantidad");
        cantidadColumn.setVisible(false);
        Grid.Column<Recurso> publicacionColumn = tablaRecursos.getColumnByKey("publicacion");
        publicacionColumn.setVisible(false);
        Grid.Column<Recurso> calificacionColumn = tablaRecursos.getColumnByKey("calificacion");
        calificacionColumn.setVisible(false);
        tablaRecursos.setItems(recursos);
        tablaRecursos.setColumnOrder(columnas);
        tablaRecursos.addComponentColumn(recurso -> {
            Icon detalles = new Icon(VaadinIcon.LIST_UL);
            detalles.getStyle().set("cursor", "pointer");
            detalles.addClickListener(e -> {
                detallesRecurso(recurso);
            });
            Icon prestamo = new Icon(VaadinIcon.BOOK);
            prestamo.getStyle().set("cursor", "pointer");
            prestamo.addClickListener(e -> {
                UI.getCurrent().navigate(PrestamoView.class, recurso.getId());
            });
            HorizontalLayout layoutBotones = new HorizontalLayout(detalles, prestamo);
            layoutBotones.setSizeFull();
            layoutBotones.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            layoutBotones.setSpacing(true);

            VerticalLayout cajabotones = new VerticalLayout(layoutBotones);
            cajabotones.setAlignItems(Alignment.CENTER);
            cajabotones.setPadding(false);
            cajabotones.setSpacing(false);

            return cajabotones;
        }).setHeader("Acciones").setAutoWidth(true);
        tablaRecursos.getElement().getThemeList().add("dark");
        tablaRecursos.getElement().getStyle().setWidth("80%");
        tablaRecursos.getStyle().set("align-self", "center");
        tablaRecursos.getStyle().setMargin("3%");
        add(busqueda(), tablaRecursos);
        actulizarTabla();
    }

    private HorizontalLayout busqueda() {
        campoBusqueda.setPlaceholder("Escribe un nombre");
        campoBusqueda.setValueChangeMode(ValueChangeMode.LAZY);
        campoBusqueda.addValueChangeListener(event -> actulizarTabla());
        campoBusqueda.getElement().getThemeList().add("dark");
        campoBusqueda.setClearButtonVisible(true);
        var buscar = new HorizontalLayout(campoBusqueda);
        return buscar;
    }

    private void actulizarTabla() {
        tablaRecursos.setItems(recursoService.findRecursos(campoBusqueda.getValue()));
    }

    private void detallesRecurso(Recurso recurso) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
        dialog.getElement().getThemeList().add("light");
        String disponible = recurso.getSubcategoria().getCategoria().getDisponible() == 1 ? "Disponible"
                : "No disponible";
        String autor;
        if (recurso.getAutor().getApellido() == null)
            autor = recurso.getAutor().getNombre();
        else {
            autor = recurso.getAutor().getNombre() + " " + recurso.getAutor().getApellido();
        }
        VerticalLayout layout = new VerticalLayout();
        VerticalLayout layout2 = new VerticalLayout();
        HorizontalLayout layout3 = new HorizontalLayout();

        dialog.add(new H3("Detalles del recurso"));
        layout.add(new HorizontalLayout(new Icon(VaadinIcon.BOOK), new Span(recurso.getTitulo())));
        layout.add(new HorizontalLayout(new Icon(VaadinIcon.USER),
                new Span(autor)));
        layout.add(new HorizontalLayout(new Icon(VaadinIcon.CALENDAR),
                new Span(String.valueOf(recurso.getPublicacion()))));
        layout.add(new HorizontalLayout(new Icon(VaadinIcon.BOOK),
                new Span(recurso.getSubcategoria().getCategoria().getNombre())));
        layout.add(new HorizontalLayout(new Icon(VaadinIcon.BOOK), new Span(recurso.getSubcategoria().getNombre())));
        layout2.add(new HorizontalLayout(new Icon(VaadinIcon.CHECK_CIRCLE), new Span(disponible)));
        layout2.add(new HorizontalLayout(new Icon(VaadinIcon.OPEN_BOOK), new Span(recurso.getEditorial().getNombre())));
        layout2.add(new HorizontalLayout(new Icon(VaadinIcon.TEXT_LABEL), new Span(recurso.getIdioma().getIdioma())));
        layout2.add(new HorizontalLayout(new Icon(VaadinIcon.FILE_TEXT), new Span(recurso.getDescripcion())));
        layout2.add(
                new HorizontalLayout(new Icon(VaadinIcon.RECORDS), new Span(String.valueOf(recurso.getCantidad()))));
        layout2.add(
                new HorizontalLayout(new Icon(VaadinIcon.DOLLAR), new Span("$" + String.valueOf(recurso.getPrecio()))));
        layout.add(
                new HorizontalLayout(new Icon(VaadinIcon.STAR), new Span(String.valueOf(recurso.getCalificacion()))));

        layout3.add(layout, layout2);
        dialog.add(layout3);
        dialog.open();
    }
}
