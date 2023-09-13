import java.util.Arrays;

public class TestDes {

    public static void main(String[] args) {
        Des des = new Des();

        System.out.println(Arrays.toString(des.stringToBits("Hello World")));

        System.out.println(des.bitsToString(des.stringToBits("Hello World")));
    }
}
