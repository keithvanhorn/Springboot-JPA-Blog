package com.cos.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
    @GetMapping("/temp/home")
    public String tempHome(){
        return "/home.html";
    }
}
