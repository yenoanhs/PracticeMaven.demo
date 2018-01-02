package init;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {


	public static final String Path_Data = System.getProperty("user.dir") + "//src/main//resources//data//";

	public static final String File_Config = "ConfigSetup.xlsx";

//	public static final String Path_AppData = System.getProperty("user.dir") + "//src/main//resources//app//";
//
//	public static final String File_AppData = "app-debug.apk";

//	public static final String Appium_Node_Path = "C://Program Files (x86)//Appium//node.exe";
//	public static final String Appium_JS_Path = "C://Program Files (x86)//Appium//node_modules//appium//bin//appium.js";
//	public static final int Appium_Port = 4723; //9515;
//	public static final String Appium_IP = "127.0.0.1";
//	public static final String URL = "http://"+Appium_IP+":"+Appium_Port+"/wd/hub";

	public static final String log_Path = System.getProperty("user.dir") + "//src//main//resources//log//";

	private static Date date = new Date() ;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
	public static final String Log_File = "logFile_"+dateFormat.format(date) +".txt";

	public static final String File_TestData = "TestData.xlsx";
}
