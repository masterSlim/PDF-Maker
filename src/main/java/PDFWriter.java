import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFWriter implements Writer {
    private final Document doc;
    private final String filePath;

    public PDFWriter(String filePath) {
        doc = new Document();
        this.filePath = filePath + "/AutoGenerated.pdf";
    }

    public PDFWriter() {
        doc = new Document();
        this.filePath = "src/test/AutoGenerated.pdf";
    }

    public boolean write(IncomingInfo info) throws FileNotFoundException, DocumentException {
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();
            doc.addTitle(info.getName() + " "+ info.getSurname());
            doc.addSubject(info.getPosition());
            doc.close();
            System.out.println("Succes writed to " + filePath);
            return true;
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } finally {
            doc.close();
        }
        return false;
    }
    public String getFilePath(){
        return filePath;
    }
}
