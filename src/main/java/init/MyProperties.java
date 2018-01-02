package init;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class MyProperties {

	private static Logger logger = LogManager.getRootLogger();
	private static Properties prop= new Properties();

	public MyProperties() throws IOException {

		String fileName = System.getProperty("user.dir")+"/src/config.properties";
		InputStream inputStr=new FileInputStream(fileName);
		prop.load(inputStr);
	}

	//
	// public static long getLong(String key) {
	// String strValue = getString(key);
	// long result = -1;
	// try {
	// result = Long.parseLong(strValue);
	// } catch (Exception exc) {
	// logger.error(exc.getLocalizedMessage());
	// }
	// return result;
	// }
	//
	// public static int getInteger(String key) {
	// String strValue = getString(key);
	// int result = -1;
	// try {
	// result = Integer.parseInt(strValue);
	// } catch (Exception exc) {
	// logger.error(exc.getLocalizedMessage());
	// }
	// return result;
	// }


	public static String getString(String key) {
		String returnValue = System.getProperty(key);
		if (returnValue != null && returnValue.length() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug(key + " assigned by system property");
			}
			return returnValue;
		}
		try {
			returnValue = prop.getProperty(key);
		} catch (MissingResourceException e) {
			returnValue = '!' + key + '!';
		}
		return returnValue;
	}

	public static String getPath(String key){
		String strValue= System.getProperty("user.dir") + getString(key);
		return strValue;
	}


}