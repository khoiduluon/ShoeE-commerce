package com.ps13251_tranhieutrung_GD2.ontap.Controllers;

import java.util.List;

import com.ps13251_tranhieutrung_GD2.ontap.DAO.AccountDAOontap;
import com.ps13251_tranhieutrung_GD2.ontap.Models.AccountsOnTap;

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
@RequestMapping("/ontap/account")
public class OntapAccount {

    @Autowired
    AccountDAOontap accountDAO;

    @GetMapping("/index")
    public String index(Model model) {
        List<AccountsOnTap> list = accountDAO.findAll();
        model.addAttribute("listAccounts", list);
        return "account-management1";
    }

    @GetMapping("/add")
    public String addForm(Model model, @ModelAttribute("account") AccountsOnTap account){
        return "edit-account1";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("account") AccountsOnTap account, @RequestParam("fileImage") MultipartFile file) {
        account.setPhoto(file.getOriginalFilename().toString());
        accountDAO.save(account);
        return "redirect:/ontap/account/index";
    }


    @GetMapping("/edit")
    public String showFormforUpdate(@RequestParam("username") String id,Model model){
        AccountsOnTap account = accountDAO.findById(id).get();
        model.addAttribute("account", account); 
        return "edit-account";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("username") String id, Model model){
        accountDAO.deleteById(id);
        return "redirect:/ontap/account/index";
    }
}
