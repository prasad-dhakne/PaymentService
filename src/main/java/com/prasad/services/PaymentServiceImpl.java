package com.prasad.services;

import com.prasad.paymentgateway.StripePaymentGateway;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service

public class PaymentServiceImpl implements PaymentService {

    private final Logger logger = Logger.getLogger(PaymentServiceImpl.class.getName());
    private final StripePaymentGateway stripePaymentGateway;

    PaymentServiceImpl(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    @Override
    public String getPaymentLink(Long orderId, String productName, Long amount) {
        try {
            return stripePaymentGateway.getPaymentLink(orderId, productName, amount);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }
}
