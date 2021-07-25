package com.ps13251_tranhieutrung_GD2.Controllers;

import java.util.List;

import com.ps13251_tranhieutrung_GD2.DAO.CategoriesDAO;
import com.ps13251_tranhieutrung_GD2.Models.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class Categories_managementController {
    
    @Autowired
    CategoriesDAO categoriesDAO;

    @GetMapping("/index")
    public String index(Model model) {
        Categories categories = new Categories();
        List<Categories> listCategories = categoriesDAO.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("listCategory", listCategories);
        return "category-management";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("category") Categories category){
        return "add";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("categories") Categories categories){
        categoriesDAO.save(categories);
        return "redirect:/category/index";
    }

    @GetMapping("/edit")
    public String showFormforUpdate(@RequestParam("categoryId") int id,Model model){
        Categories categories = categoriesDAO.findById(id).get();
        model.addAttribute("category", categories);
        return "add";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId") int id, Model model){
        categoriesDAO.deleteById(id);
        return "redirect:/category/index";
    }
}