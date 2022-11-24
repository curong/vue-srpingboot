package com.study.spring.gallery.repository;

import com.study.spring.gallery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByMemberIdOrderByPidDesc(int memberId);

}
