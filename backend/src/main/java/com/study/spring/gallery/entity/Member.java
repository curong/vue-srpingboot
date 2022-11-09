package com.study.spring.gallery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int pid;

  @Column(name = "member_email", nullable = false)
  private String email;

  @Column(name = "member_pw", length = 100, nullable = false)
  private String password;


}
