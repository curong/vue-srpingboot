package com.study.spring.gallery.repository;

import com.study.spring.gallery.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByMemberId(int memberId);

    Cart findByMemberIdAndItemId(int memberId, int itemId);

}
