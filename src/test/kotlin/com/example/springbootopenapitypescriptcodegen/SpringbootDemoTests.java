package com.example.springbootopenapitypescriptcodegen;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ContextConfiguration(classes = SpringbootOpenapiTypescriptCodegenApplication.class)
@SpringBootTest
public class SpringbootDemoTests {

    private final static String BASE_URI = "http://localhost";


    private ValidatableResponse validatableResponse;

    private ValidatableResponse validatableResponse1;

    @Before
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = 8080;
    }

    /* Get operation - Get the details of a Student */
    @Test
    public void listUsers() {

        validatableResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students")
                .then()
                .assertThat().statusCode(200);

    }

    /* Get operation - Get the details of a Student */
    @Test
    public void listAUser() {

        validatableResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/30001")
                .then()
                .assertThat().log().all().statusCode(200)
                .body("id",equalTo(30001))
                .body("name",equalTo("David"))
                .body("passportNumber",equalTo("C1232268"));;
    }

    /* Create operation - Create a new Student */
    @Test
    public void createAUser() throws JSONException {

        JSONObject newStudent = new JSONObject();

        newStudent.put("id", "1");
        newStudent.put("firstName", "ZZZ12345");
        newStudent.put("familyName", "sdfsdf");

        validatableResponse = given()
                .contentType(ContentType.JSON).body(newStudent.toString())
                .when()
                .post("/api/person")
                .then()
                .log().all().assertThat().statusCode(2);

        /* Verify that a new Student is created */
        validatableResponse1 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/1")
                .then()
                .log().all().assertThat().statusCode(200)
                .body("id",equalTo(1))
                .body("firstName",equalTo("ZZZ12345"))
                .body("familyName",equalTo("sdfsdf"));

    }

    /* Update operation - Update PassportNumber of a Student */
    @Test
    public void updateAUser() throws JSONException {

        JSONObject newStudent = new JSONObject();

        newStudent.put("name", "John");
        newStudent.put("passportNumber", "YYYY1234");

        validatableResponse = given()
                .contentType(ContentType.JSON).body(newStudent.toString())
                .when()
                .put("/students/20001")
                .then()
                .log().all().assertThat().statusCode(204);

        /* Verify that the updated Student has updated PassportNumber */
        validatableResponse1 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/20001")
                .then()
                .log().all().assertThat().statusCode(200)
                .body("id",equalTo(20001))
                .body("name",equalTo("John"))
                .body("passportNumber",equalTo("YYYY1234"));

    }

    /* Delete operation - Delete a Student */
    @Test
    public void deleteAUser() throws JSONException {

        validatableResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/students/10003")
                .then()
                .log().all().assertThat().statusCode(200);


        /* Verify that the deleted Student Request returns STATUS 404 */
        validatableResponse1 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/10003")
                .then()
                .log().all().assertThat().statusCode(404);

    }
}