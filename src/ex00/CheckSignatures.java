package src.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CheckSignatures {
    public static void check(Map<String, String> signatures, String filePath) {
        try (FileInputStream fin = new FileInputStream(filePath)) {
            ArrayList<String> listSignature = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                String hexString = Integer.toHexString(fin.read()).toUpperCase();
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }

                listSignature.add(hexString);
            }

            String stringSignature = String.join(" ", listSignature);
            writeRes(signatures, stringSignature);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void writeRes(Map<String, String> signatures, String stringSignature) {
        try (FileOutputStream fos = new FileOutputStream("src/ex00/result.txt", true)) {
            for (Map.Entry<String, String> entry : signatures.entrySet()) {
                if (stringSignature.contains(entry.getValue())) {
                    fos.write(entry.getKey().getBytes());
                    fos.write('\n');

                    System.out.println("PROCESSED");
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
