package Steps;

import Models.Responses.Successful;
import Models.Responses.UnSuccessful;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;

public class ValidateScenarios {

    public void validateScenarios(Response response){

        ResponseBody body = response.getBody();
        if(response.statusCode() == 200){
            Successful success = body.as(Successful.class);
            Assert.assertEquals(success.id, 4);
            Assert.assertEquals(success.token, "QpwL5tke4Pnpja7X4");
        }
        else if(response. statusCode() == 400){
            UnSuccessful unsuccess = body.as(UnSuccessful.class);
            Assert.assertEquals(unsuccess.error,"Missing password" );
        }
    }
}
