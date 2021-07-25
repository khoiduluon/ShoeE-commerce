package com.ps13251_tranhieutrung_GD2.ontap.Controllers;

import java.util.List;

import com.ps13251_tranhieutrung_GD2.ontap.DAO.CategoryDAOontap;
import com.ps13251_tranhieutrung_GD2.ontap.Models.CategoriesOnTap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ontap/category")
public class OntapCategory {

    @Autowired
    CategoryDAOontap categoriesDAO;

    @GetMapping("/index")
    public String index(Model model){
        List<CategoriesOnTap> listCategories = categoriesDAO.findAll();
        model.addAttribute("listCategory", listCategories);
        return "category-management1";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("category") CategoriesOnTap category){
        return "ontap-add-category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("categories") CategoriesOnTap categories){
        categoriesDAO.save(categories);
        return "redirect:/ontap/category/index";
    }

    
    @GetMapping("/edit")
    public String showFormforUpdate(@RequestParam("categoryId") int id,Model model){
        CategoriesOnTap categories = categoriesDAO.findById(id).get();
        model.addAttribute("category", categories);
        return "ontap-add-category";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId") int id, Model model){
        categoriesDAO.deleteById(id);
        return"redirect:/ontap/category/index";
    }
}


