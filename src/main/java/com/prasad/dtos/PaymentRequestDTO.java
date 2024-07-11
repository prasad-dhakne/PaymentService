package com.prasad.dtos;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private String productName;
    private Long amount;
}
