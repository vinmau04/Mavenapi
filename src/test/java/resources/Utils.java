package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Utils {
	
	// Declaring s Global Variable 
	// need to Declare as 'public status' becoz when it runs each time( with different test data) variable 'reqspec' should NOT be REPACED as NULL
	public static RequestSpecification reqspec;
	
	
	
	public RequestSpecification requestSpecification() throws IOException {
		
		/// This condition is to avoid 'log.txt' to be overwritten when we run same test with mutliple test data. 
		if (reqspec==null)
		{
		
		//A Created the PrintStream OBJECT CLass & mentioned the New Log file
		PrintStream log=new PrintStream (new FileOutputStream("C:\\Users\\admin\\Mavenapi\\src\\test\\java\\resources\\log.txt"));
		
		
		//2 EXAMPLE of REQUEST SPEC BUILDER:
		System.out.println("//2 EXAMPLE of REQUEST SPEC BUILDER:");
		// Return Type will be "RequestSpecification"
		reqspec=new RequestSpecBuilder()
		
		// as part of C. golbal.properties files" method below we are not hardcoding the BaseURL but getting it from 'global.properties' file
		.setBaseUri(getGlobalValue("baseURL"))
		// as part of C. golbal.properties files" method below we are not hardcoding the Key value but getting it from 'global.properties' file
		.addQueryParam("key", getGlobalValue("key"))
		
		//B Calling the Print Stream Variable "log" for both Request & Response details
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON)
		.build();
		return reqspec;
		}
		// this will return 'reqspec' as NOT null & will just update test data from 2nd round.
		return reqspec;
	}
	
	//C . Method to Extract values from "golbal.properties files" as part driving from GOLBAL VARIABLES
	public String getGlobalValue(String Gkey) throws IOException {
		//Created the Properties OBJECT
		Properties prop=new Properties();
		
		//Created the FileInputStream OBJECT Class & provided the "global.properties" path
		FileInputStream fis= new FileInputStream("C:\\Users\\admin\\Mavenapi\\src\\test\\java\\resources\\global.properties");
		
		//"Properties prop" object Class which scans '.properties' files but not sure where is the file 
		//but "FileInputStream fis" object Class know where '.properties' files 
		//below Code will integrate both the Class
		prop.load(fis);
		
		//below Code we are telling "getGlobalValue(Gkey)"utility can pullup an value from .properties file & returning the same
		return prop.getProperty(Gkey);	
		
	}
	
	// This Method will pull out Response Json file & return the String Value
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	

}
