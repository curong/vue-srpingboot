package com.study.spring.gallery.web;


import com.study.spring.gallery.entity.Member;
import com.study.spring.gallery.repository.MemberRepository;
import com.study.spring.gallery.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class MemberRController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, Object> params, HttpServletResponse res) {
        Member member = memberRepository.findByEmailAndPassword((String) params.get("email"),
                (String) params.get("password"));

        if (member != null) {

            int id = member.getPid();

            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        // 아이디 정보가 없을 경우 thorw 처리
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
