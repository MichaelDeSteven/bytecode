package javassist;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateClazzMethod {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("src.main.java.javassist.MathTool");

        CtField field = new CtField(CtClass.doubleType, "pi", ctClass);
        field.setModifiers(Modifier.STATIC | Modifier.FINAL | Modifier.PRIVATE);
        ctClass.addField(field, "3.1415926");

        CtMethod method = new CtMethod(CtClass.voidType,
                "calculator",
                new CtClass[]{CtClass.doubleType},
                ctClass);
        method.setModifiers(Modifier.PUBLIC);
        method.setBody("System.out.println(pi * $1 * $1);");
        ctClass.addMethod(method);

        ctClass.writeFile();

        // 测试调用
        Class clazz = ctClass.toClass();
        Object obj = clazz.newInstance();

        Method main = clazz.getDeclaredMethod("calculator", double.class);
        main.invoke(obj, 2);
    }
}
