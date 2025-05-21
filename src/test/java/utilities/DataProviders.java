package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_loginData.xlsx"; // taking xl file from testData folder
		
		ExcelUtility xlutil=new ExcelUtility(path); // creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		
	String logindata[][]=new String[totalrows][totalcols]; // created for two dimensional array which can store 2 values
	
	for(int i=1;i<=totalrows;i++)// 1    // read data from xl storing in 2 d array
	{
		for(int j=0;j<totalcols;j++)   // 0 i is rows and j is coloum
		{
			logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //0,1
		}
	}
		return logindata; // returning 2 D array
		
	}

}
