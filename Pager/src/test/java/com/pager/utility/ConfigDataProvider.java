package com.pager.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author JFigu
 * This class reads data from config.properties file
 */

public class ConfigDataProvider {
	Properties pro;
	
	//constructor
	public ConfigDataProvider() {
		//set file path
		File src = new File("./Config/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			
			//read the properties files using properties class object
			pro = new Properties(); 
			
			//load config file
			pro.load(fis);
			
		} catch (Exception e) {
			System.err.println("Not able to load config file >> " + e.getMessage());
		} 
	}
				
	//method to return data from config.properties files
	public String getDataFromConfig() {
		return pro.getProperty("keyToSearch");
	}
	
	public String getBrowser() {
		return pro.getProperty("Browser");
	}
	
	public String getQaURL() {
		return pro.getProperty("qaURL");
	}
	
	public String getStagingURL() {
		return pro.getProperty("uatURL");
	}
	
	public String getProdURLL() {
		return pro.getProperty("prodURL");
	}
/*	
	public String getUsername() {
		return pro.getProperty("username");
	}
	
	public String getMessage() {
		return pro.getProperty("message");
	}*/
	
	
	
}
