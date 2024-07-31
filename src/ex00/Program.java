package src.ex00;

import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Map<String, String> signatures = ParseSignatures.parse();
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        while (!filePath.equals("42")) {
            CheckSignatures.check(signatures, filePath);
            filePath = in.nextLine();
        }
    }
}
