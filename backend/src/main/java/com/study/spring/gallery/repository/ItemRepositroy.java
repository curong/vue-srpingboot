package com.study.spring.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.spring.gallery.entity.Item;

import java.util.List;

@Repository
public interface ItemRepositroy extends JpaRepository<Item, Integer>{
    List<Item> findByIdIn(List<Integer> ids);
}
