package logic;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface Writer {
    public  boolean write(Information info) throws FileNotFoundException, DocumentException;
}
