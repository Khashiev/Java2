package src.ex01;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("invalid args");
        } else {
            String file1 = args[0];
            String file2 = args[1];

            Parser parser = new Parser(file1, file2);
            parser.writeDictionary();

            double similarity = parser.getSimilarity();
            System.out.println("Similarity = " + Math.floor(similarity * 100) / 100);
        }
    }
}
