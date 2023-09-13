import java.math.BigInteger;
import java.util.Random;

public class Des {

    public int[] masterKey;
    public int[] tab_cles;
    public Random rand = new Random();

    public Des() {
        this.masterKey = new int[64];

        for (int i = 0; i < 64; i++) {
            this.masterKey[i] = rand.nextInt(2);
        }
    }

    public int[] stringToBits(String message) {
        String bits = new BigInteger(message.getBytes()).toString(2);

        int[] block = new int[bits.length()];

        for (int i = 0; i < bits.length(); i++) {
            block[i] = Integer.parseInt(String.valueOf(bits.charAt(i)));
        }

        return block;
    }

    public String bitsToString(int[] block) {
        StringBuilder s = new StringBuilder();

        for (int j : block) {
            s.append(j);
        }

        return new String(new BigInteger(s.toString(), 2).toByteArray());
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int j : masterKey) {
            s.append(j);
        }

        return s.toString();
    }

}