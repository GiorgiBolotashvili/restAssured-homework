import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class restAssuredTask2 {

    @Test
    public void ValidateLastRecord(){
        given().
                when().
                get("https://chercher.tech/sample/api/product/read").
                then().
                body("records.name[-1]",equalTo("CreateRecord")).
                body("records.created", everyItem(lessThan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))))).
                log().body();
    }
    @Test
    public void postNewUser(){
        JSONObject request = new JSONObject();
        request.put("first_name", "John");
        request.put("last_name","Smith");

        given().
                header("Content Type", "application/json").
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).log().body();
    }
}

