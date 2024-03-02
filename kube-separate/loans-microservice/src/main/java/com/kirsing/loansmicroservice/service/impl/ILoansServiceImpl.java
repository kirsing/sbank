package com.kirsing.loansmicroservice.service.impl;

import com.kirsing.loansmicroservice.constants.LoansConstants;
import com.kirsing.loansmicroservice.dto.LoansDto;
import com.kirsing.loansmicroservice.entity.Loans;
import com.kirsing.loansmicroservice.exceptions.LoanAlreadyExistsException;
import com.kirsing.loansmicroservice.exceptions.ResourceNotFoundException;
import com.kirsing.loansmicroservice.mapper.LoansMapper;
import com.kirsing.loansmicroservice.repository.LoansRepository;
import com.kirsing.loansmicroservice.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ILoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        isLoanRegistered(mobileNumber);
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        newLoan.setLoanNumber(Long.toString(100000000000L + new Random().nextInt(900000000)));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = findLoanByMobileNumber(mobileNumber);
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = findLoanByLoanNumber(loansDto.getLoanNumber());
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = findLoanByMobileNumber(mobileNumber);
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
    private void isLoanRegistered(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loans already has registered with given mobileNumber");
        }
    }
    private Loans findLoanByMobileNumber(String mobileNumber) {
        return loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
        );
    }
    private Loans findLoanByLoanNumber(String loanNumber) {
        return loansRepository.findByLoanNumber(loanNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loan number", loanNumber)
        );
    }
}
