package src.ex00;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParseSignatures {
    private static final String PATH_OF_SIGNATURES = "src/ex00/signatures.txt";

    public static Map<String, String> parse() {
        Map<String, String> signatures = new HashMap<>();

        try (FileInputStream fin = new FileInputStream(PATH_OF_SIGNATURES)) {
            Scanner in = new Scanner(fin);

            while (in.hasNextLine()) {
                String format = in.next().replace(',', ' ').trim();
                String byteCode = in.nextLine().trim();

                signatures.put(format, byteCode);
            }

            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return signatures;
    }
}
