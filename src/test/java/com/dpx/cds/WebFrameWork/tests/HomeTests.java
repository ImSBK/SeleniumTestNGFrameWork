package com.dpx.cds.WebFrameWork.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dpx.cds.WebFrameWork.base.BaseTest;
import com.dpx.cds.WebFrameWork.driver.DriverFactory;

import io.qameta.allure.Description;

public class HomeTests extends BaseTest {
	
	@Description("Test to check window handle")
	@Test(description = "check window handle")
	public void test()
	{
		String wh = DriverFactory.getDriver().getWindowHandle();
	     SoftAssert sa = new SoftAssert();
		 sa.assertEquals(wh, "23456");
		 sa.assertAll();
	    //Assert.assertEquals(wh, "23456");
	}

}
