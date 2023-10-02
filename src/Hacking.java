import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Hacking {

    private static int max = Integer.MIN_VALUE;

    private static int j = 0;

    public Hacking(String layout, String text) {
        enumeration(layout, text);
    }

    public static void enumeration(String layout, String text) {
        new Encrypt(layout, text, 0);
        int n = Encrypt.language().length;
        for (int i = 1; i < n; i++) {
            new Decrypt(layout, text, i);
            maxSpaces(i);
            if(hackedText()) {
                return;
            }
        }
        new Decrypt(layout, text, j);
    }

    private static int countOccurrences(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == ' ') {
                counter++;
            }
        }
        return counter;
    }

    public static int maxSpaces(int i) {
        if (max < countOccurrences(Encrypt.text_2)) {
            j = i;
            max = countOccurrences(Encrypt.text_2);
        }
        return j;
    }

    public static boolean hackedText() {
        String[] lineText = Encrypt.text_2.split("., «»\"\'!?;:");
        try {
            File file = new File("list_of_words.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            for (int i = 0; i < lineText.length; i++) {
                while (line != null) {
                    if (lineText[i].equalsIgnoreCase(line)) {
                        return true;
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
