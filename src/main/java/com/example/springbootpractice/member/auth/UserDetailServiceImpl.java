package com.example.springbootpractice.member.auth;

import com.example.springbootpractice.member.auth.MemberDetails;
import com.example.springbootpractice.member.entity.Member;
import com.example.springbootpractice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Member> user = memberRepository.findByEmail(username);
        return user.map(MemberDetails::new).orElse(null);
    }

}