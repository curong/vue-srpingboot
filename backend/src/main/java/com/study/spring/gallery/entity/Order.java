package com.study.spring.gallery.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int pid;

  @Column(name = "member_id")
  private int memberId;

  @Column(name = "name")
  private String name;

  @Column(name = "address", length = 500, nullable = false)
  private String address;

  @Column(name = "payment", length = 10, nullable = false)
  private String payment;

  @Column(name = "cardNumber", length = 16)
  private String cardNumber;

  @Column(name = "items", length = 500, nullable = false)
  private String items;



}
