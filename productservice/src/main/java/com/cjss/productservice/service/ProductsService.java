package com.cjss.productservice.service;

import com.cjss.productservice.entity.*;
import com.cjss.productservice.model.*;
import com.cjss.productservice.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductSizeRepository productSizeRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;


    public String addProduct(ProductModel productModel){
        Optional<ProductEntity> product = Optional.ofNullable(productRepository.findByProductCode(productModel.getProductCode()));

        if(!product.isPresent()) {
            ProductEntity productEntity = new ProductEntity();
            List<ProductSizeEntity> productSizeList = new ArrayList<>();
            productEntity.setProductCode(productModel.getProductCode());
            productEntity.setProductName(productModel.getProductName());
            productEntity.setDescription(productModel.getDescription());
            productEntity.setProductSizeList(productSizeList);
            productRepository.save(productEntity);
            return "PRODUCT ADDED";
        }
        else return "PRODUCT CODE ALREADY EXISTS";
    }
    public String addSkusSize(ProductSizeModel productSizeModel){  //SKUS == SIZE
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductCode(productSizeModel.getProductCode()));
        Optional<ProductSizeEntity> skuAvl = Optional.ofNullable(productSizeRepository.findBySkuCode(productSizeModel.getSkuCode()));
        if(productEntity.isPresent() && !skuAvl.isPresent()){
           ProductSizeEntity productSizeEntity = new ProductSizeEntity();
           productSizeEntity.setSkuCode(productSizeModel.getSkuCode());
           productSizeEntity.setSize(productSizeModel.getSize());
           productSizeEntity.setProductEntity(productEntity.get());
           productEntity.get().getProductSizeList().add(productSizeEntity);
           productRepository.save(productEntity.get());
           return "SKU ADDED";
       }else
           return "PRODUCT CODE NOT FOUND OR SKU ALREADY AVAILABLE";
    }

    public String addPrice(ProductPriceModel productPriceModel){
        Optional<ProductSizeEntity> productSizeEntity = Optional.ofNullable(productSizeRepository.findBySkuCode(productPriceModel.getSkuCode()));
        Optional<ProductPriceEntity> skuPriceAvl = Optional.ofNullable(productPriceRepository.findBySkuCode(productPriceModel.getSkuCode()));

        if(productSizeEntity.isPresent() && !skuPriceAvl.isPresent()) {
            ProductPriceEntity productPriceEntity = new ProductPriceEntity();
            productPriceEntity.setPrice(productPriceModel.getPrice());
            productPriceEntity.setCurrency(productPriceModel.getCurrency());
            productPriceEntity.setSkuCode(productPriceModel.getSkuCode());
            productSizeEntity.get().setProductPriceEntity(productPriceEntity);
            productSizeRepository.save(productSizeEntity.get());
            return "PRICE ADDED";
        }
      else return "SKU CODE NOT FOUND OR AVAILABLE IN PRICE REPO";
    }

}