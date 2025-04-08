package object.guo;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-03-05 09:42
 *
 * //反编译之后的字节码文件
 * public class com.guo.Test {
 *   public com.guo.Test();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]) throws java.lang.Exception;
 *     Code:
 *        0: iconst_0   //int型0入栈 -> 栈顶 = 0
 *        1: istore_1   //出栈赋值给变量i
 *        2: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        5: iload_1
 *        6: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
 *        9: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       12: iload_1
 *       13: iinc          1, 1
 *       16: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
 *       19: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       22: iload_1
 *       23: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
 *       26: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       29: iinc          1, 1
 *       32: iload_1
 *       33: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
 *       36: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       39: iload_1
 *       40: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
 *       43: return
 * }
 **/
public class Test {

    public static void main(String[] args) throws Exception {

        int i = 0;
        System.out.println(i);
        System.out.println(i++);
        System.out.println(i);
        System.out.println(++i);
        System.out.println(i);
    }
}