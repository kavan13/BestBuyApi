package com.bestbuy.bestbuytest;

import com.bestbuy.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ProductTest extends TestBase {
    @Test
    public void getAllProducts() {
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleProduct() {
        Response response = given()
                .basePath("/products")
                .pathParam("id", 43900)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void searchProductWithLimit() {

        Response response = given()
                .queryParam("$limit", 2)
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createProductwithPost() {
        ProductPojo productPojo = new ProductPojo();


        productPojo.setName("Sony - AAA Batteries (4-Pack)");
        productPojo.setType("SoftGood");
        productPojo.setPrice(4.49);
        productPojo.setUpc("1234567");
        productPojo.setShipping(0);
        productPojo.setDescription("Compatible with select electronic devices");
        productPojo.setManufacturer("Sony");
        productPojo.setModel("MN1234");


        Response response = given()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post("/products");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void updateProductWithPut() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("panasonic");
        productPojo.setType("Good");
        productPojo.setPrice(7.49);
        productPojo.setUpc("1234567");
        productPojo.setShipping(1);
        productPojo.setDescription("Battery");
        productPojo.setManufacturer("Sony");
        productPojo.setModel("mn12345");

        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .pathParam("id", 346575)
                .body(productPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updateProductWithPatch() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("CheapBattery");

        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .patch("/309062");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteProduct() {
      Response   response = given()
                .pathParam("id",185267)
                .header("Content-Type","application/json")
                .when()
                .delete("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }
}
