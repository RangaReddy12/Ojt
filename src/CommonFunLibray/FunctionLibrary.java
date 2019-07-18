package CommonFunLibray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;
public class FunctionLibrary  {
public static WebDriver startBrowser(WebDriver driver )throws Throwable
{
if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
{
System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
driver=new ChromeDriver();
}
else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox")){
driver=new FirefoxDriver();	
}
else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
{
driver=new InternetExplorerDriver();	
}
return driver;
}
//open application by accessing from property file
public static void openApplication(WebDriver driver)throws Throwable
{
driver.get(PropertyFileUtil.getValueForKey("URL"));
driver.manage().window().maximize();
}
//close application
public static void CloseApplication(WebDriver driver)
{
	driver.close();
}
	//developing for click action
	public static void clickAction(WebDriver driver,String locatorType,
			String locatorValue )
	{
		if(locatorType.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorValue)).click();
		}else if(locatorType.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorValue)).click();
		}
		else if(locatorType.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatorValue)).click();
		}
	}
	
	//method to send keys to web element
	public static void typeAction(WebDriver driver,String locatorType,
			String locatorValue,String testData){
			if(locatorType.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(testData);
			}
			else if(locatorType.equalsIgnoreCase("xpath")){
				driver.findElement(By.xpath(locatorValue)).clear();
				driver.findElement(By.xpath(locatorValue)).sendKeys(testData);
			}
			else if(locatorType.equalsIgnoreCase("name")){
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(testData);
			}	
	}

	// method to validate any page title
	public static void titleValidation(WebDriver driver,String expTitle){
		 String act_title = driver.getTitle();
		 Assert.assertEquals(act_title, expTitle);
	}
	
	// method to wait for an element in any page
	public static void waitForElement(WebDriver driver,String locatorType,
			String locatorValue,String timeToWait){
		WebDriverWait wait = new WebDriverWait(driver,Integer.parseInt(timeToWait) );
		if(locatorType.equalsIgnoreCase("id"))
		{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
		else if(locatorType.equalsIgnoreCase("name"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}
			else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}
			}
//to capture date format	
public static String generateDate()
{
	DateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
	Date date=new Date();
	return sdf.format(date);
}
//to capture data into notepad
public static void captureData(WebDriver driver, String locattype,String locatorvalue) 
		throws Throwable
{
String data="";
if(locattype.equalsIgnoreCase("id"))
{
data=driver.findElement(By.id(locatorvalue)).getAttribute("value");
		
}else
if(locattype.equalsIgnoreCase("xpath"))
{
data=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
}
FileWriter fw=new FileWriter("D:\\VENKATESH\\Maven_ERP\\CaptureData\\snumber.txt");
BufferedWriter bw=new BufferedWriter(fw);
bw.write(data);
bw.flush();
bw.close();
}

//table validation

public static void tableValidation(WebDriver driver,String column) throws Throwable
{
FileReader fr=new FileReader("D:\\VENKATESH\\Maven_ERP\\CaptureData\\snumber.txt");
BufferedReader br=new BufferedReader(fr);
String exp_data=br.readLine();
int column1=Integer.parseInt(column);
if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).isDisplayed())
{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn"))).click();
}else
{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel"))).click();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).clear();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box"))).sendKeys(exp_data);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn"))).click();
}
WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path")));
List<WebElement> rows=table.findElements(By.tagName("tr"));
	
for (int i = 1; i < rows.size(); i++)
{
String act_data=driver.findElement(By.xpath
("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column1+"]/div/span/span")).getText();
	Assert.assertEquals(act_data, exp_data);
	break;
	}
	}


//stock items
public static void stockCategories(WebDriver driver)
{
WebElement stockItem=driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"));
WebElement stockCategory=driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a"));
Actions mouse=new Actions(driver);
mouse.moveToElement(stockItem).build().perform();
mouse.moveToElement(stockCategory).click().build().perform();

}
public static void tableValidation1(WebDriver driver,String exp_data) throws Throwable
	{
			
if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).isDisplayed())
{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).clear();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).sendKeys(exp_data);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn1"))).click();
}else
{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel1"))).click();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).clear();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).sendKeys(exp_data);
//driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn1"))).click();
driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}
		
WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path1")));
List<WebElement> rows=table.findElements(By.tagName("tr"));
for (int i = 1; i < rows.size(); i++)
{
String act_data=driver.findElement(By.xpath
("//*[@id='ewContentColumn']/div[3]/form/div/div//table[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
Assert.assertEquals(act_data, exp_data);
break;
}
}

}
















