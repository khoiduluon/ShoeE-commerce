package com.ps13251_tranhieutrung_GD2.Controllers;

import com.ps13251_tranhieutrung_GD2.DAO.AccountDAO;
import com.ps13251_tranhieutrung_GD2.Models.Accounts;
import com.ps13251_tranhieutrung_GD2.Services.CookieService;
import com.ps13251_tranhieutrung_GD2.Services.ParamService;
import com.ps13251_tranhieutrung_GD2.Services.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @Autowired
    AccountDAO accountDAO;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(Model model) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        // System.out.println(model.getAttribute("remember"));
        boolean remember = paramService.getBoolean("remember", false);
        Accounts account = accountDAO.findById(username).get();
        if (account != null) {
            if (remember) {
                cookieService.create("user", username, 10);
                cookieService.create("pass", password, 10);
                System.out.println("Lưu cookie");
            } else {
                cookieService.remove("user");
                cookieService.remove("pass");
            }
            if (account.isAdmin()) {
                sessionService.setAttribute("username", username);
                return "redirect:/product/index";
            } else {
                sessionService.setAttribute("username", username);
                return "redirect:/index";
            }
        } else {
            return "error";
        }
    }

    @RequestMapping("/logout")
    public String doLogout() {
       sessionService.removeAttribute("username");
        return "login";
    }

    @RequestMapping("/register")
    public String doRegister(Model model, @ModelAttribute("account") Accounts account) {
        // Accounts account = new Accounts();
        // model.addAttribute("account", account);
        return "register";
    }


    @PostMapping("/register/save")
    public String save(@ModelAttribute("account") Accounts account, @RequestParam("fileImage") MultipartFile file) {
        // account.setPhoto(file.getOriginalFilename());
        account.setPhoto(file.getOriginalFilename().toString());
        accountDAO.save(account);
        // paramService.save(file, "");
        return "index";
    }

    @RequestMapping("/account/edit")
    public String accountEdit(Model model) {
        String sessionAccount = sessionService.getAttribute("username");
        if(sessionAccount != null ){
            Accounts account = accountDAO.findById(sessionAccount).get();
            model.addAttribute("account", account);
            System.out.println(sessionAccount);
            return "account";
        }
        return "login";
    }
}


