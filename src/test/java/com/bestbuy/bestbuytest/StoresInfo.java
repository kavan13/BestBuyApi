package com.bestbuy.bestbuytest;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresInfo extends TestBase {
    @Test
    public void getAllStores() {
        Response response = given()
                .when()
                .get("/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleStore() {
        Response response = given()
                .basePath("/stores")
                .pathParam("id", 12)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void searchStoresWithLimit() {
        Response response = given()
                .queryParam("$limit", 2)
                .when()
                .get("/categories");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createNewStoresWithPost() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("abcd");
        storesPojo.setType("SmallBox");
        storesPojo.setAddress("14 road");
        storesPojo.setAddress2("Harrow");
        storesPojo.setCity("London");
        storesPojo.setState("Uk");
        storesPojo.setZip("ha3 3eo");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .post("/stores");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void updateStoresWithPut() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("lkjhg");
        storesPojo.setType("SmallBox");
        storesPojo.setAddress("54 road");
        storesPojo.setAddress2("Wembley");
        storesPojo.setCity("London");
        storesPojo.setState("Uk");
        storesPojo.setZip("ha9 3eo");

        Response response = given()
                .basePath("/stores")
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .put("/16");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updateStoresWithPatch() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("None and Single");
        Response response = given()
                .basePath("/stores")
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .patch("/18");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteStores() {
        Response response = given()
                .basePath("/stores")
                .when()
                .delete("/9");
        response.then().statusCode(404);
        response.prettyPrint();

    }
}
