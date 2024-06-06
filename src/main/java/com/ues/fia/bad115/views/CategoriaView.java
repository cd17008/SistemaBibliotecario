package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Categoria;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.CategoriaService;
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

@Route("categorias")

@PageTitle(value = "Categorías | Biblioteca Central de Centro America")
public class CategoriaView extends VerticalLayout {
    private CategoriaService categoriaService;
    Grid<Categoria> tablaCategorias = new Grid<>(Categoria.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();

    public CategoriaView(CategoriaService categoriaService) {
        setClassName("login");
        this.categoriaService = categoriaService;
        List<Categoria> categorias = categoriaService.getCategorias();
        H1 subtitulo = new H1("Categorías");
        subtitulo.getStyle().setColor("white");
        add(navegacion, subtitulo);

        List<Grid.Column<Categoria>> columnas = Arrays.asList(
                tablaCategorias.getColumnByKey("id"),
                tablaCategorias.getColumnByKey("nombre"),
                tablaCategorias.getColumnByKey("disponible"),
                tablaCategorias.getColumnByKey("descripcion"));
        Grid.Column<Categoria> idColumn = tablaCategorias.getColumnByKey("id");
        idColumn.setVisible(false);
        tablaCategorias.setItems(categorias);
        tablaCategorias.setColumnOrder(columnas);
        tablaCategorias.addComponentColumn(categoria -> {
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
        tablaCategorias.getElement().getThemeList().add("dark");
        tablaCategorias.getElement().getStyle().setWidth("80%");
        tablaCategorias.getStyle().set("align-self", "center");
        tablaCategorias.getStyle().setMargin("3%");
        add(busqueda(), tablaCategorias);
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
        tablaCategorias.setItems(categoriaService.findCategoria(campoBusqueda.getValue()));
    }

    private void limpiar() {
        campoBusqueda.clear();
    }

}
