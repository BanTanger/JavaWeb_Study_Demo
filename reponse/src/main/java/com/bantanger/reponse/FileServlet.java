package com.bantanger.reponse;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author bantanger 半糖
 * @version 1.0
 * @Date 2022/4/5 14:36
 */
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1. 获取下载文件的**路径**
        // String realPath = this.getServletContext().getRealPath("/shipe.png"); // 通过这个编译成功后，在out里拿到绝对路径。
        String realPath = "E:\\Java 学习项目集合\\JavaWeb_Study_Demo\\out\\production\\reponse\\shipe.png";
        System.out.println("下载文件的路径: " + realPath);
//        2. 确定下载**文件名**
        String fileName = realPath.substring(realPath.lastIndexOf("/") + 1); // 通过字符串截断的方式获得文件名
//        3. 设置能让浏览器支持下载我们想要的文件
         resp.setHeader("Content-Disposition","attachment;filename=" + fileName); // 注意这里是分号；而不是冒号：
        // 如果是中文命名的文件，因为用的是字节流所以没法识别中文，可以使用 URLEncoder.encode(fileName,"UTF-8") 来处理
        // resp.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName,"UTF-8")); // 注意这里是分号；而不是冒号：

//        4. 获取下载文件的**输入流**
        FileInputStream in = new FileInputStream(realPath);
//        5. **创建缓冲区**
        int len = 0;
        byte[] buffer = new byte[1024];
//        6. 获取**OutputStream对象**
        ServletOutputStream out = resp.getOutputStream();
//        7. 将**FileOutputStream流写入**到buffer缓冲区内, 使用**OutputStream**将缓冲区的数据输出到客户端里
        while((len=in.read(buffer))>0){
            out.write(buffer,0,len);
        }
//        8. 关流
        out.close();
        in.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
