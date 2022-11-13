package com.study.spring.gallery.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "carts")
public class Cart {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int pid;

  @Column(name = "member_id")
  private int memberId;

  @Column(name = "item_id")
  private String itemId;

}
