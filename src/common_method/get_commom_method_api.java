package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class get_commom_method_api {
	
	public static int responseStatusCode_Extractor(String baseURI, String resource)
	{
		RestAssured.baseURI=baseURI;
		int res_StatusCode=given().when().get(resource).then().extract().statusCode();
		return res_StatusCode;
	}
	public static String responseBody_Extractor(String baseURI, String resource)
	{
		RestAssured.baseURI=baseURI;
		String responseBody=given().when().get(resource).then().extract().response().asString();
		return responseBody;
	}

}
