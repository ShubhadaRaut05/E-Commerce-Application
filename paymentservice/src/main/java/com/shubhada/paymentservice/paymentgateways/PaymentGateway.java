package com.shubhada.paymentservice.paymentgateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    public String generatePaymentLink(Long amount,String orderId) throws StripeException;
}
