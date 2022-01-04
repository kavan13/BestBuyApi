package com.bestbuy.bestbuytest;

import com.bestbuy.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesInfo extends TestBase {
    @Test
    public void getAllServices() {
        Response response = given()
                .when()
                .get("/services");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleService() {
        Response response = given()
                .basePath("/services")
                .pathParam("id", "6")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void searchCategoriesWithLimit() {
        Response response = given()
                .queryParam("$limit", 2)
                .when()
                .get("/services");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createNewServicesWithPost() {
        ServicesPojo servicesPojo = new ServicesPojo();

        servicesPojo.setName("Electronic Services");


        Response response = given()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post("/services");
        response.then().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void updateServicesWithPatch() {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("Electronic Store");
        Response response = given()
                .basePath("/services")
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .patch("/15");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updateServicesWithPut() {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("Speaker Store");
        Response response = given()
                .basePath("/services")
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .put("/4");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteServices() {
        Response response = given()
                .when()
                .delete("/13");
        response.then().statusCode(404);
        response.prettyPrint();
    }
}