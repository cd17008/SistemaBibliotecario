package com.ues.fia.bad115.views;

import com.ues.fia.bad115.clase.Autor;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.AutorService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.List;

@Route(value="main")
@CssImport(value = "styles.css")
@PageTitle(value = "Menu Principal | Biblioteca Central de Centro America")
public class MainView  extends VerticalLayout {

    public MainView(){
        getStyle().setBackground(String.valueOf(Color.darkGray));
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Iniciar Sesión");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Ingresar");
        i18nForm.setForgotPassword("¿Contraseña Olvidada?");
        i18n.setForm(i18nForm);
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Usuario o contraseña inválidos");
        i18nErrorMessage.setMessage("Revisa tu usuario y contraseña. Si no recuerdas tu contraseña puedes reestablecerla");
        i18n.setErrorMessage(i18nErrorMessage);
        LoginForm login = new LoginForm();
        login.setClassName("login");
        login.setI18n(i18n);
        login.getElement().getThemeList().add("dark");
        H1 titulo = new H1("BIBLIOTECA CENTRAL DE CENTRO AMÉRICA");
        titulo.setClassName("center");
        login.addLoginListener(e->{
            UI.getCurrent().navigate("autores");
        });
        add(titulo, login);
    }

}
