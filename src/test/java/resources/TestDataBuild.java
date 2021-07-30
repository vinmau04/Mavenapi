package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		
		// 1- Adding values to POJO Class  for SERIALIZE EXAMPLE
		System.out.println("// 1- Adding values to POJO Class  for SERIALIZE EXAMPLE");
		//Creating  OBJECT  for Add Place class so that we can access the Setters methods. Import 'AddPlace (pojo)'
		AddPlace place = new AddPlace();
		
		//Adding the Values to the direct Setters in AddPlace Java Class
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("http://google.com");
		
		//Types is expecting LIST OBJECT cannot pass STRING, hence we have to create LIST. 
		//Creating Array LIST OBJECT and assigning to "myList" variable, Import (Arraylist(javautil) & import java.util.List
		List<String>myList=new ArrayList<String>();
		//Adding the Values to the Array List
		myList.add("shoe park");
		myList.add("shop");
		//Below we are passing SET of STRING from ARRAY LIST via variable "myLIST" to "setTypes"
		place.setTypes(myList);
		
		// Location  is expecting another Class Object. 
		//We need to Create LOCATION CLASS OBJECT for other SUB JSON "Location.java" . Import 'Location(pojo)
		Location loc=new Location();
		//Now we are assigning value by calling variable 'loc' setter in Location Java Class.
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		//Below we are passing value via variable "loc" to "setLocation" 
		place.setLocation(loc);
		return place;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
