package com.ues.fia.bad115.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
//import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.ues.fia.bad115.clase.Usuario;
import com.ues.fia.bad115.service.UsuarioService;
//import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "login")
@CssImport(value = "styles.css")
@PageTitle(value = "Iniciar Sesión | Biblioteca Central de Centro América")
public class LoginView extends VerticalLayout {
    @Autowired
    private UsuarioService usuarioService;

    public LoginView() {
        setClassName("login");
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Bienvenido/a");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Ingresar");
        i18nForm.setForgotPassword("¿Contraseña Olvidada?");
        i18n.setForm(i18nForm);
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Usuario o contraseña inválidos");
        i18nErrorMessage
                .setMessage("Revisa tu usuario y contraseña. Si no recuerdas tu contraseña puedes reestablecerla");
        i18n.setErrorMessage(i18nErrorMessage);
        LoginForm login = new LoginForm();
        login.setClassName("login-box");
        login.setI18n(i18n);

        login.getElement().getThemeList().add("dark");
        H1 titulo = new H1("BIBLIOTECA CENTRAL DE CENTRO AMÉRICA");
        titulo.setClassName("titulo");

        login.addLoginListener(e -> {
            Usuario usuario = new Usuario();
            usuario.setEmail(e.getUsername());
            usuario.setPassword(e.getPassword());
            if (usuarioService.validarUsuario(usuario)) {
                UI.getCurrent().navigate("principal");
            } else {
                login.setError(true);
            }
        });
        add(titulo, login);
    }
}
