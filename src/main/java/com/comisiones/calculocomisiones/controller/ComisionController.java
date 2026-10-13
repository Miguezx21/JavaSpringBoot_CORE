package com.comisiones.calculocomisiones.controller;

import com.comisiones.calculocomisiones.dto.ComisionDTO;
import com.comisiones.calculocomisiones.service.ComisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ComisionController {

    private final ComisionService comisionService;

    @GetMapping("/")
    public String mostrarFormulario() {
        return "index";
    }

    @PostMapping("/calcular")
    public String calcularComisiones(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model) {

        List<ComisionDTO> resultados = comisionService.calcularComisiones(fechaInicio, fechaFin);

        model.addAttribute("resultados", resultados);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "index";
    }
}
