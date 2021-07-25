package com.ps13251_tranhieutrung_GD2.Services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    public Cookie create(String name, String value, int days){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(days * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }

    public Cookie get(String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equalsIgnoreCase(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

    public void remove(String name) {
		create(name, "", 0);
	}
}
