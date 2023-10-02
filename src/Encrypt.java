import java.util.*;

public class Encrypt {
    private static int key;

    private static String text;

    private static String layout;

    protected static String text_2;

    private static Character[] ALPHABET;

    private static final Character[] ALPHABET_RU = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private static final Character[] ALPHABET_EN = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public Encrypt(String layout, String text, int key) {
        assignment(layout, text, key);
        Character[] offset = keyOffset(getKey());
        text_2 = cipher(getText(), offset);
    }

    static Character[] keyOffset(int key) {
        var array = new ArrayList<Character>(Arrays.asList(language()));
        Collections.rotate(array, key);
        return array.toArray(new Character[0]);
    }

    static String cipher(String text, Character[] offset) {
        Character[] array = toCharacterArray(text.toCharArray());
        int[] indexArray = getIndexArray(array);
        for (int i = 0; i < indexArray.length; i++) {
            for (int j = 0; j < offset.length; j++) {
                if (indexArray[i] == j) {
                    array[i] = offset[j];
                    break;
                }
            }
        }

        return toStringChar(array);
    }

    public static Character[] toCharacterArray(char[] array) {
        Character[] textArray = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            textArray[i] = array[i];
        }
        return textArray;
    }

    public static int[] getIndexArray(Character[] array) {
        int[] arrayI = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < language().length; j++) {
                if (array[i].equals(language()[j])) {
                    arrayI[i] = j;
                }
            }
        }
        return arrayI;
    }

    public static String toStringChar(Character[] array) {
        StringBuilder text = new StringBuilder();
        for (Character character : array) {
            text.append(character);
        }
        return text.toString();
    }

    public static Character[] language() {
        if (getLayout().equals("RU")) {
            ALPHABET = ALPHABET_RU;
        } else if (getLayout().equals("EN")) {
            ALPHABET = ALPHABET_EN;
        }
        return ALPHABET;
    }

    public static void assignment(String layout, String text, int key) {
        setLayout(layout);
        setText(text);
        setKey(key);
    }

    public static void setLayout(String layout) {
        Encrypt.layout = layout;
    }
    public static void setText(String text) {
        Encrypt.text = text;
    }
    public static void setKey(int key) {
        Encrypt.key = key;
    }
    public static String getText() {
        return text;
    }
    public static int getKey() {
        return key;
    }
    public static String getLayout() {
        return Encrypt.layout;
    }
}
