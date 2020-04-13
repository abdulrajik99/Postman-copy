package com.qa.test;


import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.Restclient;
import com.qa.utill.Testutill;

public class getApiTest extends TestBase {

	public TestBase testbase=null;
	String serviceurl=null;
	String requlr=null;
	String url=null;
	Restclient restClient=null;
	Restclient restclient;
	CloseableHttpResponse closablehttpresponse;
	
	
	@BeforeMethod
	public void setup() throws IOException{
		testbase=new TestBase();
		 serviceurl=prop.getProperty("url");
		 requlr=prop.getProperty("requrl");
		 url=serviceurl+requlr;
		
	}
	@Test(priority=1)
	public void gettest() throws IOException{
		 restClient=new Restclient();
		 closablehttpresponse=restClient.get(url);
			//a .get status code
			int statusCode=closablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("the status code -------"+statusCode);
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"status code is not 200");
			
			//b. json string 
			String responseString=EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
			JSONObject responsejson=new JSONObject(responseString);
			System.out.println("Response from json "+responsejson);
			
			//get all per page string
			String s=Testutill.getValueByJPath(responsejson, "/per_page");
			System.out.println("the per page is "+s);
			Assert.assertEquals(Integer.parseInt(s), 6,"per_page value is not 3");
		
			String total=Testutill.getValueByJPath(responsejson, "/total");
			System.out.println("the per page is "+total);
			Assert.assertEquals(Integer.parseInt(total), 12 ,"per_page value is not 3");
			
			//get data in 1st one
			String id=Testutill.getValueByJPath(responsejson, "/data[0]/id");
			String name=Testutill.getValueByJPath(responsejson, "/data[0]/name");
			String year=Testutill.getValueByJPath(responsejson, "/data[0]/year");
			String color=Testutill.getValueByJPath(responsejson, "/data[0]/color");
			String pantone_value=Testutill.getValueByJPath(responsejson, "/data[0]/pantone_value");
			System.out.println("id :"+id);
			System.out.println("name :"+name);
			System.out.println("year :"+year);
			System.out.println("color :"+color);
			System.out.println("pantone_value :"+pantone_value);
			
			//get all headers
			Header[] headerArray=closablehttpresponse.getAllHeaders();
			HashMap<String, String> getallHeaders=new HashMap<String,String >();
			for(Header header:headerArray){
				getallHeaders.put(header.getName(), header.getValue());
			}
			System.out.println("Header array------->"+getallHeaders);
			
	}
	
	
	@Test(priority=2)
	public void gettestswithheaders() throws IOException{
		HashMap<String, String> headerMap=new HashMap<String, String>();
		headerMap.put("Accept", "application/json");
		 restClient=new Restclient();
		 closablehttpresponse=restClient.get(url,headerMap);
			//a .get status code
			int statusCode=closablehttpresponse.getStatusLine().getStatusCode();
			System.out.println("the status code -------"+statusCode);
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"status code is not 200");
			
			//b. json string 
			String responseString=EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
			JSONObject responsejson=new JSONObject(responseString);
			System.out.println("Response from json "+responsejson);
			
			//get all per page string
			String s=Testutill.getValueByJPath(responsejson, "/per_page");
			System.out.println("the per page is "+s);
			Assert.assertEquals(Integer.parseInt(s), 6,"per_page value is not 3");
		
			String total=Testutill.getValueByJPath(responsejson, "/total");
			System.out.println("the per page is "+total);
			Assert.assertEquals(Integer.parseInt(total), 12 ,"per_page value is not 3");
			
			//get data in 1st one
			String id=Testutill.getValueByJPath(responsejson, "/data[0]/id");
			String name=Testutill.getValueByJPath(responsejson, "/data[0]/name");
			String year=Testutill.getValueByJPath(responsejson, "/data[0]/year");
			String color=Testutill.getValueByJPath(responsejson, "/data[0]/color");
			String pantone_value=Testutill.getValueByJPath(responsejson, "/data[0]/pantone_value");
			System.out.println("id :"+id);
			System.out.println("name :"+name);
			System.out.println("year :"+year);
			System.out.println("color :"+color);
			System.out.println("pantone_value :"+pantone_value);
			
			//get all headers
			Header[] headerArray=closablehttpresponse.getAllHeaders();
			HashMap<String, String> getallHeaders=new HashMap<String,String >();
			for(Header header:headerArray){
				getallHeaders.put(header.getName(), header.getValue());
			}
			System.out.println("Header array------->"+getallHeaders);
			
	}
}
