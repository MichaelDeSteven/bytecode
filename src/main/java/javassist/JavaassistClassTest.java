package javassist;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaassistClassTest {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass ctClass = pool.makeClass("src.main.java.javassist.HelloWorld");

        CtMethod method = new CtMethod(CtClass.voidType,
                "main",
                new CtClass[]{pool.get(String[].class.getName())},
                ctClass);
        method.setModifiers(Modifier.PUBLIC | Modifier.STATIC);
        method.setBody("{ System.out.println(\"hello javassist\"); }");
        ctClass.addMethod(method);

        // 创建无参数构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        ctConstructor.setBody("{}");
        ctClass.addConstructor(ctConstructor);

        ctClass.writeFile();
        // 测试调用
        Class clazz = ctClass.toClass();
        Object obj = clazz.newInstance();

        Method main = clazz.getDeclaredMethod("main", String[].class);
        main.invoke(obj, (Object)new String[1]);
    }
}
