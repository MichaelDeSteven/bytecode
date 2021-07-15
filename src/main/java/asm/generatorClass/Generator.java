package asm.generatorClass;


import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class Generator {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8,
                ACC_PUBLIC,
                "asm/generatorClass/MathTool",
                null,
                "java/lang/Object",
                new String[] {});
        cw.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC, "PI", "D",
                null, 3.1415926).visitEnd();
        cw.visitField(ACC_PRIVATE, "f", "I", null, 0).visitEnd();
        cw.visitMethod(ACC_PUBLIC,
                "calculator",
                "(D)V",
                null,
                null).visitEnd();
        generatorGetter(cw);
        generatorSetter(cw);
        cw.visitEnd();
        byte[] data = cw.toByteArray();

        // 输出
        File f = new File("C:/Users/zhuangsongtao/IdeaProjects/bytecode/src/main/java/asm/generatorClass/MathTool.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
    }

    public static void generatorGetter(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC,
                "getF",
                "()I",
                null,
                null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "generatorClass/MathTool", "f", "I");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    public static void generatorSetter(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC,
                "setF",
                "(I)V",
                null,
                null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ILOAD, 1);
        mv.visitFieldInsn(PUTFIELD, "generatorClass/MathTool", "f", "I");
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
    }
}
