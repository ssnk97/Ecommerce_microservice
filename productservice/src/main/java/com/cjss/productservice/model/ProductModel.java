package com.cjss.productservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductModel {
    @NotNull(message = "productCode must not be null")
    @NotEmpty(message = "productCode must not be empty")
    private String productCode;
    @NotNull(message = "productName must not be null")
    @NotEmpty(message = "productName must not be empty")
    private String productName;
    private String description;

    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
