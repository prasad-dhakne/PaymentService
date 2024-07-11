package com.prasad.controllers;

import com.prasad.dtos.PaymentRequestDTO;
import com.prasad.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/link")
    public ResponseEntity<String> initiatePayment(@RequestBody
                                                  PaymentRequestDTO paymentRequestDTO){
        String link = paymentService.getPaymentLink(paymentRequestDTO.getOrderId(),
                paymentRequestDTO.getProductName(),
                paymentRequestDTO.getAmount());
        return new ResponseEntity<>(link, HttpStatus.OK);
    }

    @GetMapping("/link1")
    public ResponseEntity<String> initiatePaymentHC(){
        String link = paymentService.getPaymentLink(1L,
                "Node Js Course",
                50000L);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }
}
