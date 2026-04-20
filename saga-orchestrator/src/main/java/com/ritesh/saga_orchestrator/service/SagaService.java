package com.ritesh.saga_orchestrator.service;

import com.ritesh.saga_orchestrator.db.SagaRepo;
import com.ritesh.saga_orchestrator.db.SagaStep;
import com.ritesh.saga_orchestrator.db.SagaStepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaService {

    private SagaRepo sagaRepo;
    private SagaStepRepo sagaStepRepo;


    public SagaService(SagaRepo sagaRepo, SagaStepRepo sagaStepRepo){
        this.sagaRepo= sagaRepo;
        this.sagaStepRepo=sagaStepRepo;
    }


    public void updateSaga(){


    }

    public void updateSagaStep(){

    }

}
