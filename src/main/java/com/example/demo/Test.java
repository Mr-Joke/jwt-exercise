package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.*;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/2 10:49
 **/
public class Test {
    private static final String secretKey = "mrjoke.20180702";
    public static void main(String[] args) throws InterruptedException {
        Date currentDate = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(currentDate);
        instance.add(Calendar.SECOND,5);
        Claims claims = new DefaultClaims().setSubject("MrJoke").setIssuedAt(currentDate).setExpiration(instance.getTime());
        claims.put("username","test");
        claims.put("password","testpwd");
        String jwtSecret = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secretKey).compact();
        System.out.println(jwtSecret);
        String subName = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtSecret).getBody().getSubject();
        System.out.println(subName);
    }
}
