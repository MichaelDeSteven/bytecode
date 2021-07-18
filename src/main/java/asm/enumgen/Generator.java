package asm.enumgen;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Label;
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
        genConstruct(cw);
        genClinit(cw);
        cw.visitEnd();
        byte[] data = cw.toByteArray();

        // 输出
        File f = new File("C:/Users/zhuangsongtao/IdeaProjects/bytecode/src/main/java/asm/enumgen/Status.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
    }

    public static void genEnum(ClassWriter cw) {
        cw.visit(V1_5,
                ACC_PUBLIC | ACC_FINAL | ACC_SUPER | ACC_ENUM,
                "asm/enumgen/Status",
                "Ljava/lang/Enum<Lasm/enumgen/Status;>;",
                "java/lang/Enum",
                null);
    }

    public static void genField(ClassWriter cw) {
        cw.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC | ACC_ENUM,
                "OFFLINE",
                "Lasm/enumgen/Status;",
                null,
                null).visitEnd();
        cw.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC | ACC_ENUM,
                "ONLINE",
                "Lasm/enumgen/Status;",
                null,
                null).visitEnd();
        cw.visitField(ACC_PRIVATE,
                "code",
                "I",
                null,
                null).visitEnd();
        cw.visitField(ACC_PRIVATE,
                "desc",
                "Ljava/lang/String;",
                null,
                null).visitEnd();
    }


    public static void genConstruct(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PRIVATE,
                "<init>",
                "(Ljava/lang/String;IILjava/lang/String;)V",
                "(ILjava/lang/String;)V",
                null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitMethodInsn(INVOKESPECIAL,
                "java/lang/Enum",
                "<init>",
                "(ILjava/lang/String;)V",
                false);

        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ILOAD, 3);
        mv.visitFieldInsn(PUTFIELD,
                "asm/enumgen/Status",
                "code",
                "I");

        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 4);
        mv.visitFieldInsn(PUTFIELD,
                "asm/enumgen/Status",
                "desc",
                "Ljava/lang/String;");

        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 5);
        mv.visitEnd();
    }

    public static void genClinit(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_STATIC,
                "<clinit>",
                "()V",
                null,
                null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitTypeInsn(NEW, "asm/enumgen/Status");
        mv.visitInsn(DUP);
        mv.visitLdcInsn("OFFLINE");
        mv.visitInsn(ICONST_0);
        mv.visitInsn(ICONST_0);
        mv.visitLdcInsn("下线");
        mv.visitMethodInsn(INVOKESPECIAL,
                "asm/enumgen/Status",
                "<init>",
                "(Ljava/lang/String;IILjava/lang/String;)V",
                false);
        mv.visitFieldInsn(PUTSTATIC,
                "asm/enumgen/Status",
                "OFFLINE",
                "Lasm/enumgen/Status;");

        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitTypeInsn(NEW, "asm/enumgen/Status");
        mv.visitInsn(DUP);
        mv.visitLdcInsn("ONLINE");
        mv.visitInsn(ICONST_1);
        mv.visitInsn(ICONST_1);
        mv.visitLdcInsn("在线");
        mv.visitMethodInsn(INVOKESPECIAL,
                "asm/enumgen/Status",
                "<init>",
                "(Ljava/lang/String;IILjava/lang/String;)V",
                false);
        mv.visitFieldInsn(PUTSTATIC,
                "asm/enumgen/Status",
                "ONLINE",
                "Lasm/enumgen/Status;");
        mv.visitEnd();
    }

}
