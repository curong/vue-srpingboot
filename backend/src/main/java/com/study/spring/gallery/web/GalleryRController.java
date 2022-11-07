package com.study.spring.gallery.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.spring.gallery.entity.Item;
import com.study.spring.gallery.repository.ItemRepositroy;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GalleryRController {

  @Autowired
  private ItemRepositroy itemRepositroy;

  @GetMapping("/items")
  public List<Item> getItems() {
    List<Item> items = itemRepositroy.findAll();
    return items;
  }

}
