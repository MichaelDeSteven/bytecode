package asm.enumgen;

import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * @description: Test
 * @author: Steven
 * @time: 2021/8/7 20:57
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        Class<?> clz = new MyClassLoader().findClass("");
        Class<?> clz = ClassLoader.getSystemClassLoader()
                .loadClass("C:/Users/Steven/bytecode/src/main/java/asm/enumgen/Status");
        Object[] objects = clz.getEnumConstants();
        for (Method m : clz.getMethods()) {
            System.out.println(m.getName());
        }

        for (Object obj : objects) {
            System.out.println(obj);
            System.out.println(clz.getMethod("getCode").invoke(obj));
//            System.out.println(clz.getMethod("getDesc").invoke(obj));
        }
    }
}
