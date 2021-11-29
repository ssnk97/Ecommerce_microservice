package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.GetLoginToken;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test(groups = "productService")
public class ProductRestControllerTest {

    @Test
    public void addProduct() {
        String addEmployeePayload ="{\n" +
                "    \"productCode\":\"SCTN1\",\n" +
                "    \"productName\":\"SUPERDRY\",\n" +
                "     \"description\":\"COTTTON SHIRT\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/customer/add-product")
                .then()
                .statusCode(201);
    }

    @Test(dependsOnMethods = "addProduct")
    public void addSkusSize() {
        String addEmployeePayload ="{\n" +
                "    \"skuCode\":\"sku1\",\n" +
                "    \"productCode\":\"SCTN1\",\n" +
                "    \"size\":\"M\"\n" +
                "}\n";

        given()
                .contentType(ContentType.JSON)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/customer/add-sku")
                .then()
                .statusCode(201);
    }

    @Test(dependsOnMethods = "addSkusSize")
    public void addPrice() {
        String addEmployeePayload ="{\n" +
                "     \"skuCode\":\"sku1\",\n" +
                "    \"price\":\"2000\",\n" +
                "    \"currency\":\"Rs\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/customer/add-price")
                .then()
                .statusCode(201);
    }
}