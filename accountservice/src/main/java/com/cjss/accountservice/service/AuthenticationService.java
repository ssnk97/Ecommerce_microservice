package com.cjss.accountservice.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AuthenticationService {

    long EXPIRATIONTIME = 1000 * 60*4;
    String secret = "CJSSTECHNOLOGIES";


    public ResponseEntity<String> GenerateToken(String email){
        String encryptedToken = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(encryptedToken);
    }

    public boolean validToken(String encryptedToken) throws ExpiredJwtException {
    boolean flag = true;
        try{
            Date expirationDate=Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(encryptedToken)
                .getBody()
                .getExpiration();
        Date currentDate= Calendar.getInstance().getTime();
        flag = true;
        }
        catch(ExpiredJwtException e){
            flag = false;
            throw e;}
        finally{return flag;}

    }
    public String getTokenDetails(String encryptedToken){
        String email = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(encryptedToken)
                .getBody()
                .getSubject();
        return email;
    }

}

