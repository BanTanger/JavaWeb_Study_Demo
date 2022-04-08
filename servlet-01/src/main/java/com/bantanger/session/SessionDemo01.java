package com.bantanger.session;

import com.bantanger.pojo.Person;
import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author bantanger 半糖
 * @version 1.0
 * @Date 2022/4/7 21:06
 */
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

        // 得到session
        HttpSession session = req.getSession();

        // 在session中存放东西
        session.setAttribute("name",new Person("半塘鹅",134));

        // 获得session的ID
        String id = session.getId();

        // 判断Session是否新创建
        if(session.isNew()){
            resp.getWriter().write("session创建成功，ID" + id);
        } else {
            resp.getWriter().write("session已经在服务器中存在，ID"+id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
