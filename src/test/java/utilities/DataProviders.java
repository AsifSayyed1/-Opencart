package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Open_cart.xlsx";  // taking xl file from testdata
		
		ExcelUtility xlutil = new ExcelUtility(path);  // creating an object for xlutility
		                                               // methods invoke from ExcelUtility class in utilities package
		
		int totalrows = xlutil.getRowCount("Sheet1"); // method of Excelutility getRowCount
		int totalcols = xlutil.getCellCount("Sheet1", 1); // method of ExcelUtility getCellcount
		
		String logindata [][] = new String[totalrows][totalcols]; // created two dimensional array which can store
		
		for(int i=1;i<=totalrows;i++)  // read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++)
			{
	            // storing data in two dimensional array		
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); // 1, 0 // method from ExcelUtility gatCellData
			}
		}
		return logindata;  // returning two dimension array
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
