package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.Restclient;
import com.qa.data.Users;

public class postApiTest extends TestBase {
	
	public TestBase testbase=null;
	String serviceurl=null;
	String requlr=null;
	String url=null;
	Restclient restClient=null;
	CloseableHttpResponse closablehttpresponse=null;
	
	@BeforeMethod
	public void setup() throws IOException{
		testbase=new TestBase();
		 serviceurl=prop.getProperty("url");
		 requlr=prop.getProperty("posturl");
		 url=serviceurl+requlr;
		
	}
	
	@Test(priority=1)
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient=new Restclient();
		HashMap<String, String> headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson api ....
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("morpheus", "leader");
		
		//object to json file 
		mapper.writeValue(new File("C:\\Users\\Abdul Rajik shaik\\git\\gitrepo\\postman\\src\\main\\java\\com\\qa\\data\\Users.json"), users);
		
		//objec to json in string 
		String userJsonString =mapper.writeValueAsString(users);
		System.out.println("the user json string is  :"+userJsonString);
		
		closablehttpresponse=restClient.post(url, userJsonString, headerMap);
		
		//1. get status code
		int statusCode=closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("the status code is  :"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,"status code is not 200");
		
		//2. json string 
		String responseString= EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("the response is  "+responseJson);
		
		Users usersResObj= mapper.readValue(responseString, Users.class);
		
		
		System.out.println(users.getName().equals(usersResObj.getName()));
		System.out.println(users.getJob().equals(usersResObj.getJob()));
		System.out.println("the user id is : "+usersResObj.getId());
		System.out.println("the user created is : "+usersResObj.getCreatedAt());

	}

}
