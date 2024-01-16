package com.roben.demo.dtos;

import com.roben.demo.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String fristName, String lastName, String document, String email, String password, BigDecimal banlance, UserType userType) {
}
