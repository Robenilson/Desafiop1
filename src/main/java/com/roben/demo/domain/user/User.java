package com.roben.demo.domain.user;


import com.roben.demo.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String fristName;
    private  String lastName;
    @Column(unique = true)
    private  String document;
    @Column(unique = true)
    private String email;
    private  String password;
    private BigDecimal banlance;
    @Enumerated(EnumType.STRING)
    private  UserType userType;

     public     User(UserDTO data){
         this.fristName= data.fristName();
         this.lastName= data.lastName();
         this.document= data.document();
         this.email=data.email();
         this.password=data.password();
         this.banlance=data.banlance();
         this.userType=data.userType();
     }



}
