package com.itzo.header;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zon on 2018/6/19.
 */
@WebServlet("/referer")
public class RefererServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
//对新闻的来源进行判断
        String header = req.getHeader("referer");
        if (header != null && header.startsWith("http://localhost")) {
            //是从我自己的网站跳转过来的 可以看新闻
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("中国确实已经拿到100块金牌....");
        } else {
            response.getWriter().write("你是盗链者，可耻！！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
