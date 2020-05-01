import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

class Parser {
    /**
     * Метод принимает на вход строковое представление файла и возвращает HashMap с местом вхождения строки в качестве
     * ключа и самой расшифрованной строкой в качестве значения
     */
    public static HashMap<Integer, String> parse(String input) throws IOException {
        HashMap<Integer, String> parsed = new HashMap<Integer, String>();
        PdfReader rd = new PdfReader(input);
        for (int i = 1; i <= rd.getNumberOfPages(); i++) {
            //PdfDictionary хранит в себе все объекты найденные на странице в формате ключ (PdfName) и значение (PdfObject)
            PdfDictionary pdfDictionary = rd.getPageResources(i);
            //пока в этом методе идёт только пересчёт всех String объектов
            for (int j = 0; j <= pdfDictionary.size(); j++) {
                if (pdfDictionary.contains(PdfName.TEXT)) {
                    for (PdfObject oj : pdfDictionary.getAsArray(PdfName.TEXT)) {
                        parsed.put(j, oj.toString());
                    }
                } else System.out.println("TEXT not found");
            }
        }
        rd.close();
        return parsed;
    }
    public static HashMap<Integer, String> testParse() throws IOException {
        HashMap<Integer, String> parsed = new HashMap<Integer, String>();
        PdfReader rd = new PdfReader("src/test/pages.pdf");
        // печататет в консоль текст, найденый на каждой странице в файле
        for (int i = 1; i <= rd.getNumberOfPages(); i++) {
            System.out.println("Page №" + i + " Text: " + "\n" + PdfTextExtractor.getTextFromPage(rd, i));
            }
            rd.close();
            return parsed;
        }
    /**
     * Метод принимает на вход файл, проверяет, является ли он pdf файлом и возвращает HashMap с местом вхождения строки
     * в качестве ключа и самой расшифрованной строкой в качестве значения
     */
    public static HashMap<Integer, String> parse(File file) throws IOException {
        if (checkPdf(file.getName())) {
            return parse(file.toString());
        } else return null;
    }

    // проверяет, является ли обрабатываемый файл pdf файлом
    private static boolean checkPdf(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        String extension = filename.substring(dotIndex + 1).toLowerCase();
        return extension.equals("pdf");
    }
}
