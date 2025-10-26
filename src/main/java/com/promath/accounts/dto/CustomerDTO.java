package com.promath.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class CustomerDTO {

    @NotEmpty(message = "The name cannot be null or empty ")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "The email cannot be null or empty")
    @Email(message = "Email address should contain a valid data")
//    @Pattern(regexp = "\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message = "The email field is invalid")
    private String email;

    @NotEmpty(message = "Mobile number should not be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "The mobile number must be 10 digits")
    private String mobileNumber;

    private @Nullable AccountDTO accountDTO;
}
