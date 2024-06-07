package com.ues.fia.bad115;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDate;
import java.util.List;
import com.ues.fia.bad115.clase.Prestamo;
import com.ues.fia.bad115.service.EmailService;

import com.ues.fia.bad115.repository.PrestamoRepository;

@Component
public class ReminderScheduler {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private EmailService emailService;

    public java.sql.Date convertLocalDateToDate(LocalDate fecha) {
        return java.sql.Date.valueOf(fecha);
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendReminder() {
        try {
            emailService.sendEmail("cd17008@ues.edu.sv", "PruebaRecordatorio", "Texo de prueba", false);
            System.out.println("Correo enviado a: ");
        } catch (Exception e) {
            System.out.println("Error al enviar correo");
        }
        // Los profesores tienen 90 días para devolver un libro, 89 días después de la
        // fecha de préstamo se les enviará un recordatorio
        LocalDate fechaProfesores = LocalDate.now().minusDays(89);
        // Los miembros tienen 5 días para devolver un libro, 4 días después de la fecha
        // de préstamo se les enviará un recordatorio
        LocalDate fechaMiembros = LocalDate.now().minusDays(4);
        List<Prestamo> prestamos = prestamoRepository.findAll();
        List<Prestamo> prestamosProfesores = prestamos.stream()
                .filter(prestamo -> prestamo.getUsuario().getTipousuario().equals("Profesor")).toList();
        List<Prestamo> prestamosMiembros = prestamos.stream()
                .filter(prestamo -> prestamo.getUsuario().getTipousuario().equals("Miembro")).toList();

        List<Prestamo> prestamosProfesoresManana = prestamosProfesores.stream()
                .filter(prestamo -> prestamo.getFecha().equals(convertLocalDateToDate(fechaProfesores))).toList();
        List<Prestamo> prestamosMiembrosManana = prestamosMiembros.stream()
                .filter(prestamo -> prestamo.getFecha().equals(convertLocalDateToDate(fechaMiembros))).toList();

        for (Prestamo prestamo : prestamosProfesoresManana) {
            String email = prestamo.getUsuario().getEmail();
            String asunto = "Recordatorio de devolución. Biblioteca Central de Centro América";
            String mensaje = "Estimado profesor/a: " + prestamo.getUsuario().getNombre() + " "
                    + prestamo.getUsuario().getApellido() +
                    ",\n\nLe recordamos que tiene un prestamo pendiente de devolución en la Biblioteca Central de Centro América para el día de mañana."
                    +
                    " Por favor realice la devolución para la fecha: " + LocalDate.now().plusDays(1)
                    + ".\n\nAtentamente,\nBiblioteca Central de Centro América";

            // Enviar correo
            try {
                emailService.sendEmail(email, asunto, mensaje, false);
                System.out.println("Correo enviado a: " + email);
            } catch (Exception e) {
                System.out.println("Error al enviar correo");
            }
        }

        for (Prestamo prestamo : prestamosMiembrosManana) {
            String email = prestamo.getUsuario().getEmail();
            String asunto = "Recordatorio de devolución. Biblioteca Central de Centro América";
            String mensaje = "Estimado miembro: " + prestamo.getUsuario().getNombre() + " "
                    + prestamo.getUsuario().getApellido() +
                    ",\n\nLe recordamos que tiene un prestamo pendiente de devolución en la Biblioteca Central de Centro América para el día de mañana."
                    +
                    " Por favor realice la devolución para la fecha: " + LocalDate.now().plusDays(1)
                    + ".\n\nAtentamente,\nBiblioteca Central de Centro América";

            // Enviar correo
            try {
                emailService.sendEmail(email, asunto, mensaje, false);
                System.out.println("Correo enviado a: " + email);
            } catch (Exception e) {
                System.out.println("Error al enviar correo");
            }
        }

    }
}
