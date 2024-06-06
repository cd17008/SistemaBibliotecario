package com.ues.fia.bad115.views;


import com.ues.fia.bad115.clase.Editorial;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.EditorialService;
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

@Route(value = "editoriales")
@PageTitle(value = "Editoriales | Biblioteca Central de Centro America")

public class EditorialView extends VerticalLayout {
    private EditorialService editorialService;
    Grid<Editorial> tablaEditoriales = new Grid<>(Editorial.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();

    public EditorialView(EditorialService editorialService) {
        setClassName("login");
        this.editorialService = editorialService;
        List<Editorial> categorias = editorialService.getEditoriales();
        H1 subtitulo = new H1("Editoriales");
        subtitulo.getStyle().setColor("white");
        add(navegacion, subtitulo);

        List<Grid.Column<Editorial>> columnas = Arrays.asList(
                tablaEditoriales.getColumnByKey("id"),
                tablaEditoriales.getColumnByKey("nombre"));
        Grid.Column<Editorial> idColumn = tablaEditoriales.getColumnByKey("id");
        idColumn.setVisible(false);
        tablaEditoriales.setItems(categorias);
        tablaEditoriales.setColumnOrder(columnas);
        tablaEditoriales.addComponentColumn(editorial -> {
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
        tablaEditoriales.getElement().getThemeList().add("dark");
        tablaEditoriales.getElement().getStyle().setWidth("80%");
        tablaEditoriales.getStyle().set("align-self", "center");
        tablaEditoriales.getStyle().setMargin("3%");
        add(busqueda(), tablaEditoriales);
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
        tablaEditoriales.setItems(editorialService.findEditoriales(campoBusqueda.getValue()));
    }

    private void limpiar() {
        campoBusqueda.clear();
    }

}
