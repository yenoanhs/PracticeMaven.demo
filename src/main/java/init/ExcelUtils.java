package init;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public String path;
	public FileInputStream fileIn = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelUtils(String path) {

		this.path = path;

		try {
			// Open the Excel file
			fileIn = new FileInputStream(path);

			// Access the required test data sheet
			workbook = new XSSFWorkbook(fileIn);
			sheet = workbook.getSheetAt(0);
			fileIn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExcelUtils(String path, String sheetname) {

		this.path = path;
		try {
			// Open the Excel file
			fileIn = new FileInputStream(path);

			// Access the required test data sheet
			workbook = new XSSFWorkbook(fileIn);
			sheet = workbook.getSheet(sheetname);
			fileIn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetname) {
		int index = workbook.getSheetIndex(sheetname);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	public int getRowCount() {

		int number = sheet.getLastRowNum() + 1;
		return number;
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public String getCellData(int rowNum, int colNum) {

		try {
			if (rowNum <= 0)
				return "";

			if (colNum == -1)
				return "";

			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";

			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			switch (cell.getCellTypeEnum()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
			case FORMULA: {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					// System.out.println(cellText);
				}
				return cellText;
			}
			case BLANK:
				return "";
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			default: // ERROR _NONE
			{
				System.out.println("cell error: " + cell.getErrorCellString());
				return "";
			}
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist";
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {

			sheet = workbook.getSheet(sheetName);

			String value = getCellData(rowNum, colNum);
			return value;

		} catch (Exception e) {
			System.out.println("function: String getCellData(String sheetName, int colNum, int rowNum)");
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {

			sheet = workbook.getSheet(sheetName);
			int colNum = getColFromName(colName);
			String value = getCellData(rowNum, colNum);
			return value;

		} catch (Exception e) {
			System.out.println("function:  String getCellData(String sheetName,String colName,int rowNum)");
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public String getCellData(String colName, int rowNum) {
		try {

			int colNum = getColFromName(colName);
			String value = getCellData(rowNum, colNum);
			return value;

		} catch (Exception e) {
			System.out.println("function:  String getCellData(String colName,int rowNum)");
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public int getColFromName(String sheetName, String colName) {
		try {
			sheet = workbook.getSheet(sheetName);
			int col_Num = -1;
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			return col_Num;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getColFromName(String colName) {
		try {

			int col_Num = -1;
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			return col_Num;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters

	// returns true if data is set successfully else false
	public boolean setCellData(int rowNum, int colNum, String data) {

		try {

			row = sheet.getRow(rowNum);

			cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(data);

			} else {
				cell.setCellValue(data);
			}
			// Constant variables Test Data path and Test Data file name
			fileOut = new FileOutputStream(Constant.Path_Data + Constant.File_TestData);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return true;
	}

	public boolean setCellData(String sheetName, int rowNum, int colNum, String data) {

		try {

			this.sheet = workbook.getSheet(sheetName);
			setCellData(rowNum, colNum, data);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean setCellData(String sheetName, int rowNum, String colName, String data) {

		try {

			this.sheet = workbook.getSheet(sheetName);
			int colNum = this.getColFromName(colName);
			setCellData(rowNum, colNum, data);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean setCellData(int rowNum, String colName, String data) {

		try {

			int colNum = this.getColFromName(colName);
			setCellData(rowNum, colNum, data);

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Map<String, Object[]> getListTestName(String sheetName, int colName, int colToRun) {

		try {
			sheet = workbook.getSheet(sheetName);
			int total = getRowCount();
			if (total > 1) {
				Map<String, Object[]> data = new HashMap<String, Object[]>();
				String value ;
				String name = "";
				for (int i = 1; i < total; i++) {
					row = sheet.getRow(i);
					Object[] obj = new Object[2];
					cell = row.getCell(colName);
					name = cell.getStringCellValue();

					cell = row.getCell(colToRun);
					value = cell.getStringCellValue().trim();

					obj[0] = i; //row
					obj[1] = value; //Executed or not (Y/N)

					data.put(name, obj);
				}
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// data type Data Provider
	public Object[][] getTableArray(String SheetName) {

		String[][] tabArray = null;
		try {
			sheet = workbook.getSheet(SheetName);
			int startRow = 1;
			int startCol = 1;
			int ci, cj;
			int totalRows = sheet.getLastRowNum();
			// you can write a function as well to get Column count
			int totalCols = 2;
			tabArray = new String[totalRows][totalCols];
			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
					System.out.println(tabArray[ci][cj]);
				}
			}
		}

		catch (Exception e) {
			System.out.println("function: Object[][] getTableArray(String SheetName)");
			e.printStackTrace();

		}

		return (tabArray);
	}

	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is removed successfully else false if sheet does
	// not exist
	public boolean removeSheet(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index==-1)
            return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

     // returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		// System.out.println("**************addColumn*********************");

		try {
			fileIn = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileIn);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor( IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);


			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// removes a column and all the contents
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if (!isSheetExist(sheetName))
				return false;
			fileIn = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileIn);
			sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor( IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.NO_FILL);

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

}