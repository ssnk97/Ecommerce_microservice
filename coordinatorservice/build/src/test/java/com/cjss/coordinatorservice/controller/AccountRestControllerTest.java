package com.cjss.coordinatorservice.controller;



import com.cjss.coordinatorservice.GetLoginToken;
import io.restassured.http.ContentType;
//import org.junit.Test;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(MockitoJUnitRunner.class)
@Test(groups = "accountService")
public class AccountRestControllerTest {

   @Test
    public void customerRegistration() {
        String addEmployeePayload = "{\n" +
                "            \"firstName\": \"Satya\",\n" +
                "                \"lastName\": \"Surisetty\",\n" +
                "                \"email\": \"satya@cjss.com\",\n" +
                "                \"mobileNumber\": 8341130864,\n" +
                "                \"password\": \"password\"\n" +
                "        }";


        given()
                .contentType(ContentType.JSON)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/customer/register")
                .then()
                .statusCode(201);
    }


    @Test(dependsOnMethods = "customerRegistration")
    public void login() {

       String addEmployeePayload = "{\"userEmail\": \"satya@cjss.com\",\n" +
                "\"password\": \"password\"}";
       String encryptedToken = given()
                .contentType(ContentType.JSON)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/customer/login")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
               .extract().asString();
       System.out.println(encryptedToken);
       GetLoginToken.setEncryptedToken(encryptedToken);
    }

    @Test(dependsOnMethods = "customerRegistration")
    public void addAddress() {
        String addEmployeePayload = "        {\n" +
                "            \"line1\":\"abc2\",\n" +
                "            \"line2\":\"xyz\",\n" +
                "            \"postalCode\":533440,\n" +
                "            \"city\":\"KAKINADA\",\n" +
                "            \"state\":\"ANDHRA PRADESH\",\n" +
                "           \"shippingAddress\":true,\n" +
                "           \"billingAddress\":false\n" +
                "        }";

        String encryptedToken = GetLoginToken.getEncryptedToken();

             given()
                .contentType(ContentType.JSON)
                .header("encryptedToken", encryptedToken)
                .body(addEmployeePayload)
                .post("http://localhost:8080/coordinator/customer/add-address")
                .then()
                .statusCode(201);



    }
}