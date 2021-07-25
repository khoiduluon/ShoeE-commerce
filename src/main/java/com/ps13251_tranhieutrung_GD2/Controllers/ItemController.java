package com.ps13251_tranhieutrung_GD2.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    
    @RequestMapping("/list/item")
    public String list(Model model) {
        // model.addAttribute("items", Database.items.values());
        return "list";
    }
}
