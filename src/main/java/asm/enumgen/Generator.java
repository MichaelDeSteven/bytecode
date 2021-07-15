package asm.enumgen;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;

import static jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * @description: Generator
 * @author: Steven
 * @time: 2021/7/15 14:55
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(COMPUTE_MAXS | COMPUTE_FRAMES);
        genEnum(cw);
        genField(cw);
        genConstant(cw);
        cw.visitEnd();
        byte[] data = cw.toByteArray();

        // 输出
        File f = new File("C:/Users/Steven/bytecode/src/main/java/asm/enumgen/Status.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
    }

    public static void genEnum(ClassWriter cw) {
        cw.visit(V1_8,
                ACC_PUBLIC | ACC_ENUM,
                "asm/enumgen/Status",
                "java/lang/Object",
                null,
                null);
    }

    public static void genField(ClassWriter cw) {
        cw.visitField(ACC_PRIVATE,
                "code",
                "I",
                null,
                null).visitEnd();
        cw.visitField(ACC_PRIVATE,
                "desc",
                "java/util/String",
                null,
                null).visitEnd();
//        cw.visitField(ACC_ENUM,
//                "OFFLINE",
//                "asm/enumgen/Status",
//                null,
//                null).visitEnd();
    }


    public static void genConstant(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC,
                "Status",
                "(I)V",
                null,
                null);
        mv.visitEnd();
    }

}
