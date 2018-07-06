package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Calendar;
import java.util.Date;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 10:23
 **/
public class JwtUtils {
    public static final String secretKey = "3dxt.mrjoke@bb^123#$";

    private JwtUtils(){}

    /**
     * 生成jwt，有效期两小时
     * @param sub
     * @return
     */
    public static String getString(String sub){
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.HOUR,2);
        Claims claims = new DefaultClaims().setSubject(sub).setIssuedAt(current).setExpiration(calendar.getTime());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}
