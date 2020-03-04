package tests;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class AirlinesTest {

    private String url ="http://localhost:5000/api";

    @Test
    public void getFlight(){
        String respuesta =
        given()

            .header("Content-Type", "application/json")
        .when()
            .get(url +"/flights/3")
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
                .post(url + "/reservation/new")
        .then()
                .statusCode(200)
                .extract().path("reservation_id");
        System.out.println(respuesta);
    }

    @Test
    public void consultaReservaExistente(){
        String respuesta =
                given()
                        .header("Content-Type", "application/json")

                .when()
                        .get(url +"/reservations/1")
                .then()
                        .statusCode(200)
                        .body("passenger", equalTo("carlos"))
                        .extract().body().asString();
        System.out.println(respuesta);
    }

    @Test
    public void crearUnVuelo(){
        String respuesta =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "\t\"origin\":\"buenos aires\",\n" +
                                "\t\"destination\":\"Marruecos\",\n" +
                                "\t\"duration\":\"222\"\n" +
                                "}")
                .when()
                        .post(url +"/flight/new")
                .then()
                        .statusCode(200)
                        .body("destination", equalTo("Marruecos"))
                        .extract().body().asString();
        System.out.println(respuesta);
    }

    @Test
    public void consultaReservaInexistente(){
        String respuesta =
                given()
                        .header("Content-Type", "application/json")
                .when()
                        .get(url +"/reservations/7785")
                .then()
                        .statusCode(422)
                        .extract().body().asString();
        System.out.println(respuesta);
    }

    @Test
    public void consultaUnaReservaValida(){ //consultar por como ver una lista
        String respuesta =
                given()
                        .header("Content-Type", "application/json")
                .when()
                        .get(url +"/flights/3")
                .then()
                        .statusCode(200)
                        //.body( get List<passengers> pax, equalTo("lucas"))
                        .extract().body().asString();
        System.out.println(respuesta);
    }

}
