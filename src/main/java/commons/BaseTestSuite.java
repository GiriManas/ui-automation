package commons;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utilities.ExtentTestNGReportBuilder;
import utilities.PropertiesUtiles;



/**
 * This class is the base class of all the test classes where different kind of initialization is done
 * 
 * 
 * This class extends ExtentTestNGReportBuilder which is responsible for report generation
 * 
 * 
 * @author Giri
 *
 */
public class BaseTestSuite extends ExtentTestNGReportBuilder {

	public PropertiesUtiles properties = new PropertiesUtiles();
	public Random random = new Random(); 
	private static WebDriver driver;

	/** Stores the name of the current test method. */
	private static String testMethodName;

	/**
	 * Logger instance
	 */
	public Logger log = Constants.log;
			
	DriverManager dManager= new DriverManager();	

	//@BeforeClass(alwaysRun = true) 
	@BeforeSuite
	public void setup(ITestContext context) throws Exception{
		
		DriverManager dManager= new DriverManager();
		String browser= context.getCurrentXmlTest().getParameter("Selenium.browser");
		log.info("Browser Instance has been created");
		setDriver(dManager.getDriverInstance(browser));

		if(getDriver() instanceof FirefoxDriver){
			log.info("Firefox Instance Instance has been created");
		}else if(getDriver() instanceof ChromeDriver){
			log.info("Chrome Instance has been created");
		}else if(getDriver() instanceof InternetExplorerDriver){
			log.info("IE Instance has been created");
		}
		getDriver().manage().window().maximize();
		log.info("Maximizing the Window");
		getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

	}

	

	/**
	 * This method is used to navigate to the application
	 * returns
	 */
	public void lauchTheApplication(){
		try{
			HashMap<String, String> envDetails= getEnvironmentDetails();
			String url= envDetails.get("url");
			getDriver().get(url);
			//Need to implement the UNAME and PASSWORD details

		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error in login to the application");
		}
	}



	/**
	 * This method is used to get the environment details of the ENVIRONMENT section which is mentioned as base
	 * This returns a HashMap with both the key and value as String.
	 * @return  HashMap   This returns HashMap with environment details as key and their corresponding values.
	 */
	public HashMap<String,String> getEnvironmentDetails(){
		PropertiesUtiles.loadProperties(Constants.TESTRESOURCES+"testdata.properties");
		String reference=properties.getProperty("base");
		String url=properties.getProperty(reference+".url");
		String userName=properties.getProperty(reference+".username");
		String password=properties.getProperty(reference+".password");
		HashMap<String, String> envDetails = new HashMap<>();
		envDetails.put("url", url);
		envDetails.put("username", userName);
		envDetails.put("password", password);
		return envDetails;

	}


	/**
	 * This method is a overloaded method which is used to get the environment details of the ENVIRONMENT section
	 * which is mentioned as base.
	 * This returns a HashMap with both the key and value as String.
	 * 
	 * @param   baseInTestDataProp
	 * @return  HashMap This returns HashMap with environment details as key and their corresponding values.
	 */
	public HashMap<String,String> getEnvironmentDetails(String baseInTestDataProp){
		PropertiesUtiles.loadProperties(Constants.TESTRESOURCES+"testdata.properties");
		String reference=properties.getProperty(baseInTestDataProp);
		String url=properties.getProperty(reference+".url");
		String userName=properties.getProperty(reference+".username");
		String password=properties.getProperty(reference+".password");
		HashMap<String, String> envDetails = new HashMap<>();
		envDetails.put("url", url);
		envDetails.put("username", userName);
		envDetails.put("password", password);
		return envDetails;

	}

	public WebDriver getDriverInstance(){
		return getDriver();
	}


	@BeforeClass
	public void setupClass() {
		String envpropFile = PropertiesUtiles.loadProperties(Constants.TESTRESOURCES+"config.properties")
				.getProperty("env.propfile");
		PropertiesUtiles.loadProperties(Constants.TESTRESOURCES+envpropFile);
	}
	
	public String getURLFromProperties(){
		HashMap<String, String> envDetails= getEnvironmentDetails();
		String url= envDetails.get("url");
		return url;
		
	}
	

	@BeforeMethod
	public void setTestMethodName(Method method) {
		testMethodName = method.getName();

		Thread.currentThread().setName(testMethodName);
	}

	@AfterSuite(alwaysRun=true)
	public void tearDown(){
		log.info("Closing the browser instance");
		getDriver().quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		BaseTestSuite.driver = driver;
	}
	


}