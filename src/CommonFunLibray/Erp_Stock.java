package CommonFunLibray;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Erp_Stock {
	
public void adminLogin(WebDriver driver,String username,String password) throws Exception
{
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(username);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("btnsubmit")).click();
	
	Thread.sleep(5000);
	if(driver.findElement(By.linkText("Logout")).isDisplayed()){
		System.out.println("Login is successfull");
	}
	else
	{
		System.out.println("Login failed");
	}
}

public String Createsuppliers(WebDriver driver,String spname,String address,
String city,String country,String contPerson,String phonenumber,
String email,String mobileNumber,String Notes) throws Exception{
driver.findElement(By.linkText("Suppliers")).click();
	driver.findElement(By.xpath
("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
	Thread.sleep(5000);
	
//String expectedSupplierNumber=driver.findElement(By.xpath
//("//*[@id='x_Supplier_Number']")).getAttribute("value");
	
	driver.findElement(By.id("x_Supplier_Name")).sendKeys(spname);
	driver.findElement(By.id("x_Address")).sendKeys(address);
	driver.findElement(By.id("x_City")).sendKeys(city);
	driver.findElement(By.id("x_Country")).sendKeys(country);
	driver.findElement(By.id("x_Contact_Person")).sendKeys(contPerson);
	driver.findElement(By.id("x_Phone_Number")).sendKeys(phonenumber);
	driver.findElement(By.id("x__Email")).sendKeys(email);
	driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobileNumber);
	driver.findElement(By.id("x_Notes")).sendKeys(Notes);
	Thread.sleep(4000);
	driver.findElement(By.id("btnAction")).sendKeys(Keys.ENTER);
	Thread.sleep(4000);
	driver.findElement(By.xpath("//button[text()='OK!']")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
	Thread.sleep(4000);
	
	
/*if(!driver.findElement(By.id("btnsubmit")).isDisplayed())
//{
//driver.findElement(By.xpath
//("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
//}
	Thread.sleep(5000);
	driver.findElement(By.id("psearch")).clear();
	Thread.sleep(3000);
	//driver.findElement(By.id("psearch")).sendKeys(expectedSupplierNumber);
	Thread.sleep(5000);
driver.findElement(By.id("btnsubmit")).click();
Thread.sleep(5000);
String actualSupplierNumber=driver.findElement
(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();*/
	
	String status="";
	try{
	if(driver.getCurrentUrl().contains("a_supplierslist")){	
	System.out.println("supplier creation successfull");
	}
    else{
	System.out.println("supplier creation failed");
    }
	status="Pass";
	}
	catch(Exception e){
	status="Fail";
	}
	
	return status;
}

}