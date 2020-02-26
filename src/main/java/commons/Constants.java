package commons;

import org.apache.log4j.Logger;

import utilities.LoggerLib;


/**
 * This Interface is used to set the constants globally which will be project specific
 * 
 * @author Giri
 *
 */
public abstract class Constants {

	public static final String PROJECTPATH = System.getProperty("user.dir");
	public static final String TESTRESOURCES=PROJECTPATH + "/src/test/resources/";
	public static final String BROWSERDRIVERFOLDERLOCATION=PROJECTPATH + "/src/main/resources/browserdrivers/";
	public static final String LOGLEVEL="INFO";
	public static final String LOGFILENAME= "UIAutomation";
	public static final String URL=new BaseTestSuite().getURLFromProperties();
	public	final static Logger log = LoggerLib.writeLog(BaseTestSuite.class.getName());	
	
}

	
	
	


