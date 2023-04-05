package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;

import common_method.Patch_Common_Method_Utilities;
import common_method.Patch_Common_Method_api;
import io.restassured.path.json.JsonPath;
import request_repository.Patch_Request_Repository;

public class patch_tc1 {
	
	public static void orchestrator() throws IOException
	{
		String responseBody="";
	    int res_StatusCode=0;
	    String baseURI=Patch_Request_Repository.baseuri();
	    String resource=Patch_Request_Repository.resource();
	    String requestBody=Patch_Request_Repository.Patch_request_tc1();
	    for (int i=0; i<5; i++)
	    {
	    	res_StatusCode=Patch_Common_Method_api.response_StatusCode_Extractor(baseURI, resource, requestBody);
	        if(res_StatusCode==200)	
	        {
	    	responseBody=Patch_Common_Method_api.responseBody_Extractor(baseURI, resource, requestBody);
	    	response_Body_Validator(responseBody);
	    	
	    	break;
	        }
	        else
	        {
	        	System.out.println("correct status code is not found in itenration" + i);
	        	
            }
        }
	    Patch_Common_Method_Utilities.EvidenceFileCreator("patch_Tc1", requestBody, responseBody);
	    Assert.assertEquals(res_StatusCode,200);
	    			
	}
	public static void response_Body_Validator(String responseBody)
	{
		//create jsonPath object to extract responseBody parameters
		JsonPath jsp = new JsonPath(responseBody);
		//extract response_body parameters;
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
	
		String res_updatedAt = jsp.getString("updatedAt");
		//System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " +  "\nupdatedAt : " + res_updatedAt);
		
		//validate response body parameter
		      Assert.assertEquals(res_name, "morpheus");
		      Assert.assertEquals(res_job,"zion resident");
		      
		      
		      //extract data from createdAt parameter
		      String actual_date = res_updatedAt.substring(0,10);
		      String current_date = LocalDate.now().toString(); // create a date object
		      Assert.assertEquals(actual_date,current_date);
		     // System.out.println("Actual DATE :" + actual_date + "\nCURRENT DATE : " + current_date);
		      
		      
	}
	    


}