package com.promath.accounts.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private @Nullable AccountDTO accountDTO;
}
