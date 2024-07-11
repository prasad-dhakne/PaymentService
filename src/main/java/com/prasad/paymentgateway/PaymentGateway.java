package com.prasad.paymentgateway;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String getPaymentLink(Long orderId, String productName, Long amount) throws StripeException;
}
