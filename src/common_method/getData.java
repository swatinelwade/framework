package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Exception;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData {
	
	public static ArrayList <String> getDataExcel(String testSheetName, String testCaseName) throws IOException
	{
		ArrayList <String> arraydata = new ArrayList<String>();
		
		//Step1- Open Excel data file by creating the object of file input stream
		FileInputStream fis= new FileInputStream("F:\\Swati_Full_Stack_Tester\\RestAssured\\test_data.xlsx");
		
		//Step2- Create the object of XSSFWorkbook to open the Excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//Step3- Access the desire sheet
		//Step3.1- Fetch the count of sheet available in the Excel file
		int countofsheet = workbook.getNumberOfSheets();
		
		//Step3.2- Fetch the name of sheet and compare against desired sheet name
		for(int i=0; i<countofsheet; i++)
		{
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testSheetName))
			{
				//Step4- Access the sheet and iterate through rows 
				//to fetch the column in which test case name is found
				XSSFSheet sheet = workbook.getSheetAt(i);
				//Step4.1- Create Iterator for rows
				Iterator<Row> Rows = sheet.iterator();
				Row firstRow = Rows.next();
				
				
				//Step4.2 Create iterator for cells
				Iterator<Cell> Cells = firstRow.cellIterator();
				
				int j=0;
				int TC_Column=0;
				
				//Step4.3- Read the cell values of row number 1 to compare against the test case name
				while(Cells.hasNext())
				{
					Cell cellvalue = Cells.next();
					if (cellvalue.getStringCellValue().equalsIgnoreCase("TC_Name"))
					{
						TC_Column=j;
					}
					j++;
				}
				
				//Step5- Fetch the data for designated test case
				while(Rows.hasNext())
				{
					Row dataRow = Rows.next();
					if (dataRow.getCell(TC_Column).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						Iterator<Cell> dataCellValue = dataRow.cellIterator();
						
						while(dataCellValue.hasNext())
						{
							Cell dataofCell = dataCellValue.next();
							
							//Method(1) - to extract cell values by using try and catch
							try
							{
								String testdata = dataofCell.getStringCellValue();
								arraydata.add(testdata);
							}
							catch (IllegalStateException e)
							{
								int inttestdata = (int) dataofCell.getNumericCellValue();
								String stringtestdata = Integer.toString(inttestdata);
								arraydata.add(stringtestdata);
							}
							
							//Method(2) - to extract the cell value by using if and else
							//CellType datatype = dataCellValue.next().getCellType();
							
							/*if (datatype.toString() == "NUMERIC")
							{
								int inttestdata = (int) dataCellValue.next().getNumericCellValue();
								String stringtestdata = Integer.toString(inttestdata);
								arraydata.add(stringtestdata);
							}
							else if (datatype.toString() == "STRING")
							{
								String testdata = dataofCell.getStringCellValue();
								arraydata.add(testdata);
							}*/
							
							//Method(3) - Extract the data by converting it into string first
							/*String testdata = dataofCell.toString().replaceAll("\\.\\d+$", "");
							arraydata.add(testdata);
							System.out.println(testdata);*/
							
							
							//Method(4) - Extract the data by using data formatter
							/*DataFormatter format = new DataFormatter();
							String testdata = format.formatCellValue(dataofCell);
							arraydata.add(testdata);
							System.out.println(testdata);*/
							
						}
					}
				}
			}
		}
		
	return arraydata;
	}

}
