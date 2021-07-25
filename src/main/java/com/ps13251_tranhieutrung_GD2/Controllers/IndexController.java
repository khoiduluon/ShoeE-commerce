package com.ps13251_tranhieutrung_GD2.Controllers;

import java.util.List;
import java.util.Optional;

import com.ps13251_tranhieutrung_GD2.DAO.ProductsDAO;
import com.ps13251_tranhieutrung_GD2.Models.Products;
import com.ps13251_tranhieutrung_GD2.Services.ProductsService;
import com.ps13251_tranhieutrung_GD2.Services.SessionService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    
    @Autowired
    ProductsDAO productsDAO;

    @Autowired
    SessionService sessionService;

    @Autowired
    ProductsService productsService;

    @RequestMapping("/index")
    public String index(Model model) {
       String name =  sessionService.getAttribute("username");
       model.addAttribute("UserName", name);
        return "index";
    }
    
    // @GetMapping("/shop")
    // public String shop(Model model){
    //     List<Products> list = productsDAO.findAll();
    //     model.addAttribute("listProducts", list);
    //     return "shop";
    // }

    @RequestMapping("/sort")
    public String sort(@RequestParam("field") Optional<String> field,Model model){
        Sort sort = Sort.by(Direction.ASC, field.orElse("price"));
        List<Products> list = productsDAO.findAll(sort);
        model.addAttribute("listProducts", list);
        return "shop";
    }

    @GetMapping("/shop")
    public String pageIndex(Model model){
        return page(model, 1,"name","asc");
    }

    // @GetMapping("/shop/{page}")
    // public String page(Model model, @PathVariable(value = "page") int pageNo){
    //     int pageSize = 3;
    //     Page<Products> page = productsService.findPaginated(pageNo, pageSize);
    //     List<Products> list = page.getContent();
    //     model.addAttribute("totalPages", page.getTotalPages());
    //     model.addAttribute("number",page.getNumber());
    //     model.addAttribute("currentPage", pageNo);
    //     model.addAttribute("listProducts", list);
    //     return "shop";
    // }

    @GetMapping("/shop/{page}")
    public String page(Model model, @PathVariable(value = "page") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){
        int pageSize = 6;
        Page<Products> page = productsService.sortPaginated(pageNo, pageSize, sortField, sortDir);
        List<Products> list = page.getContent();
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("number",page.getNumber());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listProducts", list);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortField", sortDir);

        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "shop";
    }

}
