package src.ex02;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        String prefix = "--current-folder=";

        if (args.length != 1 || !args[0].startsWith(prefix)) {
            System.err.println("Invalid directory name");
            return;
        }

        String directory = args[0].substring(prefix.length());
        try {
            FileManager fileManager = new FileManager(directory);
            fileManager.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
