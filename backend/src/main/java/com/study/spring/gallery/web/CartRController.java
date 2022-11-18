package com.study.spring.gallery.web;


import com.study.spring.gallery.entity.Cart;
import com.study.spring.gallery.entity.Item;
import com.study.spring.gallery.repository.CartRepository;
import com.study.spring.gallery.repository.ItemRepositroy;
import com.study.spring.gallery.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartRController {

    private final JwtService jwtService;
    private final ItemRepositroy itemRepositroy;
    private final CartRepository cartRepository;

    @GetMapping("/items")
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).collect(Collectors.toList());

        List<Item> items = itemRepositroy.findByPidIn(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/items/{itemId}")
    public ResponseEntity pushCartItem
            (
                    @PathVariable("itemId") int itemId,
                    @CookieValue(value = "token", required = false) String token
            ) {
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setItemId(itemId);
            newCart.setMemberId(memberId);
            cartRepository.save(newCart);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("items/{itemId}")
    public ResponseEntity removeCartItem
            (
                    @PathVariable("itemId") int itemId,
                    @CookieValue(value = "token", required = false) String token
            ) {


        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        cartRepository.delete(cart);
        return new ResponseEntity(HttpStatus.OK);
    }

}
