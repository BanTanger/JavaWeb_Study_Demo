package com.bantanger.session;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author bantanger 半糖
 * @version 1.0
 * @Date 2022/4/7 19:07
 */
public class CookiesDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf8");

        PrintWriter out = resp.getWriter();

        // Cookies通过客户端请求获得
        Cookie[] cookies = req.getCookies(); // Cookies可能存在多个，所以返回数组

        // 判断Cookies是否存在
        if(cookies != null) {
            // 存在逻辑
            out.write("你上一次访问的时间是:");
            for (Cookie cookie : cookies) {
                if("lastLoginTime".equals(cookie.getName())){ // 将cookies里面的值取出比较
                    long lastLoginTime = Long.parseLong(cookie.getValue()); // 将cookies里面的值取出转化成长整型（因为是时间）
                    Date date = new Date(lastLoginTime); // 将时间戳转化成日期类
                    // out.write(String.valueOf(date)); // 日期类转化成String的方法：String.valueOf
                    out.write(date.toLocaleString()); // 日期类转化成String的方法：String.valueOf
                }
            }
        } else {
            out.write("这是您第一次访问本站"); // 使用PrintWrite对象将文字打印在浏览器
        }
        // 服务端要给客户端响应一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }
}
