package com.prasad.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class StripePaymentGateway implements PaymentGateway {

    private final Logger logger = Logger.getLogger(StripePaymentGateway.class.getName());

    @Value("${STRIPE_API_KEY}")
    private String stripeKey;

    @Override
    public String getPaymentLink(Long orderId, String productName, Long amount) throws StripeException {
        Stripe.apiKey = stripeKey;

        logger.info("Stripe API Key: " + stripeKey);

        PriceCreateParams priceCreateParams = PriceCreateParams.builder()
                .setCurrency("INR")
                .setUnitAmount(amount)
                .setProductData(
                        PriceCreateParams.ProductData.builder()
                                .setName(productName)
                                .build()
                )
                .build();

        Price price = Price.create(priceCreateParams);
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://welcometowelcomehome.neocities.org")
                                                        .build()
                                        )
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        logger.info("Payment link created: " + paymentLink.toString());
        return paymentLink.getUrl();
    }
}
