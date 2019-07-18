package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil   {
public static String getValueForKey(String Key) throws Throwable
{
Properties configProperties =new Properties();
configProperties.load(new FileInputStream(new File
("D:\\VENKATESH\\StockAccounting_New\\PropertiesFile\\Environment.properties")));
return configProperties.getProperty(Key);
}
}
