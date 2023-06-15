package com.example.springbootpractice.member.repository;

import com.example.springbootpractice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String userId);
    Optional<Member> findByEmail(String email);
}

