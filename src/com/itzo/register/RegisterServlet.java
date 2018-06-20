package com.itzo.register;

import com.itzo.utils.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Zon on 2018/6/20.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request的编码---只适合post方式
        request.setCharacterEncoding("UTF-8");

        //get方式乱码解决
        //String username = request.getParameter("username");//乱码
        //先用iso8859-1编码 在使用utf-8解码
        //username = new String(username.getBytes("iso8859-1"),"UTF-8");


        //1、获取数据
        //String username = request.getParameter("username");
        //System.out.println(username);
        //String password = request.getParameter("password");
        //.....

        //2、将散装的封装到javaBean
        //User user = new User();
        //user.setUsername(username);
        //user.setPassword(password);

        //使用BeanUtils进行映射封装
        //BeanUtils的工作原理：将map中的数据 根据key与实体的属性的对应关系封装
        //只要key的名字与实体的属性  的名字一样就能自动封装到实体中
        Map<String, String[]> properties = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //现在这个位置 user已经封装好了
        //手动封装uid----uuid----随机不重复的字符串32--java代码生成是36位
        user.setUid(UUID.randomUUID().toString());
        System.out.println("uuid  " + UUID.randomUUID().toString());
//        3.将参数传递给一个业务操作方法
        try {
            regist(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //注册的方法
    public void regist(User user) throws SQLException {
//操作数据库
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), null,
                user.getBirthday(), user.getSex(), null, null);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
