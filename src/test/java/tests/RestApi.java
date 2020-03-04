package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestApi {
    private String url="http://localhost:80/";
    @Test
    public void crearUnProyecto(){
        String respuesta =
                given()
                        .header("Content-Type", "application/xml")
                        .auth().basic("admin","admin")
                        .body("<project>\n" +
                                "\t<name>marcosss</name>\n" +
                                "</project>")
                .when()
                        .post(url +"projects.xml")
                .then()
                        .statusCode(201)
                        .body("<name>", equalTo("marcosss"))
                        .extract().body().asString();
        System.out.println(respuesta);
    }
}
