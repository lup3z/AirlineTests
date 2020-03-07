package tests;
import Pojo.Reserva;
import Pojo.ReservaInfo;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

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
        /*Map <String, Object> cuerpo = new HashMap  <String, Object>();
        cuerpo.put("flight_id", 8);
        cuerpo.put("name","Marcos");*/

        ReservaInfo respuesta =
        given()
                .header("Content-Type", "application/json")
                .body(new Reserva(8, "carlos"))
        .when()
                .post(url + "/reservation/new")
        .then()
                .statusCode(200)
                .extract().body().as(ReservaInfo.class);
        System.out.println(respuesta);
    }

    @Test
    public void consultaReservaExistente(){
        String respuesta =
                given()
                        .header("Content-Type", "application/json")

                .when()
                        .get(url +"/reservations/7")
                .then()
                        .statusCode(200)
                        .body("passenger", equalTo("Omar"))
                        .extract().body().asString();
        System.out.println(respuesta);
    }

    @Test
    public void crearUnVuelo(){
        Map <String, Object> cuerpo = new HashMap  <String, Object>();
        cuerpo.put("origin", "Buenos Aires");
        cuerpo.put("destination","Marruecos");
        cuerpo.put("duration",153);
        String respuesta =
                given()
                        .header("Content-Type", "application/json")
                        .body(cuerpo)
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
                        .body("error", equalTo("Invalid reservation_id"))
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
                        .body( "passengers", hasItems("carlos", "lucas"))
                        .extract().body().asString();
        System.out.println(respuesta);
    }

}
