package com.example.springbootpractice.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private  String name;
    private String password;
    private String email;
    private String role;
}