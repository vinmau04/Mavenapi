package resources;

//enum is special class in java which has collection of constants or  methods
public enum APIResources {
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	//We have declare a 'Constructor'
	//..continued now 'recourses' variable stores 'addPlaceAPI' path is passed in below function 
	// we need to return 'recourses' value hence below function is coded & it will 'return' the value
	// scope of 'recourses' is LOCAL hence we need GLOBAL hence we have delcleared belowstep
	private String resource;
	
	
	APIResources(String resource)
	{ 
		// this line will assign value coming from above constructor variable 'resource'	to current Class GLOBAL variable 'recourses' 
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
