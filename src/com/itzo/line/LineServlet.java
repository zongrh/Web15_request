package com.itzo.line;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zon on 2018/6/20.
 */
@WebServlet("/lineServlet")
public class LineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获得请求方式
        String method = req.getMethod();
        System.out.println("method=======" + method);
//        2.获得请求资源相关的内容
        String requestURI = req.getRequestURI();
        StringBuffer requestURL = req.getRequestURL();
        System.out.println("uri: " + requestURI);
        System.out.println("url: " + requestURL);
//        获得web应用的名称
        String contextPath = req.getContextPath();
        System.out.println("web应用：" + contextPath);
//         地址后的参数的字符串
        String quertStrng = req.getQueryString();
        System.out.println("queryString:" + quertStrng);
//        3.获得客户主机的信息----访问者的IP地址
        String remoteAddr = req.getRemoteAddr();
        System.out.println("IP:" + remoteAddr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
