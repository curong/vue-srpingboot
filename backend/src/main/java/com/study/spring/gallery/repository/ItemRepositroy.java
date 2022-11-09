package com.study.spring.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.spring.gallery.entity.Item;

@Repository
public interface ItemRepositroy extends JpaRepository<Item, Integer>{
  
}
