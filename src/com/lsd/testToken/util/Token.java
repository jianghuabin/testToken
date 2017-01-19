package com.lsd.testToken.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fangf on 2016/5/22.
 */
public class Token {

    private int id;
    private String name;
    private String role;
    private int err = 0;
    public static final int ExpiredJwtError = 1;
    public static final int SignatureError = 2;

    public Token(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(Auth.key).parseClaimsJws(token).getBody();
            System.out.println(claims.toString());
            this.id = Integer.parseInt(claims.get("id").toString());
            this.name = claims.get("name").toString();
            this.role = claims.get("role").toString();
            System.out.println("================验证成功=================");
        } catch (ExpiredJwtException e){
            this.err = ExpiredJwtError;
            System.out.println("===========超时============");
        } catch (SignatureException e) {
            this.err = SignatureError;
            System.out.println("================秘钥错误============");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getErr() {
        return err;
    }

}
