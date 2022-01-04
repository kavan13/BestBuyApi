package com.bestbuy.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJasonPath {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
//    1. Extract the limit
@Test
public void test001() {
    int limit = response.extract().path("limit");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of limit is : " +limit);
    System.out.println("------------------End of Test---------------------------");

}
//2. Extract the total
@Test
public void test002() {

    int total = response.extract().path("total");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Total number is: " + total);
    System.out.println("------------------End of Test---------------------------");
}
//3. Extract the name of 5th store
@Test
public void test003(){

    String  name=response.extract().path("data[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Store name is: "+name);
    System.out.println("------------------End of Test---------------------------");

}
//4. Extract the names of all the store
@Test
public void test004() {

    List<String> name = response.extract().path("data.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Stores name is: " + name);
    System.out.println("------------------End of Test---------------------------");
}
//5. Extract the storeId of all the store
@Test
public void test005() {

    List<Integer> idList= response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : " +idList);
    System.out.println("------------------End of Test---------------------------");

}
//6. Print the size of the data list
@Test
public void test006() {

    int size = response.extract().path("data.size");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Total size are: " + size);
    System.out.println("------------------End of Test---------------------------");

}
//7. Get all the value of the store where store name = St Cloud
@Test
public void test007() {
    List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name=='St Cloud'}");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Stores value are: " + value);
    System.out.println("------------------End of Test---------------------------");
}
//8. Get the address of the store where store name = Rochester
@Test
public void test008() {
    List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store address are: " + address);
    System.out.println("------------------End of Test---------------------------");
}

//9. Get all the services of 8th store
@Test
public void test009() {
//    List<HashMap<String, Object>> services = response.extract().path("data[7].services");
//    System.out.println("------------------StartingTest---------------------------");
//    System.out.println("The names of the services are: " + services);
//    System.out.println("------------------End of Test---------------------------");

    List<HashMap<String, Object>> services = response.extract().path("data[7].services");
    System.out.println("Services of 8th stores are : ");
    for (HashMap<String, Object> service : services) {
        System.out.println(service);
    }
}
//10. Get storeservices of the store where service name = Windows Store
@Test
public void test0010() {
   // List<String> services = response.extract().path("data.findAll{it.name=='Windows Store'}.storeservices");

   List<HashMap<String, Object>> services = response.extract().path("//data.services[7].storeservices");//data.services[7].storeservices
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Storeservices created are: " + services);
    System.out.println("------------------End of Test---------------------------");
}


//11. Get all the storeId of all the store
@Test
public void test0011() {

    List<HashMap<String, Object>> storeid = response.extract().path("data.services.storeservices.storeId");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store id is: " + storeid);
    System.out.println("------------------End of Test---------------------------");
}
//12. Get id of all the store
@Test
public void test0012() {

    List<Integer> idList= response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : " +idList);
    System.out.println("------------------End of Test---------------------------");

}

//13. Find the store names Where state = ND
@Test
public void test0013() {
    List<String> state = response.extract().path("data.findAll{it.state=='ND'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : " +state);
    System.out.println("------------------End of Test---------------------------");

}
//14. Find the Total number of services for the store where store name = Rochester
@Test
public void test0014() {
    int total = response.extract().path("data[8].services.size");
    List<String> services = response.extract().path("data.findAll{it.name=='Rochester'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of services are : " +services);
    System.out.println("List of Total services are : " +total);
    System.out.println("------------------End of Test---------------------------");

}

//15. Find the createdAt for all services whose name = “Windows Store”
@Test
public void test15() {
   // List<String> created =response.extract().path("data.findAll{it.services.name=='Windows Store'}.services.createdAt");

  List<HashMap<String, Object>> created = response.extract().path("data.services[7].createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The createdAt services are: " + created);
    System.out.println("------------------End of Test---------------------------");
}
//16. Find the name of all services Where store name = “Fargo”
@Test
public void test016(){
    List<String> services =response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store name are: " + services);
    System.out.println("------------------End of Test---------------------------");
}
//17. Find the zip of all the store
@Test
public void test17() {
    List<Integer> zip=response.extract().path("data.zip");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Zip list are: " + zip);
    System.out.println("------------------End of Test---------------------------");
}

//18. Find the zip of store name = Roseville
@Test
public void test18() {

    List<Integer> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Zip for the store are: " + zip);
    System.out.println("------------------End of Test---------------------------");
}
//19. Find the storeservices details of the service name = Magnolia Home Theater
@Test
public void test19() {
   // List<Integer> services = response.extract().path("data.findAll{it.name=='Magnolia Design Center'}.storeservices");

   List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("services detail are: " + services);
    System.out.println("------------------End of Test---------------------------");
}
//20. Find the lat of all the stores
@Test
public void test20() {
    List<Double> lat=response.extract().path("data.lat");

 //   List<HashMap<String, Object>> lat = response.extract().path("data.lat");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store lat are: " + lat);
    System.out.println("------------------End of Test---------------------------");
}
}
