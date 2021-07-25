package com.ps13251_tranhieutrung_GD2.ontap.Controllers;

import java.io.IOException;
import java.util.List;

import com.ps13251_tranhieutrung_GD2.ontap.DAO.CategoryDAOontap;
import com.ps13251_tranhieutrung_GD2.ontap.DAO.ProductDAOontap;
import com.ps13251_tranhieutrung_GD2.ontap.Models.CategoriesOnTap;
import com.ps13251_tranhieutrung_GD2.ontap.Models.ProductsOnTap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ontap/product")
public class OntapProduct {
    
    @Autowired
    ProductDAOontap productDAO;

    @Autowired
    CategoryDAOontap categoriesDAO;
    

    @GetMapping("/index")
    public String index(Model model){
        List<ProductsOnTap> list = productDAO.findAll();
        model.addAttribute("listProducts", list);
        return "product-management1";
    }

    @GetMapping("/add")
    public String addForm(Model model, @ModelAttribute("product") ProductsOnTap product){
        List<CategoriesOnTap> list = categoriesDAO.findAll();
        model.addAttribute("list",list);
        return "ontap-add-product";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("product") ProductsOnTap product, @RequestParam("fileImage") MultipartFile file)  throws IOException {
        product.setImage(file.getOriginalFilename().toString());
        productDAO.save(product);
        return "redirect:/ontap/product/index";
    }

    @GetMapping("/edit")
    public String showFormforUpdate(@RequestParam("productId") int id,Model model){
        ProductsOnTap products = productDAO.findById(id).get();
        List<CategoriesOnTap> list = categoriesDAO.findAll();
        model.addAttribute("list",list);  
        model.addAttribute("product", products);
        return "ontap-add-product";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int id, Model model){
        productDAO.deleteById(id);
        return "redirect:/ontap/product/index";
    }
}
