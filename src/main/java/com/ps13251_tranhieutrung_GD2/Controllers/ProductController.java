package com.ps13251_tranhieutrung_GD2.Controllers;

import java.util.List;

import com.ps13251_tranhieutrung_GD2.DAO.ProductsDAO;
import com.ps13251_tranhieutrung_GD2.Models.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductsDAO productsDAO;

    @GetMapping("/detail")
    public String getDetailPage(@RequestParam("productId") int id, Model model){
        Products product = productsDAO.findById(id).get();
        model.addAttribute("product", product);
        return "shop-single";
    }

}
