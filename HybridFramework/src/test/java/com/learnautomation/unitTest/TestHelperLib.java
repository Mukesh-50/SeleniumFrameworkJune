package com.learnautomation.unitTest;

import org.testng.annotations.Test;

import com.learnautomation.utility.Helper;

public class TestHelperLib {
	
	
	@Test
	public void testDates()
	{
		System.out.println(Helper.getCurrentDateTime());
	}

}
