package baseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass2 {

	
	public static WebDriver driver;
	public static Logger logger;
	
	
	@BeforeClass
	@Parameters({"browser", "url"})
	public void setup(String br, String url) {

		logger=LogManager.getLogger(this.getClass());
		 
		switch (br.toLowerCase()) {
		
		case "chrome":WebDriverManager.chromedriver().setup();
		              driver=new ChromeDriver();break;
		case "edge": WebDriverManager.edgedriver().setup();
		              driver=new EdgeDriver();break;
		case "IEexplorer":WebDriverManager.iedriver().setup();
		               driver=new InternetExplorerDriver();
		 default: System.out.println("invalid browser"+ br); return;
		}
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
@AfterClass
public void teardown() {
	if(driver != null) {
		driver.quit();
	}
}


public String captureScreen(String ss) throws IOException {
	String time=new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date());
	TakesScreenshot ts=(TakesScreenshot)driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	
	String fileDrop=System.getProperty("user.dir")+"\\screenshot\\"+ss+time+".png";
	File trg=new File(fileDrop);
	
	FileUtils.copyFile(src, trg);
	
	
	return (fileDrop);
}


@DataProvider (name="dp", indices= {0,3})
public Object[][] loginData(){
	Object data[][]= {{"chinmayakabi@amil.com", "chinmaya@1234"},
			{"chinmayakabi@amil.com", "chinmaya@1234"},
			{"chinmayakabi@amil.com", "chinmaya@1234"},
			{"chinmayakabi@amil.com", "chinmaya@1234"}
	};
	return data;
}


	
	
	
}