package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenrio() throws IOException
	{
		//execute this code only when place id is null
		//write a code that will give you place id
		// Declaring the 'StepDefination' from 'StepDefination.java' and created a Object 'beforeSD' 
		StepDefination beforeSD=new StepDefination();
		
		//Since 'place_id' Variable in 'StepDefination.java' is declared as "STATIC"
		//We have to call using Class name i,e 'StepDefination.place_id' instead of object name 'beforeSD'
		// If Place ID is null then only below code will be executed.
		if (StepDefination.place_id==null) 
		{
		//Calling GIVEN function from 'StepDefination.jave' by calling object name 'beforeSD'
		beforeSD.add_place_payload("Lokesh", "Hindi", "Willams Town");
		//Calling WHEN function from 'StepDefination.jave' by calling object name 'beforeSD'
		beforeSD.user_calls_something_with_something_http_request("addPlaceAPI", "post");
		//Calling THEN (last one in the below) function from 'StepDefination.jave' by calling object name 'beforeSD'
		beforeSD.verify_place_id_created_maps_to_using("Lokesh", "getPlaceAPI");
		}
	}

}
