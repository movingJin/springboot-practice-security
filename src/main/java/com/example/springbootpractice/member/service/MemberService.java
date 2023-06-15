package com.example.springbootpractice.member.service;

import com.example.springbootpractice.member.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findMembers();
    Member getMemberByEmail(String email);
    void join(Member member);
}
