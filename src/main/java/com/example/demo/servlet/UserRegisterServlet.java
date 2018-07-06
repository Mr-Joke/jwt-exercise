package com.example.demo.servlet;

import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 9:27
 **/
public class UserRegisterServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserRegisterServlet.class);

    /**
     * 用户注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            userService.register(username,password,email);
            req.setAttribute("tips","注册成功！");
        }catch (Exception e){
           logger.error("注册失败{}",e);
            req.setAttribute("tips","注册失败！" + e.getMessage());
        }
        req.getRequestDispatcher("../WEB-INF/jsp/tips.jsp").forward(req,resp);
    }
}
