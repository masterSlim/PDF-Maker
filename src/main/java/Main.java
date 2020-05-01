import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("\test\\pages.pdf");
        HashMap<Integer, String> parsed = Parser.parse(file);
        for(Integer i : parsed.keySet()) {
            System.out.println(i + " " + parsed.get(i));
        }
        }
}
