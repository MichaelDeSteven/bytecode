package asm.generics;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;

import static jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class Generator {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(COMPUTE_MAXS | COMPUTE_FRAMES);
        genClass(cw);
        genFields(cw);
        genMethod(cw);
        // 输出
        cw.visitEnd();
        byte[] data = cw.toByteArray();
        File f = new File("C:/Users/zhuangsongtao/IdeaProjects/bytecode/src/main/java/asm/generics/Adder.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
    }

    public static void genClass(ClassWriter cw) {
        cw.visit(V1_8,
                ACC_PUBLIC,
                "asm/generics/Adder",
                "<E:Ljava/lang/Object;T:Ljava/lang/Object;>Ljava/util/List<+TE;+TT;>;",
                "java/lang/Object",
                null);
    }

    public static void genFields(ClassWriter cw) {
        cw.visitField(ACC_PRIVATE,
                "listX",
                "Ljava/util/List<E>;",
                null,
                null).visitEnd();
        cw.visitField(ACC_PRIVATE,
                "mapY",
                "Ljava/util/Map<T>;",
                null,
                null).visitEnd();
    }

    public static void genMethod(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC,
                "getListX",
                "()Ljava/util/List<E>;",
                null,
                null);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD,
                "asm/generics/Adder",
                "listX",
                "Ljava/util/List<E>;");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

}
