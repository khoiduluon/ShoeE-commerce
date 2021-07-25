package com.ps13251_tranhieutrung_GD2.Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ps13251_tranhieutrung_GD2.Models.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String uri = request.getRequestURI();
      Accounts user = session.getAttribute("username");
      
      String error = "";
      if(user == null){
        error = "login!!";
      } else if(!user.isAdmin() && uri.startsWith("/admin")){
        error = "Access denied";
      }
      if(error.length() > 0){
        session.setAttribute("security-uri",uri);
        response.sendRedirect("/login?error=" + error);
      }
        return true;
    }
}
