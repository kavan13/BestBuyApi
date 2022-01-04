package com.bestbuy.crudtest;

import com.bestbuy.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ServicesCrudTest extends TestBase {
    static String name = "Electronics" + TestUtils.getRandomName();
    static Integer serviceId;

    @Test
    public void test001() {
        ServicesPojo servicePojo = new ServicesPojo();
        servicePojo.setName(name);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(servicePojo)
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
        serviceId = (Integer) value.get("id");

    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.id='";
        String p2 = "'}.get(0)";

        name = name + "_updated";
        ServicesPojo servicePojo = new ServicesPojo();
        servicePojo.setName(name);

        Response response = given()
                .basePath("/services")
                .header("Content-Type", "application/json")
                .pathParam("sId", serviceId)
                .body(servicePojo)
                .when()
                .put("/{sId}");
        response.then().log().all().statusCode(200);

        HashMap<String, Object> value =

                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + serviceId + p2);
        System.out.println(value);
    }

    @Test
    public void test004() {
        Response response = given()
                        .pathParam("sId", serviceId)
                        .when()
                        .delete("/{sId}");
        response.then().statusCode(200);
        response.prettyPrint();

        Response response1 = given()
                        .pathParam("sId", serviceId)
                        .when()
                        .get("/{sId}");
        response1
                .then()
                .statusCode(404);
    }

}
