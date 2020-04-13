package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop=null;
	public FileInputStream ip;
	public  int RESPONSE_STATUS_CODE_200=200;
	public  int RESPONSE_STATUS_CODE_500=500;
	public  int RESPONSE_STATUS_CODE_400=400;
	public  int RESPONSE_STATUS_CODE_401=401;
	public  int RESPONSE_STATUS_CODE_201=201;
	public TestBase() {
		try {
		prop=new Properties();
		ip=new FileInputStream("C:\\Users\\Abdul Rajik shaik\\git\\gitrepo\\postman\\src\\main\\java\\com\\qa\\config\\config.properties");
        prop.load(ip);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
        
	
	}
}
