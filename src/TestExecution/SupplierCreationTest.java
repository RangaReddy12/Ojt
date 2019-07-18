package TestExecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibray.Erp_Stock;
import Utilities.ExcelFileUtil;

public class SupplierCreationTest {
	WebDriver driver;
	 Erp_Stock erpstock=new Erp_Stock();
	  
	@BeforeTest
	public void adminlogin()throws Throwable
	{
System.setProperty("webdriver.chrome.driver", "D:\\VENKATESH\\StockAccounting_New\\CommonJars\\chromedriver.exe");
      driver = new ChromeDriver();
     driver.get("http://webapp.qedge.com/login.php");
	     driver.manage().window().maximize();
	     Thread.sleep(5000);
	     erpstock.adminLogin(driver,"admin", "master");
	}
  @Test
  public void TC_001() throws Throwable {
	  ExcelFileUtil exlobj=new ExcelFileUtil();
  int rc=   exlobj.rowCount("Suppliers");
      for(int i=1;i<=rc;i++)
      {
      String spname=exlobj.getData("Suppliers", i, 0);
      String address=exlobj.getData("Suppliers", i, 1);
      String city=exlobj.getData("Suppliers", i, 2);
      String country=exlobj.getData("Suppliers", i, 3);
      String contPerson=exlobj.getData("Suppliers", i, 4);
      String phonenumber=exlobj.getData("Suppliers", i, 5);
      String email=exlobj.getData("Suppliers", i, 6);
      String mobileNumber=exlobj.getData("Suppliers", i, 7);
      String Notes=exlobj.getData("Suppliers", i, 8);
String status=erpstock.Createsuppliers(driver,spname, address, city, country, contPerson, phonenumber, email, mobileNumber, Notes);
      
      exlobj.setData("Suppliers", i , 9,status);
        }
  }
  @AfterTest
  public void logout()
  {
	//  driver.close();
  }
}
