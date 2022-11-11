package com.study.spring.gallery.service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;

public class JwtServiceImpl implements JwtService{

  private String secretKey = "abbbasdqwasdqmklwqemlams!@#!@#fkmkmlkmkamdasmdlemqwejasji23123";

  @Override
  public String getToken(String key, Object value) {
    Date expTime = new Date();
    expTime.setTime(expTime.getTime() + 1000 * 60 * 5);

    byte[] secretKeyByte = DatatypeConverter.parseBase64Binary(secretKey);
    Key signKey = new SecretKeySpec(secretKeyByte, SignatureAlgorithm.HS256.getJcaName());

    Map<String, Object> headerMap = new HashMap<>();
    headerMap.put("typ", "JWT");
    headerMap.put("alg", "HS256");

    Map<String, Object> map = new HashMap<>();
    map.put(key, value);

    JwtBuilder builder = Jwts.builder()
                              .setHeader(headerMap)
                              .setClaims(map)
                              .setExpiration(expTime)
                              .signWith(signKey, SignatureAlgorithm.HS256);

    return builder.compact();
  }

  @Override
  public Claims getClaims(String token) {
    if (token != null) {
      try {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
        Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
        return claims;
      } catch (ExpiredJwtException e) {
          // 만료됨
      } catch (JwtException e) {
        // 유효하지 않음
      }
    }
    return null;
  }
}
