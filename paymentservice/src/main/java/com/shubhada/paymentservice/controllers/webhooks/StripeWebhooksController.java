package com.shubhada.paymentservice.controllers.webhooks;

import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhooksController {
    @PostMapping("/")
    public void handleWebhookRequest(Event webhookEvent){

        return;
    }
}
