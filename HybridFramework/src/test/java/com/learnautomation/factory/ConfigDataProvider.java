package com.learnautomation.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider 
{
	
	Properties pro;
	
	public ConfigDataProvider()
	{
		pro=new Properties();
		try 
		{
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Configuration/project.properties")));
		} catch (IOException e) {
			System.out.println("Unable to load config file "+e.getMessage());
		}
		
	}
	
	public String getData(String key)
	{
		return pro.getProperty(key);
	}
	
	

}
