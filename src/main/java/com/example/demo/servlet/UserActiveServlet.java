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
 * DateTime: 2018/7/6 11:07
 **/
public class UserActiveServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserActiveServlet.class);

    /**
     * 已注册用户激活
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String code = req.getParameter("code");
            UserService userService = new UserService();
            userService.active(code);
            req.setAttribute("tips","激活成功！");
        }catch (Exception e){
            logger.error("激活失败{}",e);
            req.setAttribute("tips","激活失败！" + e.getMessage());
        }
        req.getRequestDispatcher("../WEB-INF/jsp/tips.jsp").forward(req,resp);
    }
}
