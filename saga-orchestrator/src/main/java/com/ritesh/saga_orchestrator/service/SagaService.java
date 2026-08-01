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


    public void updateSaga(){


    }

    public void updateSagaStep(){

    }

    public void startPayment(CommonDTO event) {
    }

    public void reserveInventory(CommonDTO event) {
            //save sagastep  -- pending
            // save saga -- pending
            String topic = "INVENTORY.RESERVE";
            kafkaProducer.sendMessage(event, topic);

    }

    public void updateOrder(CommonDTO event) {

        //sgaa step
        //saga
        String topic = "ORDER.CANCEL";
        kafkaProducer.sendMessage(event, topic);
    }

    public void releaseInventory(CommonDTO event) {
        String topic = "INVENTORY.RELEASE";
        kafkaProducer.sendMessage(event,topic);
    }
}
