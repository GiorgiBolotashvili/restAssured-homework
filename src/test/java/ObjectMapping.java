import ObjectClass.Register;
import ObjectClass.Successful;
import ObjectClass.UnSuccessful;
import ObjectClass.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ObjectMapping extends BasicClass{

    @Test(priority = 1)
    public void isSuccessful() throws JsonProcessingException {
        Register register = new Register();
        register.setEmail("eve.holt@reqres.in");
        register.setPassword("pistol");

        ObjectMapper objectMapper = new ObjectMapper();
        String registerString = objectMapper.writeValueAsString(register);

        //Implement SUCCESSFUL scenario and check status code 200
        responseSuccessful =  given()
                .header("Content-Type", "application/json")
                .body(registerString)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("First scenarios status code is: " + responseSuccessful.statusCode());

        //Validate error, id and token values
        validateScenarios(responseSuccessful);
    }
    @Test(priority = 2)
    public void isUnsuccessful() throws JsonProcessingException {
        Register register = new Register();
        register.setEmail("sydney@fife");

        ObjectMapper objectMapper = new ObjectMapper();
        String registerString = objectMapper.writeValueAsString(register);

        //Implement UNSUCCESSFUL scenario and check status code 400
        responseUnSuccessful =  given()
                .header("Content-Type", "application/json")
                .body(registerString)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .extract()
                .response();

        System.out.println("Second scenarios status code is: " + responseUnSuccessful.statusCode());

        //Validate error, id and token values
        validateScenarios(responseUnSuccessful);
    }

    @Test(priority = 3)
    public void createUser() throws JsonProcessingException {
        User newUser = new User();
        newUser.setName("morpheus");
        newUser.setJob("leader");

        ObjectMapper objectMapper = new ObjectMapper();
        String newUserString = objectMapper.writeValueAsString(newUser);

            given()
                    .header("Content-Type", "application/json")
                    .body(newUserString)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .log().body();
    }

    public void validateScenarios(Response response){

        ResponseBody body = response.getBody();
        if(response.statusCode() == 200){
            Successful success = body.as(Successful.class);
            Assert.assertEquals(success.getId(), 4);
            Assert.assertEquals(success.getToken(), "QpwL5tke4Pnpja7X4");
        }
        else if(response. statusCode() == 400){
            UnSuccessful unsuccess = body.as(UnSuccessful.class);
            Assert.assertEquals(unsuccess.getError(),"Missing password" );
        }
    }
}
