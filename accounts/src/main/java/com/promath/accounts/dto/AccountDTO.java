package com.promath.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information's"
)
public class AccountDTO {

    @Schema(
        description = "Account Number of Capfer Bank",
        example = "4049543221"
    )
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type of Capfer Bank",
            example = "Savings"
    )
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Capfer Bank branch address",
            example = "123 Los Angeles"
    )
    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;
}
