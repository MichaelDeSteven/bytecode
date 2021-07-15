package asm.start;


import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Generator {
    public static void main(String[] args) throws IOException {
        // 输入
        ClassReader reader = new ClassReader("asm/Base");
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //处理
        ClassVisitor classVisitor = new MyClassVisitor(writer);
        reader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = writer.toByteArray();

        // 输出
        File f = new File("C:/Users/zhuangsongtao/IdeaProjects/bytecode/target/classes/asm/Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("生成完毕");
    }
}
