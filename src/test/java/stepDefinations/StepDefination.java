package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


//Inheritance concept  is used becoz Utils will be used extensively in Step Defination file hence "extends Utils ('Import Utils'(resources))" is used
public class StepDefination extends Utils{
	
	// Declaring the Add Place Payload from "resources/TestDataBuild.Java" file
	TestDataBuild data=new TestDataBuild();
	
	// Declaring s Global Variable
	RequestSpecification res;
	Response response;
	static String place_id;
	
	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_place_payload(String name, String language, String address) throws IOException  {
				//4- GIVEN - Assign the REQUEST SPEC BUILDER Variable "reqspec" under "given" as .spec(reqspec)
				//created "res" variable  to pull out 'given' values
				//Moved "RequestSpecification res" to TOP as GLOBAL variable
				//under spec (requestSpecification()) function is called from Utils file
				res=given().spec(requestSpecification())
				// Calling the "data" variable from above to call "addPlacePaload" function from "TestDataBuild.Java" file	
				//We are passing variable 'name,language, address' from '.feature file' to 'addPayload'
				.body(data.addPlacePayload(name, language, address));
    }

	@When("^User Calls \"([^\"]*)\" with \"([^\"]*)\" Http request$")
    public void user_calls_something_with_something_http_request(String resource, String method)  {
    			//calling 'resource' variable in below Object, 
				//will get what value is called in 'When' method in .feature file( e.g addPlaceAPI) will be fetched
				//'APIResources.valueOf(addPlaceAPI)'will be passed to Ennum function 'APIResources.java' file
				// comment is continued in 'APIResources.java' file
				APIResources resourceAPI=APIResources.valueOf(resource);
				//This code will get PATH requested in GIVEN under .feature file. 
				resourceAPI.getResource();
    			//5- WHEN - Assign given(res) value to when
    			//Created 'response' variable to pull out 'when' values
    			//Moved "Response response" to TOP as GLOBAL variable
				System.out.println((resourceAPI.getResource()));
				//'resourceAPI.getResource();' is passed as 'Post' method from variable 'method' from '.feature' file
				if (method.equalsIgnoreCase("Post")) {
					response =res.when().post(resourceAPI.getResource());
				}
				//'resourceAPI.getResource();' is passed as 'Get' method from variable 'method' from '.feature' file
				else if (method.equalsIgnoreCase("Get")) {
					response =res.when().get(resourceAPI.getResource());
				}
					
    }

    @Then("^The API call got suscess with Status Code 200$")
    public void the_api_call_got_suscess_with_status_code_200()  
    {
       assertEquals(response.getStatusCode(),200);
    }
    
    //Regular Expression with SMART STEP DEFINITION  
    @And("^\"([^\"]*)\" in response Body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String KeyValue, String ExpectedValue)
    {
    	// with Assertion "assertEquals" will get "KeyValue=status/scope" & "ExpectedValue will give OK & APP" its converted to String & compared.
    	assertEquals (getJsonPath(response,KeyValue),ExpectedValue);
    }
    
    
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException 
    {	
    	 // Below Code will get the Place_Id from Json
    	 place_id =getJsonPath(response,"place_id");
    	 //Below code we are concatenating 'Spec requestSpecification ie Base URL,Key along with Place_id
		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		 //Calling the when method function to pull data from last 'Then' step from .Feature file along with '<name>' & 'getPlaceAPI' value via 'expectedname' & 'resource' variable.
		 user_calls_something_with_something_http_request(resource,"GET");
		 //pulling out Name from Get Response
		 String actualName=getJsonPath(response,"name");
		 //Comparing Name pulled from Get Response 'actualName' with 'expectedName' from .feature file
		 assertEquals(actualName,expectedName);
    }
    
    @Given("Delete Place Payload")
    public void deleteplace_Payload() throws IOException {
    {
        // Write code here that turns the phrase above into concrete actions
    	res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
}