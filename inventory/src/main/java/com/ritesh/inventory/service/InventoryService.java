package com.ritesh.inventory.service;

import com.ritesh.common.dto.CommonDTO;
import com.ritesh.common.dto.OrderStatus;
import com.ritesh.inventory.kafka.KafkaProducer;
import com.ritesh.inventory.model.Products;
import com.ritesh.inventory.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private ProductRepo productRepo;
    private KafkaProducer kafkaProducer;


    public InventoryService(ProductRepo productRepo, KafkaProducer kafkaProducer){
        this.kafkaProducer= kafkaProducer;
        this.productRepo= productRepo;
    }
    public void reserveInventory(CommonDTO event) throws  Exception{

        Products product = productRepo.findById(event.getProductId()).orElseThrow(() -> new Exception("Product Not Found"));
        String topic;
        int remain = product.getStock() - event.getQuantity();
        if(remain >= 0){
            product.setStock(remain);
            productRepo.save(product);
            topic = "INVENTORY_RESERVED";
        }
        else{
            topic = "INVENTORY_FAILED";
            event.setOrderStatus(OrderStatus.FAILED);
            throw new Exception("Out of Stock");
        }
        kafkaProducer.sendMessage(event,topic);
    }

    public void releaseInventory(String productId, int quanity) throws  Exception{
        Products product = productRepo.findById(productId).orElseThrow(() -> new Exception("Product Not Found"));
        int currStock = product.getStock();
            product.setStock(currStock+quanity);
            productRepo.save(product);

    }
}
