package com.kirsing.accountmicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto  {

    @Schema(
            description = "Name of the customer", example = "Kirsing"
    )
     @NotEmpty(message = "Name can't be a null or empty")
     @Size(min=2, max=10, message = "The length of the customer name should be between 2 and 10 symbols")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "kirillru98@mail.ru"
    )
     @NotEmpty(message = "Email can't be a null or empty")
     @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "8044785437"
    )
     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}
