package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods for 
 * the actions performed on Properties file
 * @author SATURN
 *
 */
public class PropertyFileUtility {

	private Properties property;
	
	/**
	 * This method is used to initialize Properties file
	 * @param filePath
	 */
	public void propertyConfig(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to fetch the value from 
	 * properties file based on key
	 * @param key
	 * @return
	 */
	public String fetchProperty(String key) {
		return property.getProperty(key);
	}
	
	/**
	 * This method is used to write data into properties file
	 * @param key
	 * @param value
	 * @param filePath
	 * @param message
	 */
	public void setDataToProperties(String key, String value, 
			String filePath, String message) {
		property.put(key, value);
		
		try {
			property.store(new FileOutputStream(filePath), message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
