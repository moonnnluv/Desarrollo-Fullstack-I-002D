package com.edutech.edutech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsuarioController {



    // RUTA A HTML USUARIO
    @GetMapping("/usuario")
    public String usuario(){
        return "usuario";
    }
    
    


}
