package com.example.demo.servlet;

import com.example.demo.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/2 14:12
 **/
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Claims claims = Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(req.getParameter("secret")).getBody();
        String username = (String) claims.get("username");
        String passwrod = (String) claims.get("password");
        if (Constants.TEMP_USERNAME.equals(username) && Constants.TEMP_PASSWORD.equals(passwrod)){
            System.out.println("通过校验");
        }else {
            System.out.println("校验失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
