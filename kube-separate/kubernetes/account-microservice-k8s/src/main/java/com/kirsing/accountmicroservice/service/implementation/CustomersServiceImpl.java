package com.kirsing.accountmicroservice.service.implementation;


import com.kirsing.accountmicroservice.dto.AccountsDto;
import com.kirsing.accountmicroservice.dto.CardsDto;
import com.kirsing.accountmicroservice.dto.CustomerDetailsDto;
import com.kirsing.accountmicroservice.dto.LoansDto;
import com.kirsing.accountmicroservice.entity.Accounts;
import com.kirsing.accountmicroservice.entity.Customer;
import com.kirsing.accountmicroservice.exception.ResourceNotFoundException;
import com.kirsing.accountmicroservice.mapper.AccountsMapper;
import com.kirsing.accountmicroservice.mapper.CustomerMapper;
import com.kirsing.accountmicroservice.repository.AccountsRepository;
import com.kirsing.accountmicroservice.repository.CustomerRepository;
import com.kirsing.accountmicroservice.service.ICustomersService;
import com.kirsing.accountmicroservice.service.client.CardsFeignClient;
import com.kirsing.accountmicroservice.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if(null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        return customerDetailsDto;

    }
}