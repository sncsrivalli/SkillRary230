package testscripts;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
//Skillrary app test case 1
public class Test1 extends BaseClass{

	@Test
	public void test1() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		
		home.clickGearsTab();
		home.clickSkillraryDemoApp();
		web.handleChildBrowser();
		soft.assertEquals(demoApp.getPageHeader(), "SkillRary-ECommerce");
		
		demoApp.mouseHoverToCourse(web);
		demoApp.clickSeleniumTraining();
		
		soft.assertEquals(selenium.getPageHeader(), "Selenium Training");
		selenium.doubleClickAdd(web);
		selenium.clickAddToCart();
		
		web.handleAlert("OK");
		Thread.sleep(2000);
		soft.assertEquals(selenium.getItemAddedMessage(),"Item added to cart");
		
		soft.assertAll();
	}
}
