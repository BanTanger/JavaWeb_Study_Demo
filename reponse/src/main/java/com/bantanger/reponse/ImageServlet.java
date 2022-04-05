package com.bantanger.reponse;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author bantanger 半糖
 * @version 1.0
 * @Date 2022/4/5 15:12
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 如何让浏览器 3 秒钟自动刷新一次
        resp.setHeader("refresh","10");

        // 在内存中创建一个图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        // 得到图片
        Graphics2D g = (Graphics2D) image.getGraphics(); // 笔
        g.setColor(Color.white);
        g.fillRect(0,0,80,20);

        // 给图片写数据（生成随机数）
        g.setColor(Color.BLACK); // 大写代表书写字体颜色，小写代表书写背景颜色
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeNum(),0,20);

        // 告诉浏览器，这个请求使用图片的方式打开
        resp.setContentType("image/jpeg");
        // 网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragme","no-cache");

        // 把图片写给浏览器
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    private String makeNum(){
        Random random = new Random(); // 创建随机类
        String s = random.nextInt(9999999) + "";// 生成1000 到 9999 的四位随机数
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - s.length(); i++) {
            sb.append("0"); // 保证生成的是四位数
        }
        s = sb.toString() + s;
        return s;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }
}
