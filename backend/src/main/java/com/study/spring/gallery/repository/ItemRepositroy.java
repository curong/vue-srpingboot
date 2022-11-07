package com.study.spring.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.spring.gallery.entity.Item;

public interface ItemRepositroy extends JpaRepository<Item, Integer>{
  
}
