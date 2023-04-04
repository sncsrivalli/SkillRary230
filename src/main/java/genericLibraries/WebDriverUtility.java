package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all reusable methods of WebDriver
 * @author SATURN
 *
 */
public class WebDriverUtility {
	private WebDriver driver;
	private Actions a;
	private Select s;

	/**
	 * This method is used to launch the browser and navigate 
	 * to application
	 * @param browser
	 * @param url
	 * @param time
	 * @return
	 */
	public WebDriver openApplication(String browser, String url, long time) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser data");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		return driver;
	}
	
	/**
	 * This method is used to wait until element is visible
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
		return e;
	}
	
	/**
	 * This method is used to mouse hover to an element
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		a = new Actions(driver);
		a.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to double click on an element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		a = new Actions(driver);
		a.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to right click on an element
	 * @param element
	 */
	public void rightClick(WebElement element) {
		a = new Actions(driver);
		a.contextClick(element).perform();
	}
	
	/**
	 * This method is used to drag and drop an element in specified 
	 * target location
	 * @param element
	 * @param target
	 */
	public void dragAndDropElement(WebElement element, WebElement target) {
		a = new Actions(driver);
		a.dragAndDrop(element, target).perform();
	}
	
	/**
	 * This method is used to select an element from drop down based on index
	 * @param element
	 * @param index
	 */
	public void dropdown(WebElement element, int index) {
		s = new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method is used to select an element from drop down based on 
	 * value
	 * @param element
	 * @param value
	 */
	public void dropdown(WebElement element, String value) {
		s = new Select(element);
		s.selectByValue(value);
	}
	
	/**
	 * This method is used to select an element from drop down based on 
	 * text
	 * @param text
	 * @param element
	 */
	public void dropdown(String text, WebElement element ) {
		s = new Select(element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * This method is used to switch to frame based on index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch to frame based on name or id attribute value
	 * @param nameOrId
	 */
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method is used to switch to frame based on frame element
	 * @param frameElement
	 */
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	/**
	 * This method is used to switch back from frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to scroll till element using element reference
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	/**
	 * This method is used to capture the screenshot of a web page
	 * @param javaUtil
	 */
	public void takeScreenshot(JavaUtility javaUtil) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/ss_"+javaUtil.getCurrentTime()
								+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to handle alert pop up
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert al = driver.switchTo().alert();
		if(status.equalsIgnoreCase("ok"))
			al.accept();
		else 
			al.dismiss();			
	}
	
	/**
	 * This method is used to switch to parent window 
	 */
	public void switchToParentWindow() {
		driver.switchTo().window(driver.getWindowHandle());
	}
	
	/**
	 * This method is used to handle child browser pop up
	 */
	public void handleChildBrowser() {
		Set<String> set = driver.getWindowHandles();
		for (String wId : set) {
			driver.switchTo().window(wId);
		}
	}
	
	/**
	 * This method is used to close current tab or window
	 */
	public void closeCurrentTab() {
		driver.close();
	}
	
	/**
	 * This method is used to close all tabs and quit the browser
	 */
	public void quitBrowser() {
		driver.quit();
	}
}
