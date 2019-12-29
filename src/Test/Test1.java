package Test;



import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1{

public static void main (String[] args) throws Exception {
	
	
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kasik\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
	//WebDriver driver = new FirefoxDriver();
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("marionette", false); 
    
    FirefoxOptions options = new FirefoxOptions(); 
    options.merge(capabilities);
    
    FirefoxDriver driver = new FirefoxDriver(options);
    WebDriverWait wait = new WebDriverWait(driver,10);

	//go to https://www.sherwin-williams.com/ (it is using Angular on front end, not our product)
    
	String baseUrl = "https://www.sherwin-williams.com/";
    driver.get(baseUrl + "/");
    
	//click "Shop Our Products"
    
     driver.findElement(By.xpath("//a[@href='/homeowners/']")).click();
    
	//	click "For Homeowners" on overlaying popup
    
     driver.findElement(By.xpath("//a[@href='https://www.sherwin-williams.com/homeowners']")).click();
    
	//	search for "tuberose paint"
    
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SimpleSearchForm_SearchTerm")));

    driver.findElement(By.id("SimpleSearchForm_SearchTerm")).clear();
    driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys("tuberose");
    
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.as_thumbnail > img")));

	//	click on searched product "SW 6578 | tuberose | interior/exterior"
    
    driver.findElement(By.cssSelector("div.as_thumbnail > img")).click();

	//	verify you are on the right page by checking url part: "https://www.sherwin-williams.com/homeowners/color/find-and-explore-colors/paint-colors-by-family/SW6578-tuberose"
	    
    String url = driver.getCurrentUrl();
	    
    if (!url.startsWith("https://www.sherwin-williams.com/homeowners/color/find-and-explore-colors/paint-colors-by-family/SW6578-tuberose")) {
	    	throw new Exception("you are not on a good page");
	    }
	//	verify product name is displayed "SW 6578 Tuberose"
	// Tuberose SW 6578
	    
	    WebElement tuberoseLiCurrents = driver.findElement(By.xpath("//li[@class='current']"));
	    
	    String currentTuberoseString = tuberoseLiCurrents.getText();
	    String[] currentTuberoseStringArray = currentTuberoseString.split(" ");

	    StringBuilder sb = new StringBuilder(50);
	    int currentTuberoseStringArrayLength = currentTuberoseStringArray.length;
	    for (int i = 0; i < currentTuberoseStringArrayLength;i++) {
	    	if(i == 0) {
	    		continue;
	    		
	    	}
	    	else {
	    		sb.append(currentTuberoseStringArray[i]);
	    		sb.append(' ');
	    		
	    		
	    	}
	    }
	    sb.append(currentTuberoseStringArray[0]);
	  

	    String finalResult = sb.toString();		
	    if (!finalResult.equalsIgnoreCase("SW 6578 Tuberose")) {
	    	throw new Exception("Product name is not compliant.");
	    }
	    
	    	  
	//	click "DETAILS" tab
	
	 
	    String detailsButton = "//button[@id='color-info--color-details-tab']";
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(detailsButton)));
	 driver.findElement(By.xpath(detailsButton)).sendKeys(Keys.ENTER);

	 
	//	verify "Hex Value: #d47c8c"
	 
	 String hexValue= driver.findElement(By.xpath( "//div[@class='color-hex ng-binding']")).getText();
	  

	    if (!hexValue.equalsIgnoreCase("Hex Value: #d47c8c")) {
	    	throw new Exception("hexValue is not Hex Value: #d47c8c.");
	    }
    
	//	click "View All Red Paint Colors" link
	 
	String viewRedPaint =  "//a[@href='/homeowners/color/find-and-explore-colors/paint-colors-by-family/family/red']";
	 
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(viewRedPaint)));
    driver.findElement(By.xpath(viewRedPaint)).sendKeys(Keys.ENTER);
    
    
	//	search for "tuberose" in find a color field
    
    String findTuberose = "//input[contains(@class, 'sw-color-family__search-input')]";
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(findTuberose)));
    driver.findElement(By.xpath(findTuberose)).clear();
    driver.findElement(By.xpath(findTuberose)).sendKeys("tuberose");
    
	//	get HREF from tuberose rectangle (property)
    
    String href = "//a[contains(@class, 'sw-color-family__swatch-container sw-color-family__swatch-container--search-results')]";

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(href)));
    String verifyProduct = driver.findElement(By.xpath(href)).getAttribute("href");
        
    
	//	open new tab
	//	navigate to the link from HREF (step above) in the newly opened tab

    
   String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
   
   driver.findElement(By.cssSelector("body")).sendKeys(selectLinkOpeninNewTab);
   ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		   driver.switchTo().window(tabs.get(0));
		   driver.get(verifyProduct);
    
    
	//  verify product name is displayed "SW 6578 Tuberose" (same step as before)
    
		    tuberoseLiCurrents = driver.findElement(By.xpath("//li[@class='current']"));
		    
		     currentTuberoseString = tuberoseLiCurrents.getText();
		     currentTuberoseStringArray = currentTuberoseString.split(" ");

		    sb = new StringBuilder(50);
		     currentTuberoseStringArrayLength = currentTuberoseStringArray.length;
		    for (int i = 0; i < currentTuberoseStringArrayLength;i++) {
		    	if(i == 0) {
		    		continue;
		    		
		    	}
		    	else {
		    		sb.append(currentTuberoseStringArray[i]);
		    		sb.append(' ');
		    		
		    		
		    	}
		    }
		    sb.append(currentTuberoseStringArray[0]);
		  

		   finalResult = sb.toString();		
		    if (!finalResult.equalsIgnoreCase("SW 6578 Tuberose")) {
		    	throw new Exception("Product name is not compliant.");
		    }
    //close browser
		   driver.close(); 
	
  
	
	
	}

}
