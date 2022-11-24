package com.study.spring.gallery.web;


import com.study.spring.gallery.dto.OrderDTO;
import com.study.spring.gallery.entity.Order;
import com.study.spring.gallery.repository.CartRepository;
import com.study.spring.gallery.repository.OrderRepository;
import com.study.spring.gallery.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRController {

    private final JwtService jwtService;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @GetMapping("/list")
    public ResponseEntity getOrder
            (
                    @CookieValue(value = "token", required = false) String token
            )
    {

        if (!jwtService.isValid(token))
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        List<Order> orders = orderRepository.findByMemberIdOrderByPidDesc(memberId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @Transactional
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
        int memberId = jwtService.getId(token);
        newOrder.setMemberId(memberId);
        newOrder.setName(orderDTO.getName());
        newOrder.setAddress(orderDTO.getAddress());
        newOrder.setPayment(orderDTO.getPayment());
        newOrder.setCardNumber(orderDTO.getCardNumber());
        newOrder.setItems(orderDTO.getItems());

        orderRepository.save(newOrder);


        cartRepository.deleteByMemberId(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
