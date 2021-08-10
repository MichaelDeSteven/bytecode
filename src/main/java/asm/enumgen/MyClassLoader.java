package asm.enumgen;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @description: MyClassLoader
 * @author: Steven
 * @time: 2021/8/7 22:37
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) {
        String myPath = "C:/Users/Steven/bytecode/src/main/java/asm/enumgen/Status.class";
        byte[] cLassBytes = null;

        File f = new File(myPath);
        InputStream in = null;
        try {
            in = new FileInputStream(f);
            cLassBytes = new byte[(int)f.length()];
            in.read(cLassBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class clazz = defineClass("asm.enumgen.Status", cLassBytes, 0, cLassBytes.length);
        System.out.println(myPath);
        return clazz;
    }
}
