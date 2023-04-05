package common_method;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class put_common_method_api {
	public static int responseStatusCode_Extractor(String baseURI, String resource, String req_body)
		{
			RestAssured.baseURI=baseURI;
			int res_StatusCode=given().header("Content-Type", "application/json").body(req_body).when().put(resource).then().extract().statusCode();
			return res_StatusCode;
		}
	public static String responsebody_Extractor(String baseURI, String resource, String req_body)
	{
		RestAssured.baseURI=baseURI;
		String res_body=given().header("Content-Type", "application/json").body(req_body).when().put(resource).then().extract().response().asString();
		return res_body;
	}

}
