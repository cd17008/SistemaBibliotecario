package com.ues.fia.bad115.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ues.fia.bad115.service.EmailService;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text,
            @RequestParam boolean isHtmlContent) {
        try {
            emailService.sendEmail(to, subject, text, isHtmlContent);
            return "Email sent successfully";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
