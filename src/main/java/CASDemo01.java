/**
 * @description:
 * @author: guofengbo
 * @create: 2020-01-07 20:51
 **/
public class CASDemo01 {

    private static volatile int num = 0;

    public synchronized static void addNum() {
        num++;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 2000; i++) {

                new Thread(() -> {
                   try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CASDemo01.addNum();
                    //System.out.println(num);
                }).start();

        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(num);
    }

}
