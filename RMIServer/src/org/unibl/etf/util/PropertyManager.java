package org.unibl.etf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	private static PropertyManager instance;
	
	private PropertyManager() {}
	
	public static PropertyManager getInstance() {
		if(instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}
	
	public String getFilesPath() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("FILES_PATH");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getPolicyPath() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("POLICY_PATH");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}

}
