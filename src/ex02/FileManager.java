package src.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class FileManager {
    private Path directory;

    public FileManager(String path) throws IOException {
        directory = Paths.get(path);

        if (!Files.isDirectory(directory)) {
            throw new IOException("Not a directory: " + path);
        }
    }

    public void run() {
        System.out.println(directory);
        Scanner in = new Scanner(System.in);

        String commandLine;
        while (!(commandLine = in.nextLine()).equals("exit")) {
            String[] commands = commandLine.split(" ");

            if (commands.length == 1 && commands[0].equals("ls")) {
                lsCommand();
            } else if (commands.length == 2 && commands[0].equals("cd")) {
                cdCommand(commands[1]);
            } else if (commands.length == 3 && commands[0].equals("mv")) {
                mvCommand(commands[1], commands[2]);
            } else {
                System.out.println(commandLine + ": command not found");
            }
        }
    }

    private void lsCommand() {
        File[] files = new File(directory.toString()).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName() + " " + file.length() / 1000 + " KB");
                } else {
                    System.out.println(file.getName() + " " + folderSize(file) / 1000 + " KB");
                }
            }
        }
    }

    private long folderSize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += folderSize(file);
                }
            }
        }

        return size;
    }

    private void cdCommand(String path) {
        Path newDirectory = directory.resolve(path);

        if (!new File(newDirectory.toString()).isDirectory()) {
            System.err.println(path + ": No such file or directory");
        } else {
            directory = newDirectory.normalize();
        }

        System.out.println(directory);
    }

    private void mvCommand(String from, String to) {
        Path pathFrom = directory.resolve(from);
        Path pathTo = directory.resolve(to);

        try {
            if (Files.isDirectory(pathTo)) {
                Files.move(pathFrom, pathTo.resolve(pathFrom.getFileName()),
                        StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.move(pathFrom, pathTo.resolveSibling(to));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
