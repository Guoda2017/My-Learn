import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-19 17:07
 **/
public class HashMapTest {

    public static void main(String[] args) throws Exception {

        Map<String, String> map = new HashMap<String, String>(0);

        Class<?> mapType = map.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map));

        Map<String, String> map2 = new HashMap<String, String>(3);

        Class<?> mapType2 = map2.getClass();
        Method capacity2 = mapType2.getDeclaredMethod("capacity");
        capacity2.setAccessible(true);
        System.out.println("capacity2 : " + capacity2.invoke(map2));


        Map<String, String> map3 = new HashMap<String, String>(9);

        Class<?> mapType3 = map3.getClass();
        Method capacity3= mapType3.getDeclaredMethod("capacity");
        capacity3.setAccessible(true);
        System.out.println("capacity3 : " + capacity3.invoke(map3));

    }

}
