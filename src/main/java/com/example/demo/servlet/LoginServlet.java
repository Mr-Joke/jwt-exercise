package com.example.demo.servlet;

import com.example.demo.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/2 13:59
 **/
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isCorrect = checkUser(username,password);
        if (isCorrect){
            Date currentDate = new Date();
            Calendar instance = Calendar.getInstance();
            instance.setTime(currentDate);
            instance.add(Calendar.MINUTE,120);
            Claims claims = new DefaultClaims().setSubject(Constants.JWT_SUBJECT).setIssuedAt(currentDate).setExpiration(instance.getTime());
            claims.put("username",username);
            claims.put("password",password);
            String jwtSecret = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY).compact();
            System.out.println(jwtSecret);
        }else {
            System.out.println("Error User");
        }
    }

    private boolean checkUser(String username, String password) {
        if (username == null || "".equals(username)) return false;
        if (password == null || "".equals(password)) return false;
        return username.equals(Constants.TEMP_USERNAME) && username.equals(Constants.TEMP_PASSWORD);
    }
}
