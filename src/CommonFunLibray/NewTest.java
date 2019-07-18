package CommonFunLibray;

import org.testng.annotations.Test;

import DriverFactory.DriverScript;

public class NewTest {
  @Test
  public void f() throws Throwable {
	  DriverScript drivobject=new DriverScript();
	  drivobject.startTest();
  }
}
