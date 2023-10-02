import java.io.*;
import java.util.Scanner;

public class Realization {

    private static String layout;

    private static String inpOutScanner;

    private static String exit = "";

    private static Scanner scanner = new Scanner(System.in);

    private static Scanner console = new Scanner(System.in);

    private static int key;

    public Realization() {
        choiceEncDecHac();
    }

    public static String languageSelection() {
        System.out.println("Выберите раскладку: (1)RU или (2)EN");
        while (true) {
            layout = scanner.nextLine();
            if (layout.equalsIgnoreCase("RU") ||
                    layout.equalsIgnoreCase("РУ") ||
                    layout.equals("1")) {
                layout = "RU";
                return layout;
            } else if (layout.equalsIgnoreCase("EN") ||
                    layout.equalsIgnoreCase("ЕН") ||
                    layout.equals("2")) {
                layout = "EN";
                return layout;
            } else
                System.out.println("Вы написали " + layout + ". Укажите: (1)RU или (2)EN");
        }
    }

    public static void choiceEncDecHac() {
        while (!(exit.equalsIgnoreCase("EXIT") || exit.equalsIgnoreCase("ВЫХОД"))) {
            System.out.println("Желаете (1)ЗАШИФРОВАТЬ, (2)ДЕШИФРОВАТЬ или (3)ВЗЛОМАТЬ?");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("зашифровать") || choice.equals("1")) {
                new Encrypt(languageSelection(), choiceInpOutScanner(), key());
                textOutput(Encrypt.text_2);
                print();
            } else if (choice.equalsIgnoreCase("дешифровать") || choice.equals("2")) {
                new Decrypt(languageSelection(), choiceInpOutScanner(), key());
                textOutput(Encrypt.text_2);
                print();
            } else if (choice.equalsIgnoreCase("взломать") || choice.equals("3")) {
                new Hacking(languageSelection(), choiceInpOutScanner());
                textOutput(Encrypt.text_2);
                print();
            } else
                System.out.println("Вы написали " + choice + ". Укажите: зашифровать, дешифровать или взломать");
            exitProg();
        }
    }

    public static String choiceInpOutScanner() {
        System.out.println("Текс будет считан с (1)ФАЙЛА или (2)КЛАВИАТУРЫ?\n Укажите: файл или клавиатура" );
        while (true) {
            inpOutScanner = scanner.nextLine();
            if (inpOutScanner.equalsIgnoreCase("файл") || inpOutScanner.equals("1")) {
                return textInput();
            } else if (inpOutScanner.equalsIgnoreCase("клавиатура") || inpOutScanner.equals("2")) {
                return textScanner();
            } else
                System.out.println("Вы написали " + inpOutScanner + ". Укажите: файл или клавиатура");
        }
    }

    public static int key() {
        System.out.println("Укажите сдвиг(ключ)");
        return console.nextInt();
    }

    public static String textInput() {
        System.out.println("Укажите путь к файлу");
        inpOutScanner = "";
        String way = scanner.nextLine();
        while (true) {
            try {
                File file = new File(way);
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    inpOutScanner += line;
                    line = reader.readLine();
                }
                return inpOutScanner.toLowerCase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void textOutput(String text) {
        System.out.println("Укажите путь к файлу, куда сохраним текст");
        String way = scanner.nextLine();
        try {
            File file = new File(way);
            FileWriter fr = new FileWriter(file);
            fr.write(text);
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String textScanner() {
        System.out.println("Напишите текст, который желаете зашифровать, дешифровать или взломать.");
        inpOutScanner = scanner.nextLine();
        System.out.println("Вы ввели \" " + inpOutScanner + " \". Если текст введен верно, то напишите (1)ДА, в ином случае (2)НЕТ");
        String value = scanner.nextLine();
        while (value.equalsIgnoreCase("нет") || value.equals("2")) {
            System.out.println("Введите текст: ");
            inpOutScanner = scanner.nextLine();
            System.out.println("Текст \" " + inpOutScanner + " \" введен верно?");
            value = scanner.nextLine();
        }
        return inpOutScanner.toLowerCase();
    }

    public static void exitProg() {
        System.out.println("Для выхода из программы напишите: ВЫХОД или EXIT. \nВ ином случае нажмите Enter.");
        exit = scanner.nextLine();
    }

    public static void print() {
        System.out.println("Ваш текст:\n" + Encrypt.text_2 + "\n");
    }
}
