import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/test/pages.pdf");
        HashMap<Integer, String> parsed = Parser.testParse();
        for(Integer i : parsed.keySet()) {
            System.out.println(i + " " + parsed.get(i));
        }
        }
}
