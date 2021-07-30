Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if Place is being Sucssesfully added using AddPlaceAPI
  Given Add Place Payload with "<name>" "<language>" "<address>"
  When User Calls "addPlaceAPI" with "Post" Http request
  Then The API call got suscess with Status Code 200
  And "status" in response Body is "OK"
  And "scope" in response Body is "APP"
  Then verify place_Id created maps to "<name>" using "getPlaceAPI" 
  
Examples:
	|name 	 | language |address		   |
	|Deepak  | Tanil    |BTM			   |
	|Vinu    | Kanada   |Maleshpalaya	   |
	|Maurya  | English  |Singapore		   |
	|Abhi    | Tamil    |Domlur			   |
	
	
@DeletePlace @Regression	
Scenario: Verify if Delete API fucntionality is working as expected
  Given Delete Place Payload
  When User Calls "deletePlaceAPI" with "Post" Http request
  Then The API call got suscess with Status Code 200
  And "status" in response Body is "OK"