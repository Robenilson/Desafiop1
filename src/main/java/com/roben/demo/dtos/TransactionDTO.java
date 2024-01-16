package com.roben.demo.dtos;

import com.roben.demo.domain.user.User;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
