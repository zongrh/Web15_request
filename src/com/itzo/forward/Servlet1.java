package com.itzo.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class Servlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        //想request域中存储数据
        request.setAttribute("name", "tom");
        request.setAttribute("name2", "呃呃呃呃呃呃呃呃呃鹅鹅鹅鹅鹅鹅饿");


        //servlet1 将请求转发给servlet2
        RequestDispatcher dispatcher = request.getRequestDispatcher("/servlet2");
        //执行转发的方法
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}