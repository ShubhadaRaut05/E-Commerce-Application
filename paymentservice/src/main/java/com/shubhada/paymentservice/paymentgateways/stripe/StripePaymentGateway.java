package com.shubhada.paymentservice.paymentgateways.stripe;

import com.shubhada.paymentservice.paymentgateways.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.secret_key}")
    private String stripeSecretKey;
    @Override
    public String generatePaymentLink(Long amount, String orderId) throws StripeException {
        //just call the stripe api and generate payment link
        //1. set up your product catalog means create price object

        Stripe.apiKey = stripeSecretKey;
        //create product
        ProductCreateParams params =
                ProductCreateParams.builder().setName("GENERIC").build();
        Product product = Product.create(params);

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)//amount*100=Rs 10=>1000
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(priceCreateParams);
        //2. create a payment link
       // Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://scaler.com/?order_id="+orderId).build()
                                        ).build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }
}
