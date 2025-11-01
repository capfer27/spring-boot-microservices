package com.capfer.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CardDTO {

    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer", example = "4354437687"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "The CardType cannot be null or empty")
    @Schema(
            description = "The type of the card", example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "The card limit should be greater than zero")
    @Schema(
            description = "The total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "The total amount used should be equal or greater than zero")
    @Schema(
            description = "The total amount used by the customer", example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "The total available amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    private int availableAmount;
}
