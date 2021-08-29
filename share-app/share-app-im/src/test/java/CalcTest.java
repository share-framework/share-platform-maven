import java.util.HashMap;
import java.util.Map;

public class CalcTest {
    public static void main(String[] args) {
        int c = 5;
        int b = 0;
        int a = 0;
        while (a <= c) {
            double bb = (int)Math.sqrt(c - a*a);
            b = (int) bb;
            if (b == bb) {
                System.err.println("存在" + a + "==" + b);
                break;
            } else {
                System.err.println("不存在");
            }
            a++;
        }

    }
}
