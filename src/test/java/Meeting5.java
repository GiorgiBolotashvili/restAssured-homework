import Models.Requests.Register;
import Models.Responses.ResponseUser;
import Models.Requests.RequestUser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Meeting5 extends BasicClass{

    @Test(priority = 1)
    public void isSuccessful(){
        Register register = new Register("eve.holt@reqres.in", "pistol" );

        //Implement SUCCESSFUL scenario and check status code 200
        response =  given()
                .header("Content-Type", "application/json")
                .body(register)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("First scenarios status code is: " + response.statusCode());

        //Validate error, id and token values
        steps.validateScenarios(response);
    }
    @Test(priority = 2)
    public void isUnsuccessful(){
        Register register = new Register("sydney@fife");

        //Implement UNSUCCESSFUL scenario and check status code 400
        response =  given()
                .header("Content-Type", "application/json")
                .body(register)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .extract()
                .response();

        System.out.println("Second scenarios status code is: " + response.statusCode());

        //Validate error, id and token values
        steps.validateScenarios(response);
    }

    @Test(priority = 3)
    public void createUser(){
        RequestUser newUser = new RequestUser("morpheus", "leader");

        response = given()
                    .header("Content-Type", "application/json")
                    .body(newUser)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(201)
                    .extract()
                    .response();

        ResponseUser user = response.as(ResponseUser.class);

        System.out.println(user.toString());
    }
}
