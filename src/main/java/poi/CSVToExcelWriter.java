package poi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

public class CSVToExcelWriter {

    public void writeCsvToExcel() {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("poi/demoCsv.csv");
        try {
            String csvFileAddress = "F:\\training\\hr-portal\\src\\main\\resources\\poi\\demoCsv.csv"; //csv file address
            String xlsxFileAddress = "F:\\training\\hr-portal\\src\\main\\resources\\poi\\demoExcel.xlsx"; //xlsx file address
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine=null;
            int RowNum=0;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow=sheet.createRow(RowNum);
                for(int i=0;i<str.length;i++){
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage()+"Exception in try");
        }
    }
    public static void main(String[] args) {
        CSVToExcelWriter docxFileReader = new CSVToExcelWriter();
        docxFileReader.writeCsvToExcel();
    }
}
