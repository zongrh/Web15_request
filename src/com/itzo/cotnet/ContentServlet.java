package com.itzo.cotnet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Zon on 2018/6/19.
 */
@WebServlet("/contentServlet")
public class ContentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获得单个表单值
        String username = req.getParameter("username");
        System.out.println(username);
        String password = req.getParameter("password");
        System.out.println(password);
//        2、获得多个表单的值
        String[] hobbys = req.getParameterValues("hobby");
        for (String hobby : hobbys) {
            System.out.println(hobby);
        }
//        3、获得所有的请求参数的名额
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            System.out.println(parameterNames.nextElement());
        }
        System.out.println("--------------");
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey());
            for (String str : entry.getValue()) {
                System.out.println(str);
            }
            System.out.println("--------------");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
