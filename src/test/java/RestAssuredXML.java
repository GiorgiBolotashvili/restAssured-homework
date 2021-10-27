import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.core.IsEqual.equalTo;


public class RestAssuredXML {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName";
    }

    @Test(priority = 1)
    public void checkSize(){

       given()
            .accept(ContentType.XML)
            .when()
            .get()
            .then()
            .assertThat()
            .body("ArrayOftContinent.tContinent.sName.size()", equalTo(6));
    }

    @Test(priority = 2)
    public void checkAllSName(){

        NodeChildrenImpl names = given()
                                    .contentType("application/xml")
                                    .when()
                                    .get()
                                    .then()
                                    .extract()
                                    .path("ArrayOftContinent.tContinent.sName");

        System.out.println(names.list());
    }

    @Test(priority = 3)
    public void checkSCode(){

        given()
                .accept(ContentType.XML)
                .when()
                .get()
                .then()
                .assertThat()
                .body("ArrayOftContinent.tContinent.find{it.sCode == 'AN'}.sName", equalTo("Antarctica"));
    }

    @Test(priority = 4)
    public void checkLastSName(){
        given()
                .accept(ContentType.XML)
                .when()
                .get()
                .then()
                .assertThat()
                .body("ArrayOftContinent.tContinent[-1].sName", equalTo("The Americas"));
    }
}
