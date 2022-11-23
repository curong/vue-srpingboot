package com.study.spring.gallery.web;


import com.study.spring.gallery.dto.OrderDTO;
import com.study.spring.gallery.entity.Order;
import com.study.spring.gallery.repository.OrderRepository;
import com.study.spring.gallery.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRController {

    private final JwtService jwtService;
    private final OrderRepository orderRepository;

    @GetMapping("/list")
    public ResponseEntity getOrder
            (
                    @CookieValue(value = "token", required = false) String token
            ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        List<Order> orders = orderRepository.findAll();

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }


    @PostMapping("push")
    public ResponseEntity pushOrder
            (
                    @RequestBody OrderDTO orderDTO,
                    @CookieValue(value = "token", required = false) String token
            ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Order newOrder = new Order();
        newOrder.setMemberId(jwtService.getId(token));
        newOrder.setName(orderDTO.getName());
        newOrder.setAddress(orderDTO.getAddress());
        newOrder.setPayment(orderDTO.getPayment());
        newOrder.setCardNumber(orderDTO.getCardNumber());
        newOrder.setItems(orderDTO.getItems());

        orderRepository.save(newOrder);



        return new ResponseEntity<>(HttpStatus.OK);
    }


}
