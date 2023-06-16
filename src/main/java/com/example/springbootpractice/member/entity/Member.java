package com.example.springbootpractice.member.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="member",
        uniqueConstraints = {@UniqueConstraint(
                name = "email_unique",
                columnNames = {"email"} )
        })
@DynamicUpdate
@Builder
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    private String role; //ROLE_USER, ROLE_ADMIN

}
