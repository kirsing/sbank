package com.kirsing.accountmicroservice.dto;

public record AccountMsgDto(
        Long accountNumber, String name, String email, String mobileNumber) {
}
