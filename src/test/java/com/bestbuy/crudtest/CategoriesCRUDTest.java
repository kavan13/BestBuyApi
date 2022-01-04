package com.bestbuy.crudtest;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CategoriesCRUDTest extends TestBase {
    static String name = "Electronic" + TestUtils.getRandomName();
    static String id = TestUtils.getRandomName();
    static String categoryId;

    @Test
    public void test001() {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);

        Response response = given()
                .basePath("/categories")
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
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
        categoryId = (String) value.get("id");

    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.id='";
        String p2 = "'}.get(0)";

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setId(id);
        categoriesPojo.setName(name);

        Response response = given()
                .basePath("/categories")

                .pathParam("cId",id)
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .put("/{cId}");
        response.then().statusCode(200);
        response.prettyPrint();

        HashMap<String, Object> value =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + id + p2);
        System.out.println(value);
    }

    @Test
    public void test004(){

        Response response = given()
                .pathParam("cId",categoryId)
                .when()
                .delete("/{cId}");
        response.then().statusCode(200);
        response.prettyPrint();

        Response response1 = given()
                .pathParam("cId", categoryId)
                .when()
                .get("/{cId}");
        response1.then().statusCode(404);
    }


}
