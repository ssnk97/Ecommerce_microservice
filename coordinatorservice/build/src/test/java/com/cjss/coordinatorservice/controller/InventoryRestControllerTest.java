package com.cjss.coordinatorservice.controller;


import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test(groups = "inventoryService", dependsOnGroups = "productService")
public class InventoryRestControllerTest {

    @Test
    public void addInventory() {
        String addEmployeePayload ="{\n" +
                "    \"quantityAvailable\":\"5\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/add-inventory?skuCode=sku1")
                .then()
                .statusCode(201);
    }
}