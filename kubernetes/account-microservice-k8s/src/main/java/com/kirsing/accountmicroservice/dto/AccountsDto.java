package com.kirsing.accountmicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "Account Number can't be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "AccountNumber of SBANK account", example = "3453335081"
    )
    private Long accountNumber;

    @Schema(
            description = "Account type of SBANK account", example = "Savings"
    )
    @NotEmpty(message = "Account Type can't be a null or empty")
    private String accountType;
    @Schema(
            description = "SBANK branch address", example = "17 Kirova street, Gomel"
    )
    @NotEmpty(message = "Branch address can't be a null or empty")
    private String branchAddress;
}
