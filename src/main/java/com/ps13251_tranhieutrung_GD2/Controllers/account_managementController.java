package com.ps13251_tranhieutrung_GD2.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.ps13251_tranhieutrung_GD2.DAO.AccountDAO;
import com.ps13251_tranhieutrung_GD2.DAO.CategoriesDAO;
import com.ps13251_tranhieutrung_GD2.DAO.ProductsDAO;
import com.ps13251_tranhieutrung_GD2.Models.Accounts;
import com.ps13251_tranhieutrung_GD2.Models.Categories;
import com.ps13251_tranhieutrung_GD2.Models.Products;
import com.ps13251_tranhieutrung_GD2.Models.Report;
import com.ps13251_tranhieutrung_GD2.Services.AccountsService;
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
@RequestMapping("/accountmn")
public class account_managementController {
    
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    AccountsService accountService;

    @GetMapping("/index")
    public String index(Model model){
        // Pageable pageAble = PageRequest.of(page.orElse(0), 2);
        // Page<Products> list = productsDAO.findAll(pageAble);
        // model.addAttribute("currentPage", page);
        // model.addAttribute("totalPages", list.getTotalPages());
        // List<Accounts> list = accountDAO.findAll();
        // model.addAttribute("listAccounts", list);
        return page(model, 1);
    }

    @GetMapping("/index/{page}")
    public String page(Model model, @PathVariable(value = "page") int pageNo){
        int pageSize = 5;
        Page<Accounts> page = accountService.findPaginated(pageNo, pageSize);
        List<Accounts> list = page.getContent();
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("number",page.getNumber());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listAccounts", list);

        return "account-management";
    }
    
    // @RequestMapping("/sort")
    // public String sort(@RequestParam("field") Optional<String> field,Model model){
    //     Sort sort = Sort.by(Direction.ASC, field.orElse("price"));
    //     List<Products> list = accountDAO.findAll(sort);
    //     model.addAttribute("listProducts", list);
    //     return "product-management";
    // }

    @GetMapping("/add")
    public String addForm(Model model, @ModelAttribute("account") Accounts account){
     
        return "edit-account";
    }
    // @GetMapping("/page")
    // public String page(Model model, @RequestParam("p") Optional<Integer> page){
    //     Pageable pageAble = PageRequest.of(page.orElse(0), 1);
    //     Page<Products> listPage = productsDAO.findAll(pageAble);
    //     model.addAttribute("listPage", listPage);
    //     return "page-management";
    // }
    
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("account") Accounts account, @RequestParam("fileImage") MultipartFile file) {
        account.setPhoto(file.getOriginalFilename().toString());
        accountDAO.save(account);
        return "redirect:/accountmn/index";
    }

    @GetMapping("/edit")
    public String showFormforUpdate(@RequestParam("username") String id,Model model){
        Accounts account = accountDAO.findById(id).get();
        model.addAttribute("account", account); 
        return "edit-account";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("username") String id, Model model){
        accountDAO.deleteById(id);
        return "redirect:/accountmn/index";
    }

}
