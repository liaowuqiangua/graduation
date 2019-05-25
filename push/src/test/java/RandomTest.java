import java.util.Random;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/23 22:56
 * @description :${description}
 */
public class RandomTest {
    public static void main(String[] args) throws Exception{
        for (;;){
            Thread.sleep(100);
            System.out.println(new Random().nextDouble());
        }
    }
}
