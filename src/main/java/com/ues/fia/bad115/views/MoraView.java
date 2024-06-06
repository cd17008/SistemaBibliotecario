package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Mora;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.MoraService;
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

@Route("moras")

@PageTitle(value = "Moras | Biblioteca Central de Centro America")
public class MoraView extends VerticalLayout {
    private MoraService moraService;
    Grid<Mora> tablaMoras = new Grid<>(Mora.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();

    public MoraView(MoraService moraService) {
        setClassName("login");
        this.moraService = moraService;
        List<Mora> moras = moraService.getMoras();
        H1 subtitulo = new H1("Moras");
        subtitulo.getStyle().setColor("white");
        add(navegacion, subtitulo);

        List<Grid.Column<Mora>> columnas = Arrays.asList(
                tablaMoras.getColumnByKey("id"),
                tablaMoras.getColumnByKey("cantidad"),
                tablaMoras.getColumnByKey("idprestamo"),
                tablaMoras.getColumnByKey("estado"),
                tablaMoras.getColumnByKey("fecha"));
        Grid.Column<Mora> idColumn = tablaMoras.getColumnByKey("id");
        idColumn.setVisible(false);
        tablaMoras.setItems(moras);
        tablaMoras.setColumnOrder(columnas);
        tablaMoras.addComponentColumn(mora -> {
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
        tablaMoras.getElement().getThemeList().add("dark");
        tablaMoras.getElement().getStyle().setWidth("80%");
        tablaMoras.getStyle().set("align-self", "center");
        tablaMoras.getStyle().setMargin("3%");
        add(busqueda(), tablaMoras);
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
        tablaMoras.setItems(moraService.findMorasByCantidad(Float.parseFloat(campoBusqueda.getValue())));
    }

    private void limpiar() {
        campoBusqueda.clear();
    }
}
