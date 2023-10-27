import java.util.Arrays;

public class TripleDes {

    Des des1, des2, des3;

    /**
     * Create a new TripleDes object containing 3 Des objects
     */
    public TripleDes() {
        this.des1 = new Des();
        this.des2 = new Des();
        this.des3 = new Des();
    }

    /**
     * Encrypt a message with the triple DES algorithm
     * @param message the message to encrypt
     * @return the encrypted message
     */
    public int[] tripleCrypte(String message) {
        int[] res1bits = this.des1.crypte(message);
        String res1 = Arrays.toString(res1bits);

        int[] res2bits = this.des2.crypte(res1);
        String res2 = Arrays.toString(res2bits);

        return this.des3.crypte(res2);

    }

    /**
     * Decrypt a message with the triple DES algorithm
     * @param message the message to decrypt
     * @return the decrypted message
     */
    public String tripleDecrypte(int[] message) {
        String res1string = this.des3.decrypte(message);
        int[] res1 = Arrays.stream(res1string.substring(1, res1string.length() - 1).split(", ")).mapToInt(Integer::parseInt).toArray();

        String res2string = this.des2.decrypte(res1);
        int[] res2 = Arrays.stream(res2string.substring(1, res2string.length() - 1).split(", ")).mapToInt(Integer::parseInt).toArray();

        return this.des1.decrypte(res2);
    }

}
