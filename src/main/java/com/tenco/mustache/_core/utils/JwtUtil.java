package com.tenco.mustache._core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tenco.mustache.user.User;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "jang";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private static final String SUBJECT = "tenco_blog";

    // 토큰생성
    public static String create(User user){

        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String jwt = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(expiresAt)
                .withClaim("id",user.getId())
                .withClaim("username",user.getUsername())
                .withClaim("email",user.getEmail())
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC512(SECRET_KEY));

        return jwt;
    }
}
