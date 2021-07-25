package com.ps13251_tranhieutrung_GD2.Controllers;

import com.ps13251_tranhieutrung_GD2.Models.Item;
import com.ps13251_tranhieutrung_GD2.Services.SessionService;
import com.ps13251_tranhieutrung_GD2.Services.ShoppingCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/card")
public class ShoppingCardController {
    
    @Autowired
    SessionService sessionService;


    @Autowired
    ShoppingCardService shoppingCardService;

    @GetMapping("/view")
    public String view(Model model, Item item) {
        model.addAttribute("cards", shoppingCardService.getItems());
        model.addAttribute("getCount", shoppingCardService.getCount());
        model.addAttribute("getAmount", shoppingCardService.getAmount());
        return "card";
    }

    @GetMapping("/add")
    public String addItem(@RequestParam("productId") int id){
        String userNull = sessionService.getAttribute("username");
        if(userNull!=null){
            shoppingCardService.add(id);
            return "redirect:/card/view";
        } 
        return "redirect:/login";
    }
    @GetMapping("/clear")
    public String clearItem(){
        shoppingCardService.clear();
        return "redirect:/card/view";
    }
    @RequestMapping("/update")
    public String updateItem(@RequestParam("itemId") int id, @RequestParam("qty") int qty){
        shoppingCardService.update(id, qty);
       System.out.println(id +" "+qty);
        return "redirect:/card/view";
    }
    
    @GetMapping("/remove")
    public String removeItem(@RequestParam("itemId") int id){
        shoppingCardService.remove(id);
        return "redirect:/card/view";
    }
}
