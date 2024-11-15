package com.shubhada.paymentservice.services;

import com.shubhada.paymentservice.paymentgateways.stripe.StripePaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private StripePaymentGateway stripePaymentGateway;
    public PaymentService(StripePaymentGateway stripePaymentGateway){
        this.stripePaymentGateway=stripePaymentGateway;
    }
    public String createPaymentLink(Long orderId) throws StripeException {
        //String emailOfCustomer=
        //String phoneNumberOfCustomer=
        //Long amount=

        //Order order=restClient.get(localhost:9000/orders/{orderId})
        //Long userId=order.getUserId();
        //User user=restClient.get(localhost:9000/users/{userId})
        //String emailOfCustomer=user.getEmail()
        //String phoneNumber=user.getPhoneNumber()
        //Long amount=order.getTotalAmount()

        return stripePaymentGateway.generatePaymentLink(10000L,orderId.toString());//this is 100 rs
    }
}
