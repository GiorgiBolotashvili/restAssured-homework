import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

public class restAssuredBasics {

    @Test(dataProvider = "idAndCountry")
    public void firstTask(int id, String country) {

        Response response =
                given().
                        when().
                        get("http://ergast.com/api/f1/2017/circuits.json").
                        then().
                        extract().
                        response();

        System.out.println(response.jsonPath().getString("MRData.CircuitTable.Circuits[" + id + "]"));

        Assert.assertEquals(response.jsonPath().getString("MRData.CircuitTable.Circuits[" + id + "].Location.country"), country);

    }

    @DataProvider(name = "idAndCountry")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {1, "USA"},
                        {5, "Hungary"}
                };
    }
}
