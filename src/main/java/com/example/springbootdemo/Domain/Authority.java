package com.example.springbootdemo.Domain;


import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    private String authority;
}
