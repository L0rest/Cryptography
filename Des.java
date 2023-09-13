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

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int j : masterKey) {
            s.append(j);
        }

        return s.toString();
    }

}