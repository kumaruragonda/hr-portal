package jasperreports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;

import java.io.*;
import java.util.*;

public class JasperExcel {

    public static void main(String args[]) throws IOException, JRException {
        generateExcel();
     //   xls();

    }

    public  static void generateExcel() throws JRException, IOException {
        JasperReport jasperReport;
        try (InputStream inputStream = JRLoader.getResourceInputStream("csv/ImpressaoVenda.jrxml")) {
            jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));
        }
        Map<String, Object> params = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);


        File outputFile = new File("output.xlsx");
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            Exporter exporter = new JRXlsxExporter();
            exporter.setExporterInput( new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void fill() throws JRException
    {
        long start = System.currentTimeMillis();
        InputStream inputStream = JRLoader.getResourceInputStream("csv/sample.jasper");
        JasperReport jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));
        JasperFillManager.fillReportToFile(jasperReport, null, (Map<String, Object>) new JREmptyDataSource());
        System.err.println("Filling time : " + (System.currentTimeMillis() - start));
    }



    public static void xls() throws JRException
    {
        long start = System.currentTimeMillis();
        File sourceFile = new File("csv/sample.jrprint");

        JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");

        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
    }


    /**
     *
     */
    public void csv() throws JRException
    {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/UnicodeReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");

        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile, "UTF-8"));

        exporter.exportReport();

        System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
    }



}
