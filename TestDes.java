import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class TestDes {

    @Test(expected = IllegalArgumentException.class)
    public void TestConversion() {
        Des des = new Des();

        String s1 = "Hello World";
        String result1 = des.bitsToString(des.stringToBits(s1));
        Assert.assertEquals(s1, result1);

        String s2 = "E45!2gf?grF41,G";
        String result2 = des.bitsToString(des.stringToBits(s2));
        Assert.assertEquals(s2, result2);

        String s3 = "a@é_çèà";
        String result3 = des.bitsToString(des.stringToBits(s3));
        Assert.assertEquals(s3, result3);

        String s4 = "";
        des.stringToBits(s4); // throws IllegalArgumentException if the string is empty

        int[] tab = new int[0];
        des.bitsToString(tab); // throws IllegalArgumentException if the array is empty
    }

    @Test
    public void TestPermutation() {
        Des des = new Des();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation1 = {2, 4, 6, 0, 1, 3, 5, 7};
        int[] result1 = des.permutation(permutation1, bloc1);
        int[] expected1 = new int[]{3, 5, 7, 1, 2, 4, 6, 8};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

    }

    @Test(expected = IllegalArgumentException.class)
    public void TestCoupageDecoupage() {
        Des des = new Des();
        Random rd = new Random();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[][] result1 = des.decoupage(bloc1, 4);
        int[][] expected1 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Assert.assertEquals(Arrays.deepToString(expected1), Arrays.deepToString(result1));

        int[] bloc4 = new int[10]; // storing random integers in the array
        for (int i = 0; i < bloc4.length; i++) {
            bloc4[i] = rd.nextInt(20);
        }
        // A bloc is equals to the merge of its slices
        Assert.assertEquals(Arrays.toString(bloc4), Arrays.toString(des.recollage_bloc(des.decoupage(bloc4, 5))));

        int[] bloc5 = {1, 2, 3, 4, 5, 6};
        des.decoupage(bloc5, 4); // throws IllegalArgumentException if the array can't be splited into 'tailleBlocs' length subarrays
    }

    @Test
    public void TestDecaleGauche() {
        Des des = new Des();
        Random rd = new Random();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result1 = des.decale_gauche(bloc1, 1);
        int[] expected1 = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

        int[] bloc2 = new int[10]; // storing random integers in the array
        for (int i = 0; i < bloc2.length; i++) {
            bloc2[i] = rd.nextInt(20);
        }
        int[] result2 = des.decale_gauche(bloc2, bloc2.length);
        // if nbCran = length of the array, the array doesn't change
        Assert.assertEquals(Arrays.toString(bloc2), Arrays.toString(result2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestXor() {
        Des des = new Des();

        int[] bloc1 = {0, 0, 0, 0, 0, 0, 0, 0};
        int[] bloc2 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] result1 = des.xor(bloc1, bloc2);
        int[] expected1 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

        int[] bloc3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] bloc4 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] result2 = des.xor(bloc3, bloc4);
        int[] expected2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        Assert.assertEquals(Arrays.toString(expected2), Arrays.toString(result2));

        int[] bloc5 = {1, 0, 1, 1, 0, 1, 1, 0};
        int[] bloc6 = {1, 1, 0, 1, 1, 1, 0, 1};
        int[] result3 = des.xor(bloc5, bloc6);
        int[] expected3 = new int[]{0, 1, 1, 0, 1, 0, 1, 1};
        Assert.assertEquals(Arrays.toString(expected3), Arrays.toString(result3));

        int[] bloc7 = {1, 0, 1, 1, 0, 1, 1, 0};
        int[] bloc8 = {0, 1, 1, 0, 1, 0, 1, 1, 1};
        des.xor(bloc7, bloc8); // throws IllegalArgumentException if the arrays don't have the same length
    }

    @Test
    public void TestFonctionS() {

    }

    @Test
    public void TestCrypteDecrypte() {

    }
}
