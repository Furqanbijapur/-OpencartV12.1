package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtility is a utility class for performing operations on Excel files
 * using Apache POI. It provides methods to read, write, and format data in
 * Excel sheets.
 */
public class ExcelUtility {

    public FileInputStream fi; // Input stream for reading Excel files
    public FileOutputStream fo; // Output stream for writing to Excel files
    public XSSFWorkbook workbook; // Represents the Excel workbook
    public XSSFSheet sheet; // Represents a sheet in the workbook
    public XSSFRow row; // Represents a row in the sheet
    public XSSFCell cell; // Represents a cell in the row
    public CellStyle style; // Represents the style of a cell
    String path; // Path to the Excel file

    /**
     * Constructor to initialize the Excel file path.
     *
     * @param path The file path of the Excel file.
     */
    public ExcelUtility(String path) {
        this.path = path;
    }

    /**
     * Returns the number of used rows in the specified sheet.
     *
     * @param sheetName The name of the sheet.
     * @return The number of used rows in the sheet.
     * @throws IOException If an I/O error occurs.
     */
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum(); // Index of last row
        workbook.close();
        fi.close();
        return rowcount;
    }

    /**
     * Returns the number of cells in the specified row.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number (0-based index).
     * @return The number of cells in the row.
     * @throws IOException If an I/O error occurs.
     */
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum(); // Index of last cell
        workbook.close();
        fi.close();
        return cellcount;
    }

    /**
     * Retrieves the data from a specific cell.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number (0-based index).
     * @param colnum The column number (0-based index).
     * @return The data in the specified cell as a String.
     * @throws IOException If an I/O error occurs.
     */
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;

        try {
            data = formatter.formatCellValue(cell); // Format cell value into String
        } catch (Exception e) {
            data = ""; // Return empty string if cell is null or unreadable
        }

        workbook.close();
        fi.close();
        return data;
    }

    /**
     * Sets the data to a specific cell in the sheet.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number (0-based index).
     * @param colnum The column number (0-based index).
     * @param data The data to set in the cell.
     * @throws IOException If an I/O error occurs.
     */
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);

        // If the file doesn't exist, create a new workbook
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        }

        // Open the existing workbook
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // Create the sheet if it doesn't exist
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }

        sheet = workbook.getSheet(sheetName);

        // Create the row if it doesn't exist
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }

        row = sheet.getRow(rownum);

        // Create the cell and set the value
        cell = row.createCell(colnum);
        cell.setCellValue(data);

        // Write back to the Excel file
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    /**
     * Applies green background color to the specified cell.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number (0-based index).
     * @param column The column number (0-based index).
     * @throws IOException If an I/O error occurs.
     */
    public void fillGreenColor(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        // Create and apply green fill style
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        // Write changes back to file
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    /**
     * Applies red background color to the specified cell.
     *
     * @param sheetName The name of the sheet.
     * @param rownum The row number (0-based index).
     * @param column The column number (0-based index).
     * @throws IOException If an I/O error occurs.
     */
    public void fillRedColor(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        // Create and apply red fill style
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        // Write changes back to file
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}
