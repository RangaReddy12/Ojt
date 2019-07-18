package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibray.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@Test
	public void startTest() throws Throwable
	{	//creating excel object to access excel utilities
		ExcelFileUtil excel= new ExcelFileUtil();
		
		for(int i=1;i<=excel.rowCount("MasterTestCases");i++){
			String moduleStatus="";	
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y")){
				//Define Module Name
				String TCModule=excel.getData("MasterTestCases", i,1);
				//generate html report
        report=new ExtentReports("./Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
				int rowcount= excel.rowCount(TCModule);
          test=report.startTest("TCModule");			
			for(int j=1;j<=rowcount;j++){
					String Description=excel.getData(TCModule, j,0);
					String Object_Type=excel.getData(TCModule, j,1);
					String Locator_Type=excel.getData(TCModule, j,2);
					String Locator_Value=excel.getData(TCModule, j,3);
					String Test_Data=excel.getData(TCModule, j,4);
					
					//System.out.println(Description);
					
					//calling the test step functions
					try{
						if(Object_Type.equalsIgnoreCase("startBroswer")){
				driver=FunctionLibrary.startBrowser(driver);
				test.log(LogStatus.INFO, Description);
					}
					else if(Object_Type.equalsIgnoreCase("openApplication")){
						System.out.println("came into openApplication");
						FunctionLibrary.openApplication(driver);
						test.log(LogStatus.INFO, Description);
						}
						else if(Object_Type.equalsIgnoreCase("waitForElement")){
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);	
						}
						else if(Object_Type.equalsIgnoreCase("titleValidation")){
							FunctionLibrary.titleValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_Type.equalsIgnoreCase("typeAction")){
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
						}
						else if(Object_Type.equalsIgnoreCase("clickAction")){
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Object_Type.equalsIgnoreCase("closeBrowser")){
						FunctionLibrary.CloseApplication(driver);
						test.log(LogStatus.INFO, Description);
						}
						else if(Object_Type.equalsIgnoreCase("captureData")){
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO, Description);
							}
						
						else if(Object_Type.equalsIgnoreCase("tableValidation")){
							FunctionLibrary.tableValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
							}
						
						excel.setData(TCModule, j, 5, "Pass");
						test.log(LogStatus.PASS, Description);
						moduleStatus="true";
					}catch(Exception e){
						excel.setData(TCModule, j, 5, "Fail");
						test.log(LogStatus.FAIL, Description);
						moduleStatus="false";
					}
					
					if(moduleStatus.equalsIgnoreCase("true"))
					{
					excel.setData("MasterTestCases", i, 3, "Pass");
					}
					if(moduleStatus.equalsIgnoreCase("false"))
					{
					excel.setData("MasterTestCases", i, 3, "Fail");
					}
				report.flush();
				report.endTest(test);
				}
				
			}
	if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("N")){
		excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
		}
	}
}









