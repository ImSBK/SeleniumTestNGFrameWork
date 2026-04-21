package com.dpx.cds.WebFrameWork.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dpx.cds.WebFrameWork.base.BaseTest;
import com.dpx.cds.WebFrameWork.driver.DriverFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Epic("Maven 2.0")
@Feature("Search API")
public class LoginTests extends BaseTest {

    @Story("Get page title")
	@Link(name = "allure", type="AUT")
	@Link("https://qameta.io")
	@TmsLink(value = "Tms12345")
	@Issue(value = "Issue234")
	@Description("Test to check the login page title")
	@Test(description="check title details")
	public void loginTest()
	{
		String title = DriverFactory.getDriver().getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"Maven Repository: Search/Browse/Explore");
	}
	
}
