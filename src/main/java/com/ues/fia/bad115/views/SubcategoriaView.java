package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Subcategoria;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.SubcategoriaService;
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

@Route("subcategorias")

@PageTitle(value = "Subcategorías | Biblioteca Central de Centro America")
public class SubcategoriaView extends VerticalLayout {
    private SubcategoriaService subcategoriaService;
    Grid<Subcategoria> tablaSubcategorias = new Grid<>(Subcategoria.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();

    public SubcategoriaView(SubcategoriaService subcategoriaService) {
        setClassName("login");
        this.subcategoriaService = subcategoriaService;
        List<Subcategoria> categorias = subcategoriaService.getSubcategorias();
        H1 subtitulo = new H1("Subcategorías");
        subtitulo.getStyle().setColor("white");
        add(navegacion, subtitulo);

        List<Grid.Column<Subcategoria>> columnas = Arrays.asList(
                tablaSubcategorias.getColumnByKey("id"),
                tablaSubcategorias.getColumnByKey("nombre"),
                tablaSubcategorias.getColumnByKey("categoria"),
                tablaSubcategorias.addColumn(subcategoria -> subcategoria.getCategoria().getNombre())
                        .setHeader("Categoria"),
                tablaSubcategorias.getColumnByKey("descripcion"));
        Grid.Column<Subcategoria> idColumn = tablaSubcategorias.getColumnByKey("id");
        idColumn.setVisible(false);
        Grid.Column<Subcategoria> categoriaColumn = tablaSubcategorias.getColumnByKey("categoria");
        categoriaColumn.setVisible(false);
        tablaSubcategorias.setItems(categorias);
        tablaSubcategorias.setColumnOrder(columnas);
        tablaSubcategorias.addComponentColumn(subcategoria -> {
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
        tablaSubcategorias.getElement().getThemeList().add("dark");
        tablaSubcategorias.getElement().getStyle().setWidth("80%");
        tablaSubcategorias.getStyle().set("align-self", "center");
        tablaSubcategorias.getStyle().setMargin("3%");
        add(busqueda(), tablaSubcategorias);
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
        tablaSubcategorias.setItems(subcategoriaService.findSubcategorias(campoBusqueda.getValue()));
    }

    private void limpiar() {
        campoBusqueda.clear();
    }
}
