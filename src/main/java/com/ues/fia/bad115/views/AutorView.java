package com.ues.fia.bad115.views;


import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.AutorService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import java.util.List;

@Route(value="autores")
@PageTitle(value = "Autores|Biblioteca Central de Centro America")
public class AutorView extends VerticalLayout {
    private AutorService autorService;
    Grid<Autor> tablaAutores = new Grid<>(Autor.class);
    TextField campoBusqueda = new TextField();
    NavBar navegacion = new NavBar();


    public AutorView(AutorService autorService){
        this.autorService = autorService;
        List<Autor> autores = autorService.getAutores();
        ListDataProvider<Autor> lista = new ListDataProvider<>(autores);

        add(navegacion, new H1("Autores"));

        tablaAutores.setItems(autores);
        tablaAutores.setColumnOrder(tablaAutores.getColumnByKey("id"),
                tablaAutores.getColumnByKey("nombre"),
                tablaAutores.getColumnByKey("apellido"),
                tablaAutores.getColumnByKey("pais"));
        tablaAutores.addComponentColumn(autor -> {
            Icon editar = new Icon(VaadinIcon.EDIT);
            editar.setColor("green");
            Icon detalles = new Icon(VaadinIcon.LIST_UL);
            detalles.setColor("blue");
            Icon eliminar = new Icon(VaadinIcon.TRASH);
            eliminar.setColor("red");
            HorizontalLayout layoutBotones = new HorizontalLayout();
            layoutBotones.add(detalles, editar, eliminar);

            return layoutBotones;
        }).setHeader("Acciones").setAutoWidth(true);

        add(busqueda(),tablaAutores);
        actulizarTabla();
    }

    private HorizontalLayout busqueda(){
        campoBusqueda.setPlaceholder("Escribe un nombre");
        campoBusqueda.setValueChangeMode(ValueChangeMode.LAZY);
        campoBusqueda.addValueChangeListener(event ->actulizarTabla());

        Icon vaciar = new Icon(VaadinIcon.CLOSE_CIRCLE_O);

        vaciar.addClickListener(e->limpiar());

        var buscar = new HorizontalLayout(campoBusqueda,vaciar);
        return buscar;
    }
    private void actulizarTabla(){
        tablaAutores.setItems(autorService.findAutores(campoBusqueda.getValue()));
    }
    private void limpiar(){
        campoBusqueda.clear();
    }
}
