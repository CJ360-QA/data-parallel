package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import baseClass.BaseClass2;

public class HomePage extends BaseClass2 {

	
	public HomePage() {
		PageFactory.initElements( driver,this);
	}
	
	
	@FindBy (xpath="//div[@type='text']") WebElement txtuser ;
	@FindBy (xpath="//div[contains(@type, 'name')]") WebElement txtpass ;
	@FindBy (xpath="//a//span[starts-with(@type, 'text')]") WebElement sub ;
	@FindBy (xpath="//a//span[text()='text']") WebElement dd ;
	@FindBy (xpath="//span[normalize-space()='text']") List<WebElement> lin;
	@FindBy (xpath="//a[@type='text' and @class='name']") WebElement maaz ;
//	@FindBy (xpath="") WebElement  ;

	public void signup(String name, String pass) {
		txtuser.sendKeys(name);
		txtpass.sendKeys(pass);
		sub.click();
		
		Select slt=new Select(dd);
		slt.selectByVisibleText("indian");
		slt.selectByIndex(2);
		slt.selectByValue("name");
	}
	
	public void verifyTitle() {
		boolean status=maaz.isDisplayed();
		Assert.assertEquals(status, true);
		Assert.assertEquals(driver.getTitle(), "chinmaya");
	}
	
}
