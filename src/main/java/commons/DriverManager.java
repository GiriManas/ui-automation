package commons;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utilities.LoggerLib;


public class DriverManager {
	public static WebDriver driver;
	private DesiredCapabilities capabilities;
	public Logger log = LoggerLib.writeLog(this.getClass().getName());
	//private  String browser;
	
	public static WebDriver getTheDriverInstance(){
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	public  WebDriver getDriverInstance(String browserName) {
		if(browserName==null){
			log.info("TESTNG.xml- Null Browser: Default Browser CHROME instance initiated");
			browserName="CHROME";
		}
		browserName = browserName.trim().toUpperCase();

		switch(browserName){
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", Constants.BROWSERDRIVERFOLDERLOCATION+"\\geckodriver.exe");
//			capabilities = DesiredCapabilities.firefox();
//			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver();
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", Constants.BROWSERDRIVERFOLDERLOCATION+"\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", Constants.BROWSERDRIVERFOLDERLOCATION+"\\IEDriverServer.exe");
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("EnableNativeEvents", false);
			capabilities.setCapability("ignoreZoomSetting", true);
			driver=new InternetExplorerDriver(capabilities);
			break;

		default:
			System.out.println("By Default Firefox has been launched");
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		}



		return driver;
	}

}