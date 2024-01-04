package com.kirsing.accountmicroservice.service;


import com.kirsing.accountmicroservice.dto.CustomerDetailsDto;

public interface ICustomersService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
