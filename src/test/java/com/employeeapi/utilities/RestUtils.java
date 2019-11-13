package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("Sami"+generatedString);
	}

	public static String empSal()
	{
		String generatedNumericSalary = RandomStringUtils.randomNumeric(5);
		return (generatedNumericSalary);
	}
	
	public static String empAge()
	{
		String generatedNumericAge = RandomStringUtils.randomNumeric(2);
		return (generatedNumericAge);
	}


}
