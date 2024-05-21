package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.AutorService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.List;

@Route(value="main")
@PageTitle(value = "Menu Principal|Biblioteca Central de Centro America")
public class MainView  extends VerticalLayout {

    public MainView(){
        getStyle().setBackground(String.valueOf(Color.darkGray));
        NavBar navBar = new NavBar();
        add(navBar);


    }

}
