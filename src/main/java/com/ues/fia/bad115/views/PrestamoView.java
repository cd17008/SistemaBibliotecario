package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Recurso;
import com.ues.fia.bad115.clase.Usuario;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.service.RecursoService;
import com.ues.fia.bad115.service.UsuarioService;
import com.ues.fia.bad115.service.PrestamoService;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dependency.CssImport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Prestamo")
@CssImport(value = "styles.css")
@PageTitle(value = "Prestamo | Biblioteca Central de Centro America")
public class PrestamoView extends VerticalLayout {
    NavBar navegacion = new NavBar();
    private RecursoService recursoService;
    private UsuarioService usuarioService;
    private PrestamoService prestamoService;

    @Autowired
    public PrestamoView(RecursoService recursoService, UsuarioService usuarioService, PrestamoService prestamoService) {
        this.recursoService = recursoService;
        this.usuarioService = usuarioService;
        this.prestamoService = prestamoService;
        
        H2 titulo = new H2("Realizar Prestamo");
        titulo.setClassName("titulo");
        setClassName("login");
        add(navegacion, titulo, getFormLayout());
    }

    public FormLayout getFormLayout() {
        TextField carnet = new TextField("Carnet de usuario");
        TextField codigoR = new TextField("Codigo de recurso");
        DatePicker fechaPrestamo = new DatePicker("Fecha de prestamo");
        DatePicker fechaDevolucion = new DatePicker("Fecha de devolucion");
        fechaDevolucion.addValueChangeListener(event -> {
            fechaDevolucion.setErrorMessage(null);
            if (fechaPrestamo.getValue().isBefore(fechaDevolucion.getValue())) {
                fechaDevolucion.setInvalid(false);
            } else {
                fechaDevolucion.setErrorMessage("La fecha de prestamo debe ser antes de la fecha de devolucion");
                fechaDevolucion.setInvalid(true);
            }
        });

        fechaPrestamo.addValueChangeListener(event -> {
            fechaPrestamo.setErrorMessage(null);
            if (fechaPrestamo.getValue().isBefore(fechaDevolucion.getValue())) {
                fechaPrestamo.setInvalid(false);
            } else {
                fechaPrestamo.setErrorMessage("La fecha de prestamo debe ser antes de la fecha de devolucion");
                fechaPrestamo.setInvalid(true);
            }
        });


        Button button = new Button("Guardar");
        button.addClickListener(clickEvent -> {
            // Validar recurso
            if (recursoService.getRecurso(codigoR.getValue()) == null){
                // Recurso no existe, mostrar mensaje de error
                Notification.show("El recurso ingresado no existe", 3000, Position.TOP_CENTER);
                return;
            }

            /// Validar usuario
            if (usuarioService.getUsuarioByCarnet(carnet.getValue()) == null){
                // Usuario no existe, mostrar mensaje de error
                Notification.show("El usuario ingresado no existe", 3000, Position.TOP_CENTER);
                return;
            }
            else{
                Usuario user = usuarioService.getUsuarioByCarnet(carnet.getValue());
                if (user.activo == 0){ 
                    // Usuario no existe, mostrar mensaje de error
                    Notification.show("El usuario ingresado no esta activo", 3000, Position.TOP_CENTER);
                    return;
                }
            }

            // Validar fechas de prestamo y devolucion en caso de que no se hayan ingresado
            if (fechaPrestamo.getValue() == null || fechaDevolucion.getValue() == null){
                Notification.show("Debe ingresar la fecha de prestamo y devolucion", 3000, Position.TOP_CENTER);
                return;
            }

            // Encontrar el usuario con carnet
            Usuario usuario = usuarioService.getUsuarioByCarnet(carnet.getValue());

            // Encontrar el recurso con el código
            Recurso recurso = recursoService.getRecurso(codigoR.getValue());

            // Crear el préstamo
            Prestamo prestamo = new Prestamo();
            prestamo.setUsuario(usuario);
            prestamo.setRecurso(recurso);

            //Obtener el valor de las fechas guardarlas como string y luego convertirlas a Date

            String fechaPrestamoString = fechaPrestamo.getValue().toString();
            String fechaDevolucionString = fechaDevolucion.getValue().toString();

            //Convertir las fechas a Date
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fechaPrestamoDate = formato.parse(fechaPrestamoString);
                Date fechaDevolucionDate = formato.parse(fechaDevolucionString);
            
                prestamo.setFecha(fechaPrestamoDate);
                prestamo.setDevolucion(fechaDevolucionDate);
            } catch (ParseException e) {
                // Handle the ParseException here
                e.printStackTrace();
            } 
            // Guardar el préstamo
            prestamoService.savePrestamo(prestamo);

            Notification.show("Prestamo guardado", 3000, Position.TOP_CENTER);
        });

        FormLayout formLayout = new FormLayout();
        formLayout.add(codigoR, carnet, fechaPrestamo, fechaDevolucion, button);
        formLayout.setColspan(codigoR, 2);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 3)
        );
        formLayout.getStyle().set("padding-left", "15em");
        formLayout.getStyle().set("padding-right", "15em");
        formLayout.getElement().getThemeList().add("dark");
        return formLayout;
    }
}
