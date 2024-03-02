package com.kirsing.accountmicroservice.service.implementation;

import com.kirsing.accountmicroservice.constants.AccountConstants;
import com.kirsing.accountmicroservice.dto.AccountMsgDto;
import com.kirsing.accountmicroservice.dto.AccountsDto;
import com.kirsing.accountmicroservice.dto.CustomerDto;
import com.kirsing.accountmicroservice.entity.Accounts;
import com.kirsing.accountmicroservice.entity.Customer;
import com.kirsing.accountmicroservice.exception.CustomerAlreadyExistsException;
import com.kirsing.accountmicroservice.exception.ResourceNotFoundException;
import com.kirsing.accountmicroservice.mapper.AccountsMapper;
import com.kirsing.accountmicroservice.mapper.CustomerMapper;
import com.kirsing.accountmicroservice.repository.AccountsRepository;
import com.kirsing.accountmicroservice.repository.CustomerRepository;
import com.kirsing.accountmicroservice.service.IAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImplementation implements IAccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImplementation.class);

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer has already been registered with this mobile number "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    private void sendCommunication(Accounts account, Customer customer) {
        var accountsMsgDto = new AccountMsgDto(account.getAccountNumber(), customer.getName(),
                customer.getEmail(), customer.getMobileNumber());
        log.info("Sending Communication request for the details: {}", accountsMsgDto);
        boolean result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Is the Communication request successfully triggered?: {}", result);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public Customer checkCustomer(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = checkCustomer(mobileNumber);
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());


        newAccount.setAccountNumber(1000000L + new Random().nextInt(9000000));
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
  }
    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if(accountNumber != null) {
            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
            );
            accounts.setCommunicationSw(true);
            accountsRepository.save(accounts);
            isUpdated= true;
        }
        return isUpdated;
    }

}
