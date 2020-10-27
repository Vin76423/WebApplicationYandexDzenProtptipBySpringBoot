package com.example.webapplicationboot.controller;

import com.example.webapplicationboot.entity.User;
import com.example.webapplicationboot.service.user.exception.DuplicateUserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandlerHomeController {
    @ExceptionHandler(value = DuplicateUserException.class)
    public String DuplicateUserHandler(Model model, RuntimeException e) {
        model.addAttribute("massage", e.getMessage());
        model.addAttribute("user", new User());
        return "reg";
    }
}
