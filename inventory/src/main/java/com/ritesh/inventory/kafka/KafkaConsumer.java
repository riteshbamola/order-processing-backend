package com.ritesh.inventory.kafka;

import com.ritesh.common.dto.CommonDTO;
import com.ritesh.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.AclEntry;

@Service
public class KafkaConsumer {

    @Autowired
    private InventoryService service;
    @KafkaListener(topics = "invenotry.reserve")
    public void reserveInventory(CommonDTO event, Acknowledgment acknowledgment){
        try {
            service.reserveInventory(event.getProductId(), event.getQuantity());

            //CDC event to orchestrator == inventory.reserved

        }catch (Exception e){
            System.out.println(e.getMessage());

            //CDC event to Orchestrator //Not found or Invenotry
        }
        acknowledgment.acknowledge();
    }

    public void releaseInventory(CommonDTO event, Acknowledgment acknowledgment){
        try{
            service.releaseInventory(event.getProductId(),event.getQuantity());


            //cdc Event to orchestrattor  inventory.released
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
