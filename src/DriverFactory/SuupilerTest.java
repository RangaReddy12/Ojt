package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibray.Erp_Stock;
import Utilities.ExcelFileUtil;

public class SuupilerTest {
	WebDriver driver;
	//calling functions
	Erp_Stock st=new Erp_Stock();
@BeforeTest
public void adminlogin()throws Throwable
{
System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
driver=new ChromeDriver();
driver.get("http://webapp.qedge.com");
driver.manage().window().maximize();
//calling login function
st.adminLogin(driver, "admin", "master");
}
@Test
public void supplierCreation()throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil();
int rc=xl.rowCount("Suppliers");
for(int i=1;i<=rc;i++)
{
String sname=xl.getData("Suppliers", i, 0);
String address=xl.getData("Suppliers", i, 1);
String city=xl.getData("Suppliers", i, 2);
String country=xl.getData("Suppliers", i, 3);
String contPerson=xl.getData("Suppliers", i, 4);
String pnumber=xl.getData("Suppliers", i, 5);
String mail=xl.getData("Suppliers", i, 6);
String mnumber=xl.getData("Suppliers", i, 7);
String note=xl.getData("Suppliers", i, 8);
//call supplier creation method;
String status=st.Createsuppliers(driver, sname, address, city, country, 
contPerson,	pnumber, mail, mnumber, note);
xl.setData("Suppliers", i, 9, status);
}
}
@AfterTest
public void logout()
{
driver.close();
}
}













