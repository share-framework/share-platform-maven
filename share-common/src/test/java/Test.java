import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Test {
    public static void main(String[] args) throws ZipException {
        System.err.println("ZIP4J jar包解压缩测试");
        for (int i = 0; i < 15; i++) {
            System.err.println("第"+(i+1)+"次循环");
            long l1 = System.currentTimeMillis();
            new ZipFile("/Users/andot/Movies/test.zip")
                    .extractAll("/Users/andot/Movies/destination_directory");
            long l2 = System.currentTimeMillis();
            System.err.println(l2-l1);
        }
    }
}
