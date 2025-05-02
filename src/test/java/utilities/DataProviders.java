package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

/**
 * This class provides data for TestNG test methods using the @DataProvider annotation.
 * It reads login data from an Excel file and supplies it as a 2D array.
 */
public class DataProviders {

    /**
     * DataProvider method to supply login data for test methods.
     * 
     * @return A 2D array of Strings containing login data from the Excel file.
     * @throws IOException If there is an issue reading the Excel file.
     */
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Path to the Excel file located in the testData directory
        String path = ".\\testData\\DataFile.xlsx";

        // Creating an instance of ExcelUtility to read Excel data
        ExcelUtility xlutil = new ExcelUtility(path);

        // Getting number of rows and columns from Sheet1
        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        // Creating a 2D array to store the login data
        String[][] loginData = new String[totalRows][totalCols];

        // Reading data from Excel file into the 2D array
        for (int i = 1; i <= totalRows; i++) { // i starts from 1 (assuming row 0 is header)
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // i-1 to store in array index
            }
        }

        // Returning the populated login data array
        return loginData;
    }
}
