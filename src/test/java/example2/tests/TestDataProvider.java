package example2.tests;

import org.testng.annotations.DataProvider;
import all.utils.ExcelUtils;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "excelData")
    public Object[][] getData() {
        List<List<String>> excelData = ExcelUtils.readExcelFile("src/test/resources/test_data.xlsx");

        System.out.println("🔹 Data from Excel:");
        for (List<String> row : excelData) {
            System.out.println("Row: " + row);
        }

        Object[][] data = new Object[excelData.size()][2];  // 2 עמודות בלבד: email, password
        for (int i = 0; i < excelData.size(); i++) {
            data[i][0] = excelData.get(i).get(0); // email
            data[i][1] = excelData.get(i).get(1); // password
        }
        return data;
    }

}
