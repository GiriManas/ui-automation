package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * This class has different components for handling the properties file
 * 
 * @author Giri
 *
 */
public class PropertiesUtiles {
	
	public static Properties prop = new Properties();
	public static InputStream input = null;
	OutputStream output = null;
	

	/**
	 * This method loads the properties file for further operation
	 * @param propertiesFile	String- Properties file location
	 * @return					Properties- returns Properties instance
	 */
  public static Properties loadProperties(String propertiesFile) {

	

	try {

		input = new FileInputStream(propertiesFile);
		prop.load(input);

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	return prop;

  }
  
  /**
   * This method is used to get the value against the key
   * @param key		String- required key as an input
   * @return		String- This returns the value of the particular key
   */
  public String getProperty(String key){
		return prop.getProperty(key);
	}
  
  @SuppressWarnings("unused")
private void printAllContent() {

		try {
			
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				System.out.println("Key : " + key + ", Value : " + value);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	  }
  
  /**
   * This method takes properties file as an input and against the key stores the value in the file
   * @param propertiesFile		String- File loaction as an input
   * @param key					String- key as an input
   * @param value				String- value to be stored against the key
   */
  public void writeProperties(String propertiesFile, String key, String value){
	  try {

			output = new FileOutputStream(propertiesFile);

			// set the properties value
			prop.setProperty(key, value);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	  }
	  
  
}