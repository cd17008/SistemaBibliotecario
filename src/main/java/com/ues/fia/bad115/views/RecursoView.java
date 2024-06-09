package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.RecursoService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

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
                tablaRecursos.getColumnByKey("autor"),
                tablaRecursos.getColumnByKey("subcategoria"),
                tablaRecursos.getColumnByKey("descripcion"),
                tablaRecursos.getColumnByKey("idioma"),
                tablaRecursos.getColumnByKey("cover"),
                tablaRecursos.getColumnByKey("cantidad"),
                tablaRecursos.getColumnByKey("precio"),
                tablaRecursos.getColumnByKey("calificacion"),
                tablaRecursos.getColumnByKey("publicacion"),
                tablaRecursos.getColumnByKey("editorial"));

        Grid.Column<Recurso> idColumn = tablaRecursos.getColumnByKey("id");
        idColumn.setVisible(false);
        tablaRecursos.setItems(recursos);
        tablaRecursos.setColumnOrder(columnas);
        tablaRecursos.addComponentColumn(recurso -> {
            Icon editar = new Icon(VaadinIcon.EDIT);

            Icon detalles = new Icon(VaadinIcon.LIST_UL);

            Icon eliminar = new Icon(VaadinIcon.TRASH);

            HorizontalLayout layoutBotones = new HorizontalLayout(detalles, editar, eliminar);
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
        campoBusqueda.getStyle().setBorder("1px solid #ccc");
        campoBusqueda.getStyle().set("border-radius", "5px");
        campoBusqueda.getStyle().setBackgroundColor("white");
        Icon vaciar = new Icon(VaadinIcon.CLOSE_CIRCLE_O);

        vaciar.addClickListener(e -> limpiar());

        var buscar = new HorizontalLayout(campoBusqueda, vaciar);
        return buscar;
    }

    private void actulizarTabla() {
        tablaRecursos.setItems(recursoService.findRecursos(campoBusqueda.getValue()));
    }

    private void limpiar() {
        campoBusqueda.clear();
    }
}
