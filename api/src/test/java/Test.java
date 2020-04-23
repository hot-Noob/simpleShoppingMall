/**
 * @ClassName Test
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 11:05
 * @Version 1.0
 **/
import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        float d1 = 2.15f;
        float d2 = 1.10f;
        System.out.println("float类型运算结果：\n" + d1 + "-" + d2 + "=" + (d1 -d2) + "\n");

        double d3 = 2.15;
        double d4 = 1.10;
        System.out.println("double类型运算结果：\n" + d3 + "-" + d4 + "=" + (d3 -d4) + "\n");

        BigDecimal bd1 = new BigDecimal("2.15");
        BigDecimal bd2 = new BigDecimal("1.10");
        System.out.println("BigDecimal,参数为String类型运算结果:\n" + bd1 + "-" + bd2 + "=" + bd1.subtract(bd2) + "\n");

        BigDecimal bd3 = new BigDecimal(String.valueOf(2.15));
        BigDecimal bd4 = new BigDecimal(String.valueOf(1.10));
        System.out.println("BigDecimal,参数为double类型时运算结果:\n" + bd3 + "-" + bd4 + "=" + bd3.subtract(bd4) + "\n");
    }
}