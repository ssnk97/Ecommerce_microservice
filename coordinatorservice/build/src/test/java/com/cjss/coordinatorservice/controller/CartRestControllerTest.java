package com.cjss.coordinatorservice.controller;


import com.cjss.coordinatorservice.GetLoginToken;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test(groups ="cartService", dependsOnGroups = {"accountService", "productService", "inventoryService"})
public class CartRestControllerTest {

    @Test
    public void addToCart() {
        String addEmployeePayload ="{\n" +
                "    \"productCode\":\"SCTN1\",\n" +
                "    \"skuCode\":\"sku1\",\n" +
                "    \"quantity\":5\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .header("encryptedToken", GetLoginToken.getEncryptedToken())
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/add-cart")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "addToCart")
    public void viewCart() {

        given()
                .contentType(ContentType.JSON)
                .header("encryptedToken", GetLoginToken.getEncryptedToken())
                .get("http://localhost:8080/coordinator/view-cart")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "viewCart")
    public void placeOrder() {
        given()
                .contentType(ContentType.JSON)
                .header("encryptedToken", GetLoginToken.getEncryptedToken())
                .post("http://localhost:8080/coordinator/place-order?addressId=1")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "placeOrder")
   public void itemStatus() {
        given()
                .contentType(ContentType.JSON)
                .header("encryptedToken", GetLoginToken.getEncryptedToken())
                .get("http://localhost:8080/coordinator/get-item-status/1")
                .then()
                .statusCode(302);
    }
}