package com.ofa.epttavm.user.entity;


import com.ofa.epttavm.user.dto.UserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    @Builder.Default
    private UserRole role = UserRole.USER;

    @Column(name = "logged_in")
    @Builder.Default
    private Boolean loggedIn = false;

    @Column(name = "basket")
    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    private List<String> basket = new ArrayList<>(); // keep only product codes in user's basket
}