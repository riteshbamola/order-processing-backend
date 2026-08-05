package com.ritesh.payment.service;

import com.ritesh.common.dto.CommonDTO;
import com.ritesh.payment.db.Payment;
import com.ritesh.payment.db.PaymentRepo;
import com.ritesh.payment.db.PaymentSattus;
import lombok.Data;
import com.ritesh.payment.kafka.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class PaymentService {

    private PaymentRepo paymentRepo;

    public PaymentService(PaymentRepo paymentRepo){
        this.paymentRepo= paymentRepo;
    }


    public void startPayment(CommonDTO commonDTO){
        
        Payment payment = new Payment();
        Payment.builder()
                .amount(commonDTO.getAmount())
                .orderId(commonDTO.getOrderId())
                .productId(commonDTO.getProductId())
                .build();

        paymentRepo.save(payment);
        int success = (int) (Math.random() * 10);


        if(success > 7){

            payment.setStatus(PaymentSattus.SUCCESS);

            ////cdc service success message

        }else{
            payment.setStatus(PaymentSattus.FAILED);

            ////cdc service failed message

        }

        paymentRepo.save(payment);
    }


}
