package logic;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface Writer {
    public boolean write(IncomingInfo info) throws FileNotFoundException, DocumentException;
}
