import Steps.ValidateScenarios;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BasicClass {
    Response response;
    ValidateScenarios steps;


    public BasicClass(){
        steps = new ValidateScenarios();
        RestAssured.baseURI = "https://reqres.in/api";
    }
}
