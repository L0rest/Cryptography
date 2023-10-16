import org.junit.*;

import java.util.Arrays;

public class TestDes {

    @Test
    public void TestConversion(){
        Des des = new Des();
        String s1 = "Hello World";
        Assert.assertEquals(s1, des.bitsToString(des.stringToBits(s1)));
        String s2 = "E452gfgrF41,G";
        Assert.assertEquals(s2, des.bitsToString(des.stringToBits(s2)));
        String s3 = "a@é_çèà";
        Assert.assertEquals(s3, des.bitsToString(des.stringToBits(s3)));
    }

    @Test
    public void TestPermutation(){
        Des des = new Des();

        int[] bloc1 = {1,2,3,4,5,6,7,8};
        int[] permutation1 = {2,4,6,0,1,3,5,7};
        Assert.assertEquals(Arrays.toString(new int[] {3,5,7,1,2,4,6,8}), Arrays.toString(des.permutation(permutation1, bloc1)));

        int[] bloc2 = {1,2,3,4,5,6,7,8};

    }

}
