package com.study.spring.gallery.web;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.study.spring.gallery.entity.Member;
import com.study.spring.gallery.repository.MemberRepository;
import com.study.spring.gallery.service.JwtService;
import com.study.spring.gallery.service.JwtServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class MemberRController {

  @Autowired
  private MemberRepository memberRepository;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Map<String, Object> params, HttpServletResponse res) {
    Member member = memberRepository.findByEmailAndPassword((String) params.get("email"),
        (String) params.get("password"));

    if (member != null) {

      JwtService jwtService = new JwtServiceImpl();
      int id = member.getPid();

      String token = jwtService.getToken("id", id);
      
      Cookie cookie = new Cookie(token, token);
      cookie.setHttpOnly(true);
      cookie.setPath("/");

      res.addCookie(cookie);
      return ResponseEntity.ok().build();
    }

    // 아이디 정보가 없을 경우 thorw 처리
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

}
