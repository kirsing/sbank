package com.kirsing.accountmicroservice.service;

import com.kirsing.accountmicroservice.dto.CustomerDto;
import com.kirsing.accountmicroservice.entity.Customer;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    Customer checkCustomer(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
