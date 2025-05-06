package testCases;

import org.testng.annotations.Test;

import baseClass.BaseClass2;
import pages.HomePage;

public class HomeTest extends BaseClass2{

	
	
	@Test (dataProvider="dp")
	public void t1(String name, String pass) {
		HomePage hp=new HomePage();
		hp.signup(name, pass);
		hp.verifyTitle();
	}
	
}
