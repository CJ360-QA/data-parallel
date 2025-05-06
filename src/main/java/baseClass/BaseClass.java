package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	
	public static Properties prop;
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
		logger=LogManager.getLogger(this.getClass());
		
		if(driver==null) {
			try {
				prop=new Properties();
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\configProperties\\config.properties");
				prop.load(fis);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			String browser=prop.getProperty("username");
			
			if(browser.equalsIgnoreCase("browser")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			} else if(browser.equalsIgnoreCase("edge")){
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			} else {
				throw new RuntimeException("invalid browser:" +browser);
			}
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
			
		}
	}
	
	@AfterClass
	public void teardown() {
		if(driver != null) {
			driver.close();
		}
	}
	
	
	
	public String captureScreen(String ss) throws IOException {
		String time=new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String fileDrop=System.getProperty("user.dir")+"screenshots"+ss+time+".png";
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
