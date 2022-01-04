package com.bestbuy.crudtest;

import com.bestbuy.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ProductsCrudTest extends TestBase {
    static String name = "Sony TV " + TestUtils.getRandomName();
    static String type = "Gadgets" + TestUtils.getRandomName();
    static Double price = 555.99;
    static Integer shipping = 5;
    static String upc = "039393943943";
    static String description = "Electronics Gadgets under one roof " + TestUtils.getRandomValue();
    static String manufacturer = "Sony";
    static String model = TestUtils.getRandomName();
    static int productId;

    @Test
    public void test001() {
        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productsPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void test002() {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);

        productId = (int) value.get("id");


    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.id='";
        String p2 = "'}.get(0)";

        name = name + "_updated";
        type = type + "_updated";

        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);

        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .pathParam("pId", productId)
                .body(productsPojo)
                .when()
                .put("/{pId}");
        response.then().log().all().statusCode(200);

        HashMap<String, Object> value =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + productId + p2);
        System.out.println(value);
    }

    @Test
    public void test004() {

        Response response = given()
                .pathParam("pId", productId)
                .when()
                .delete("/{pId}");
        response.then().statusCode(200);
        response.prettyPrint();

        Response response1 = given()
                .pathParam("pId", productId)
                .when()
                .get("/{pId}");
        response1.then().statusCode(404);
        response1.prettyPrint();
    }

}
