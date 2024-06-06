package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Idioma;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.IdiomaService;
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

@Route(value = "idiomas")
@PageTitle(value = "Idiomas | Biblioteca Central de Centro America")

public class IdiomaView extends VerticalLayout {
    private IdiomaService idiomaService;
    Grid<Idioma> tablaIdioma = new Grid<>(Idioma.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();

    public IdiomaView(IdiomaService idiomaService) {
        setClassName("login");
        this.idiomaService = idiomaService;
        List<Idioma> idiomas = idiomaService.getIdiomas();
        H1 subtitulo = new H1("Idiomas");
        subtitulo.getStyle().setColor("white");
        add(navegacion, subtitulo);

        List<Grid.Column<Idioma>> columnas = Arrays.asList(
                tablaIdioma.getColumnByKey("id"),
                tablaIdioma.getColumnByKey("idioma"));
        Grid.Column<Idioma> idColumn = tablaIdioma.getColumnByKey("id");
        idColumn.setVisible(false);
        tablaIdioma.setItems(idiomas);
        tablaIdioma.setColumnOrder(columnas);
        tablaIdioma.addComponentColumn(idioma -> {
            Icon editar = new Icon(VaadinIcon.EDIT);

            Icon detalles = new Icon(VaadinIcon.LIST_UL);

            Icon eliminar = new Icon(VaadinIcon.TRASH);

            /*HorizontalLayout layoutBotones = new HorizontalLayout();
            layoutBotones.add(detalles, editar, eliminar);

            return layoutBotones;*/

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
        tablaIdioma.getElement().getThemeList().add("dark");
        tablaIdioma.getElement().getStyle().setWidth("80%");
        tablaIdioma.getStyle().set("align-self", "center");
        tablaIdioma.getStyle().setMargin("3%");
        add(busqueda(), tablaIdioma);
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
        tablaIdioma.setItems(idiomaService.findIdioma(campoBusqueda.getValue()));
    }

    private void limpiar() {
        campoBusqueda.clear();
    }
}
