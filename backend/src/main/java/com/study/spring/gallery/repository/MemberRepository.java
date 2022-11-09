package com.study.spring.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.spring.gallery.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

  Member findByEmailAndPassword(String email, String password);

}
