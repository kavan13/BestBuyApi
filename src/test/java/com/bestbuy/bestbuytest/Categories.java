package com.bestbuy.bestbuytest;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Categories extends TestBase {
    @Test
    public void getAllCategories() {
        Response response =
                given()
                        .when()
                        .get("/categories");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleCategories() {
        Response response = given()
                .basePath("/categories")
                .pathParam("id", "abcat0100000")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void searchCategoriesWithParameter() {
        Response response = given()
                .queryParam("name", "DVD Games")
                .queryParam("$limit", 2)
                .when()
                .get("/categories");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createNewCategoriesWithPost() {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setId("abcde0203");
        categoriesPojo.setName("Theater Systems");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post("/categories");
        response.then().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void createNewCategoriesWithPut() {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
         categoriesPojo.setId("abcat06758");
        categoriesPojo.setName("Theater TV With Speaker");

        Response response = given()
                .basePath("/categories")
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .put("/abcat0107000");
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void updateCategoriesWithPatch() {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName("Sony Speaker");
        Response response = given()
                .basePath("/categories")
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .patch("/abcat0010000");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteCategories() {
        Response response = given()
                .when()
                .delete("/pcmcat138100050028");
        response.then().statusCode(404);
        response.prettyPrint();

    }
}
