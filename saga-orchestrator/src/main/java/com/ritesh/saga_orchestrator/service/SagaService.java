package com.ritesh.saga_orchestrator.service;

import com.ritesh.common.dto.CommonDTO;
import com.ritesh.saga_orchestrator.db.Saga;
import com.ritesh.saga_orchestrator.db.SagaRepo;
import com.ritesh.saga_orchestrator.db.SagaStep;
import com.ritesh.saga_orchestrator.db.SagaStepRepo;
import com.ritesh.saga_orchestrator.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SagaService {

    private SagaRepo sagaRepo;
    private SagaStepRepo sagaStepRepo;
    private KafkaProducer kafkaProducer;

    public SagaService(SagaRepo sagaRepo, SagaStepRepo sagaStepRepo, KafkaProducer kafkaProducer){
        this.sagaRepo= sagaRepo;
        this.sagaStepRepo=sagaStepRepo;
        this.kafkaProducer= kafkaProducer;
    }

    public void startPayment(CommonDTO event) {
        String topic = "PAYMENT_START";
        kafkaProducer.sendMessage(event,topic);
    }

    public void reserveInventory(CommonDTO event) {
        String topic = "INVENTORY_RESERVE";
        kafkaProducer.sendMessage(event, topic);

    }
    public void cancelOrder(CommonDTO event) {
        String topic = "ORDER_CANCEL";
        kafkaProducer.sendMessage(event, topic);

    }

    public  void completeOrder(CommonDTO event){
        String topic = "ORDER_COMPLETE";
        kafkaProducer.sendMessage(event,topic);
    }
    public void releaseInventory(CommonDTO event) {
        String topic = "INVENTORY_RELEASE";
        kafkaProducer.sendMessage(event,topic);
    }
}