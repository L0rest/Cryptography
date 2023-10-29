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

        // create a string containing russians, chinese and arabic characters
        String s5 = "Привет, это текст с русскими, китайскими и арабскими символами: 你好，这是一个包含俄语，中文和阿拉伯语字符的文本：مرحبا ، هذا هو نص يحتوي على أحرف روسية وصينية وعربية: ";
        String result5 = des.bitsToString(des.stringToBits(s5));
        Assert.assertEquals(s5, result5);

    }

    @Test
    public void TestPermutation() {
        Des des = new Des();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation1 = {2, 4, 6, 0, 1, 3, 5, 7};
        int[] result1 = des.permutation(permutation1, bloc1);
        int[] expected1 = {3, 5, 7, 1, 2, 4, 6, 8};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

        int[] bloc2 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation2 = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] result2 = des.permutation(permutation2, bloc2);
        // if the permutation is the identity, the array doesn't change
        Assert.assertEquals(Arrays.toString(bloc2), Arrays.toString(result2));

        int[] bloc3 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation3 = {5, 7, 2, 1, 6, 3, 0, 4};
        int[] result3 = des.invPermutation(permutation3, des.permutation(permutation3, bloc3));
        // if we make a permutation and then the inverse permutation, the array doesn't change
        Assert.assertEquals(Arrays.toString(bloc3), Arrays.toString(result3));

        int[] bloc4 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation4 = {5, 1, 3, 2, 4, 0, 7, 6};
        int[] result4 = des.permutation(permutation4, bloc4);
        int[] expected4 = {6, 2, 4, 3, 5, 1, 8, 7};
        Assert.assertEquals(Arrays.toString(expected4), Arrays.toString(result4));

        int[] bloc5 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation5 = {5, 2, 4, 1};
        int[] result5 = des.permutation(permutation5, bloc5);
        int[] expected5 = {6, 3, 5, 2};
        // if the permutation is shorter than the array, some values are not used in the permutation and thus not in the result
        Assert.assertEquals(Arrays.toString(expected5), Arrays.toString(result5));

        int[] bloc6 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation6 = {5, 5, 5, 5, 5, 5, 5};
        int[] result6 = des.permutation(permutation6, bloc6);
        int[] expected6 = {6, 6, 6, 6, 6, 6, 6};
        // it's possible to have the same index multiple times in the permutation and thus to have multiple times the same value in the result
        Assert.assertEquals(Arrays.toString(expected6), Arrays.toString(result6));

        int[] bloc7 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] permutation7 = {5, 2, 4, 1, 3, 0, 7, 6};
        int[] result7 = des.invPermutation(permutation7, bloc7);
        int[] expected7 = {6, 4, 2, 5, 3, 1, 8, 7};
        Assert.assertEquals(Arrays.toString(expected7), Arrays.toString(result7));

    }

    @Test(expected = IllegalArgumentException.class)
    public void TestDecoupageRecollage() {
        Des des = new Des();
        Random rd = new Random();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[][] result1 = des.decoupage(bloc1, 4);
        int[][] expected1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Assert.assertEquals(Arrays.deepToString(expected1), Arrays.deepToString(result1));

        int[] bloc2 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[][] result2 = des.decoupage(bloc2, 1);
        int[][] expected2 = {{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}};
        Assert.assertEquals(Arrays.deepToString(expected2), Arrays.deepToString(result2));

        int[] bloc3 = new int[10]; // storing random integers in the array
        for (int i = 0; i < bloc3.length; i++) {
            bloc3[i] = rd.nextInt(20);
        }
        int[] result3 = des.recollage_bloc(des.decoupage(bloc3, 5));
        // Merge the subarrays into one array is the same as the original array
        Assert.assertEquals(Arrays.toString(bloc3), Arrays.toString(result3));

        int[] bloc4 = {};
        des.decoupage(bloc4, 4); // throws IllegalArgumentException if the array is empty

        int[] bloc5 = {1, 2, 3, 4, 5, 6};
        des.decoupage(bloc5, 4); // throws IllegalArgumentException if the array can't be splited into 'tailleBlocs' length subarrays

        int[][] bloc6 = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        int[] result6 = des.recollage_bloc(bloc6);
        int[] expected6 = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertEquals(Arrays.toString(expected6), Arrays.toString(result6));

        int[][] bloc7 = {{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}};
        int[] result7 = des.recollage_bloc(bloc7);
        int[] expected7 = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertEquals(Arrays.toString(expected7), Arrays.toString(result7));

        int[][] bloc8 = {{}, {}, {}};
        int[] result8 = des.recollage_bloc(bloc8);
        int[] expected8 = {};
        Assert.assertEquals(Arrays.toString(expected8), Arrays.toString(result8));

        int[][] bloc9 = {};
        des.recollage_bloc(bloc9); // throws IllegalArgumentException if the array is empty

    }

    @Test
    public void TestDecaleGauche() {
        Des des = new Des();
        Random rd = new Random();

        int[] bloc1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result1 = des.decale_gauche(bloc1, 1);
        int[] expected1 = {2, 3, 4, 5, 6, 7, 8, 1};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

        int[] bloc2 = new int[10]; // storing random integers in the array
        for (int i = 0; i < bloc2.length; i++) {
            bloc2[i] = rd.nextInt(20);
        }
        int[] result2 = des.decale_gauche(bloc2, bloc2.length);
        // if nbCran = length of the array, the array doesn't change
        Assert.assertEquals(Arrays.toString(bloc2), Arrays.toString(result2));

        int[] bloc3 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result3 = des.decale_gauche(bloc3, 0);
        // if nbCran = 0, the array doesn't change
        Assert.assertEquals(Arrays.toString(bloc3), Arrays.toString(result3));

        int[] bloc4 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result4 = des.decale_gauche(bloc4, 3);
        int[] expected4 = {4, 5, 6, 7, 8, 1, 2, 3};
        Assert.assertEquals(Arrays.toString(expected4), Arrays.toString(result4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestXor() {
        Des des = new Des();

        int[] bloc1 = {0, 0, 0, 0, 0, 0, 0, 0};
        int[] bloc2 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] result1 = des.xor(bloc1, bloc2);
        int[] expected1 = {1, 1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

        int[] bloc3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] bloc4 = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] result2 = des.xor(bloc3, bloc4);
        int[] expected2 = {0, 0, 0, 0, 0, 0, 0, 0};
        Assert.assertEquals(Arrays.toString(expected2), Arrays.toString(result2));

        int[] bloc5 = {1, 0, 1, 1, 0, 1, 1, 0};
        int[] bloc6 = {1, 1, 0, 1, 1, 1, 0, 1};
        int[] result3 = des.xor(bloc5, bloc6);
        int[] expected3 = {0, 1, 1, 0, 1, 0, 1, 1};
        Assert.assertEquals(Arrays.toString(expected3), Arrays.toString(result3));

        int[] bloc7 = {1, 0, 1, 1, 0, 1, 1, 0};
        int[] bloc8 = {0, 1, 1, 0, 1, 0, 1, 1, 1};
        des.xor(bloc7, bloc8); // throws IllegalArgumentException if the arrays don't have the same length
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestFonctionS() {
        Des des = new Des();

        int[] bloc1 = {0, 0, 0, 0, 0, 0};
        int noRound1 = 1;
        int[] result1 = des.fonction_S(bloc1, noRound1);
        int[] expected1 = {1, 1, 1, 1};
        Assert.assertEquals(Arrays.toString(expected1), Arrays.toString(result1));

        int[] bloc2 = {1, 1, 1, 1, 1, 1};
        int noRound2 = 6;
        int[] result2 = des.fonction_S(bloc2, noRound2);
        int[] expected2 = {1, 1, 0, 0};
        Assert.assertEquals(Arrays.toString(expected2), Arrays.toString(result2));

        int[] bloc3 = {1, 0, 1, 1, 0, 1};
        int noRound3 = 5;
        int[] result3 = des.fonction_S(bloc3, noRound3);
        int[] expected3 = {1, 1, 1, 1};
        Assert.assertEquals(Arrays.toString(expected3), Arrays.toString(result3));

        int[] bloc4 = {0, 1, 1, 0, 1, 0};
        int noRound4 = 3;
        int[] result4 = des.fonction_S(bloc4, noRound4);
        int[] expected4 = {1, 1, 0, 0};
        Assert.assertEquals(Arrays.toString(expected4), Arrays.toString(result4));

        int[] bloc5 = {1, 0, 1, 1, 0};
        des.fonction_S(bloc5, 1); // throws IllegalArgumentException if the array length is not 6

    }

    @Test(expected = IllegalArgumentException.class)
    public void TestCrypteDecrypte() {
        Des des = new Des();

        String s1 = "Hello World";
        String result1 = des.decrypte(des.crypte(s1));
        Assert.assertEquals(s1, result1);

        String s2 = "E45!2gf?grF41,G";
        String result2 = des.decrypte(des.crypte(s2));
        Assert.assertEquals(s2, result2);

        String s3 = "a@é_çèà";
        String result3 = des.decrypte(des.crypte(s3));
        Assert.assertEquals(s3, result3);

        String s4 = "Bonjour, ceci est un texte avec des caractères spéciaux : \n éèàçù!@#$%^&*()_+-=,;:/?<>|\\{}[]";
        String result4 = des.decrypte(des.crypte(s4));
        Assert.assertEquals(s4, result4);

        String s5 = "";
        des.crypte(s5); // throws IllegalArgumentException if the string is empty

        int[] s6 = {};
        des.decrypte(s6); // throws IllegalArgumentException if the array is empty

        String s7 = "Привет, это текст с русскими, китайскими и арабскими символами: 你好，这是一个包含俄语，中文和阿拉伯语字符的文本：مرحبا ، هذا هو نص يحتوي على أحرف روسية وصينية وعربية: ";
        String result7 = des.decrypte(des.crypte(s7));
        Assert.assertEquals(s7, result7);
    }
}
