import java.util.Arrays;

public class TestDes {

    public static void main(String[] args) {
        Des des = new Des();

        System.out.println(des);

        int[] test = des.generePermutation(16);

        System.out.println("test : " + Arrays.toString(test));
        System.out.println("decoupage : " + Arrays.deepToString(des.decoupage(test, 4)));
        System.out.println("recollage : " + Arrays.toString(des.recollage_bloc(des.decoupage(test, 4))));
        System.out.println("decale : " + Arrays.toString(des.decale_gauche(test, 4)));

        System.out.println("fonction S : " + Arrays.toString(des.fonction_S(new int[]{1, 0, 0, 0, 0, 1})));

        for (int i = 0; i < 16; i++) {
            des.genereCle(i);
        }

        for (int[] cle : des.tab_cles) {
            System.out.println(Arrays.toString(cle));
        }
    }
}
