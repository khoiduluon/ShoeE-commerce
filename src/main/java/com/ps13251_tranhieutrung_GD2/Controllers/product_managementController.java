package com.ps13251_tranhieutrung_GD2.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;



import com.ps13251_tranhieutrung_GD2.DAO.CategoriesDAO;
import com.ps13251_tranhieutrung_GD2.DAO.ProductsDAO;
import com.ps13251_tranhieutrung_GD2.Models.Categories;
import com.ps13251_tranhieutrung_GD2.Models.Products;
import com.ps13251_tranhieutrung_GD2.Models.Report;
import com.ps13251_tranhieutrung_GD2.Services.ProductsService;
import com.ps13251_tranhieutrung_GD2.Services.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/product")
public class product_managementController {
    

    @Autowired
    ProductsDAO productsDAO;

    @Autowired
    CategoriesDAO categoriesDAO;

    @Autowired
    ProductsService productsService;

    @GetMapping("/index")
    public String index(Model model){
        // Pageable pageAble = PageRequest.of(page.orElse(0), 2);
        // Page<Products> list = productsDAO.findAll(pageAble);
        // model.addAttribute("currentPage", page);
        // model.addAttribute("totalPages", list.getTotalPages());
        // model.addAttribute("listProducts", list);
        return page(model, 1,"name","asc");
    }

    @GetMapping("/index/{page}")
    public String page(Model model, @PathVariable(value = "page") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){
        int pageSize = 5;
        Page<Products> page = productsService.sortPaginated(pageNo, pageSize, sortField, sortDir);
        List<Products> list = page.getContent();
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("number",page.getNumber());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listProducts", list);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortField", sortDir);

        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "product-management";
    }
    
    @RequestMapping("/sort")
    public String sort(@RequestParam("field") Optional<String> field,Model model){
        Sort sort = Sort.by(Direction.ASC, field.orElse("price"));
        List<Products> list = productsDAO.findAll(sort);
        model.addAttribute("listProducts", list);
        return "product-management";
    }

    @GetMapping("/add")
    public String addForm(Model model, @ModelAttribute("product") Products product){
        List<Categories> list = categoriesDAO.findAll();
        model.addAttribute("list",list);
        return "add1";
    }
    // @GetMapping("/page")
    // public String page(Model model, @RequestParam("p") Optional<Integer> page){
    //     Pageable pageAble = PageRequest.of(page.orElse(0), 1);
    //     Page<Products> listPage = productsDAO.findAll(pageAble);
    //     model.addAttribute("listPage", listPage);
    //     return "page-management";
    // }
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("product") Products product, @RequestParam("fileImage") MultipartFile file)  throws IOException {
        product.setImage(file.getOriginalFilename().toString());
        productsDAO.save(product);
        return "redirect:/product/index";
    }

    @GetMapping("/edit")
    public String showFormforUpdate(@RequestParam("productId") int id,Model model){
        Products products = productsDAO.findById(id).get();
        List<Categories> list = categoriesDAO.findAll();
        model.addAttribute("list",list);  
        model.addAttribute("product", products); // thêm search, add photo account,
        return "add1";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int id, Model model){
        productsDAO.deleteById(id);
        return "redirect:/product/index";
    }

    @GetMapping("/report")
    public String report(Model model){
        List<Report> list = productsDAO.getIventoryByCategory();
        model.addAttribute("list",list);
        return "report-management";
    }

}
