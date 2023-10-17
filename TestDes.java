import org.junit.*;

import java.util.Arrays;
import java.util.Random;

public class TestDes {

    @Test
    public void TestConversion() {
        Des des = new Des();

        String s1 = "Hello World";
        Assert.assertEquals(s1, des.bitsToString(des.stringToBits(s1)));

        String s2 = "E452gfgrF41,G";
        Assert.assertEquals(s2, des.bitsToString(des.stringToBits(s2)));

        String s3 = "a@é_çèà";
        Assert.assertEquals(s3, des.bitsToString(des.stringToBits(s3)));
    }

    @Test
    public void TestPermutation() {
        Des des = new Des();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation1 = {2, 4, 6, 0, 1, 3, 5, 7};
        Assert.assertEquals(Arrays.toString(new int[]{3, 5, 7, 1, 2, 4, 6, 8}), Arrays.toString(des.permutation(permutation1, bloc1)));

    }

    @Test
    public void TestDecaleGauche() {
        Des des = new Des();
        Random rd = new Random();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertEquals(Arrays.toString(new int[]{2, 3, 4, 5, 6, 7, 8, 1}), Arrays.toString(des.decale_gauche(bloc1, 1)));

        int[] bloc2 = new int[10]; // storing random integers in the array
        for (int i = 0; i < bloc2.length; i++) {
            bloc2[i] = rd.nextInt(20);
        }
        Assert.assertEquals(Arrays.toString(bloc2), Arrays.toString(des.decale_gauche(bloc2, bloc2.length))); // si on effectue n décalage à gauche dans un tableau de taille n, on retrouve le même tableau

    }

    @Test
    public void TestXor() {
        Des des = new Des();

        int[] bloc1 = {0, 0, 0, 0, 0, 0, 0, 0};
        int[] bloc2 = {1, 1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(Arrays.toString(new int[]{1, 1, 1, 1, 1, 1, 1, 1}), Arrays.toString(des.xor(bloc1, bloc2)));

        int[] bloc3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] bloc4 = {1, 1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(Arrays.toString(new int[]{0, 0, 0, 0, 0, 0, 0, 0}), Arrays.toString(des.xor(bloc3, bloc4)));

        int[] bloc5 = {1, 0, 1, 1, 0, 1, 1, 0};
        int[] bloc6 = {0, 1, 1, 0, 1, 0, 1, 1};
        Assert.assertEquals(Arrays.toString(new int[]{1, 1, 0, 1, 1, 1, 0, 1}), Arrays.toString(des.xor(bloc5, bloc6)));
    }

}
