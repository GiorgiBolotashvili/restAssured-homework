import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BasicClass {
    Response responseSuccessful;
    Response responseUnSuccessful;

    public BasicClass(){
        RestAssured.baseURI = "https://reqres.in/api";
    }
}
