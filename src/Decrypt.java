public class Decrypt {

    private static String layout;;
    private static String text;

    private static int key;

    public Decrypt(String layout, String text, int key) {
        Decrypt.layout = layout;
        Decrypt.text = text;
        Decrypt.key = key * -1;
        new Encrypt(Decrypt.layout, Decrypt.text, Decrypt.key);
    }


}
