import org.junit.*;

public class TestDes {

    @Before
    public void setUp() {
        Des des = new Des();
    }

    @Test
    public void TestConversion(){
        Des des = new Des();
        String s1 = "Hello World";
        Assert.assertEquals(s1, des.bitsToString(des.stringToBits(s1)));
        String s2 = "E452gfgrF41,G";
        Assert.assertEquals(s2, des.bitsToString(des.stringToBits(s2)));

        String s4 = "";
        Assert.assertEquals(s4, des.bitsToString(des.stringToBits(s4)));
    }

}
