package com.promath.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer, Accounts, Cards and Loans information"
)
public class CustomerDetailsDTO {

    @Schema(
            description = "The name of the customer",
            example = "Mestre Caito"
    )
    @NotEmpty(message = "The name cannot be null or empty ")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "The email of the customer",
            example = "example123@gmail.com"
    )
    @NotEmpty(message = "The email cannot be null or empty")
    @Email(message = "Email address should contain a valid data")
    private String email;

    @Schema(
            description = "The mobile number of the customer",
            example = "3324567654"
    )
    @NotEmpty(message = "Mobile number should not be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "The mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "The account details of the Customer")
    private @Nullable AccountDTO accountDTO;

    @Schema(description = "The Cards details of the Customer")
    private CardsDTO cardsDTO;

    @Schema(description = "The Loans details of the Customer")
    private LoansDTO loansDTO;
}
