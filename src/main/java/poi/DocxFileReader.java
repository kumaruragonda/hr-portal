package poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class DocxFileReader {

    public void readDocxFile() {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("doc/items.docx");
        try {
            XWPFDocument document = new XWPFDocument(inputStream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for(XWPFParagraph paragraph : paragraphs){
                String[] items = paragraph.getParagraphText().split("\\*");
                for(String item: items){
                    System.out.println(item);
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DocxFileReader docxFileReader = new DocxFileReader();
        docxFileReader.readDocxFile();
    }
}
