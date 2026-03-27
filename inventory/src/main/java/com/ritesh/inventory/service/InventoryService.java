package com.ritesh.inventory.service;

import com.ritesh.inventory.model.Products;
import com.ritesh.inventory.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ProductRepo productRepo;
    public void reserveInventory(String productId, int quanity) throws  Exception{

        Products product = productRepo.findById(productId).orElseThrow(() -> new Exception("Product Not Found"));

        int remain = product.getStock() - quanity;
        if(remain >= 0){
            product.setStock(remain);
            productRepo.save(product);
        }
        else{
            throw new Exception("Out of Stock");
        }
    }

    public void releaseInventory(String productId, int quanity) throws  Exception{
        Products product = productRepo.findById(productId).orElseThrow(() -> new Exception("Product Not Found"));
        int currStock = product.getStock();
            product.setStock(currStock+quanity);
            productRepo.save(product);

    }
}
