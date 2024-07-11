package com.prasad.services;

public interface PaymentService {
    String getPaymentLink(Long orderId, String productName, Long amount);
}
