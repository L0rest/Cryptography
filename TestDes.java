import java.util.Arrays;

public class TestDes {

    public static void main(String[] args) {
        Des des = new Des();

        System.out.println("Message: 123456ABCD132536");

        int[] msg_code = des.crypte("123456ABCD132536");
        System.out.println("Code: " + Arrays.toString(msg_code));

        System.out.println(des.bitsToString(msg_code));

        System.out.println("Message: " + des.decrypte(msg_code));

    }
}
