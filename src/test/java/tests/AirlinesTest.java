package tests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class AirlinesTest {

    @Test
    public void getFlight(){
        String respuesta =
        given()

            .header("Content-Type", "application/json")
        .when()
            .get("http://localhost:5000/api/flights/3")
        .then()
            .statusCode(200)
            .body("origin", equalTo("Istanbul"))
            .extract().body().asString();
        System.out.println(respuesta);

    }

    @Test
    public void crearReserva(){
        Integer respuesta =
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\t\"flight_id\":\"4\",\n" +
                        "\t\"name\":\"rodrige\"\n" +
                        "}")
        .when()
                .post("http://localhost:5000/api/reservation/new")
        .then()
                .statusCode(200)
                .extract().path("reservation_id");

        System.out.println(respuesta);
    }
}
