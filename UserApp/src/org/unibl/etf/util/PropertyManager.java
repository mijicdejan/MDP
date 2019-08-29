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
	
	public String getUsersBaseURL() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("USERS_BASE_URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getDateTimeFormat() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("DATE_TIME_FORMAT");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getSessionDurationFormat() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("SESSION_DURATION_FORMAT");
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
	
	public String getRMIHost() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("RMI_HOST");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public int getMulticastPort() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return Integer.parseInt(properties.getProperty("MULTICAST_PORT"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return 0;
	}
	
	public String getMulticastAddress() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("MULTICAST_ADDRESS");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getNotificationNumberFile() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("NOTIFICATION_NUMBER_FILE");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getJavaFile() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("JAVA_FILE");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getGsonFile() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("GSON_FILE");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getXMLFile() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("XML_FILE");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getKryoFile() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("KRYO_FILE");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public int getSocketPort() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return Integer.parseInt(properties.getProperty("SOCKET_PORT"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return 0;
	}
	
	public int getMonitoringPort() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return Integer.parseInt(properties.getProperty("MONITORING_PORT"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return 0;
	}
	
	public String getServerAddress() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("SERVER_ADDRESS");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getMonitoringAddress() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("MONITORING_ADDRESS");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getKeyStorePath() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("KEY_STORE_PATH");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getKeyStorePassword() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("KEY_STORE_PASSWORD");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getTrustStorePath() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("TRUST_STORE_PATH");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String getTrustStorePassword() {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("properties.properties"));
			return properties.getProperty("TRUST_STORE_PASSWORD");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}

}
