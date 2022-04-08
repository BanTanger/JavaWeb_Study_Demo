package com.bantanger.session;

import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author bantanger 半糖
 * @version 1.0
 * @Date 2022/4/7 20:49
 */
public class CookiesDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        PrintWriter out = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("name")){
                URLDecoder.decode(cookie.getValue(),"UTF-8");
                out.write(cookie.getValue());
            }
        }
        // Cookie cookie = new Cookie("name", "半塘鹅"); Tomcat 9 以后不报错
        Cookie cookie = new Cookie("name", URLEncoder.encode("半糖", "GBK"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
