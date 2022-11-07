package com.study.spring.gallery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "items")
public class Item {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int pid;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(name = "imgPath")
  private String imgPath;

  @Column(name = "price")
  private int price;

  @Column(name = "discount_per")
  private int discountPer;

}
