package asm.enumgen;

import jdk.internal.org.objectweb.asm.*;

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
        ClassWriter cw = new ClassWriter(0);
        genEnum(cw);
        genField(cw);
        genConstruct(cw);
        genClinit(cw);
        genMethodOfValues(cw);
        genGetter(cw);
        cw.visitEnd();
        byte[] data = cw.toByteArray();
//        byte[] data = dump();
        // 输出
        File f = new File("C:/Users/Steven/bytecode/src/main/java/asm/enumgen/Status.class");
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
        cw.visitSource("Status.java", null);
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
        cw.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC | ACC_SYNTHETIC,
                "$VALUES",
                "[Lasm/enumgen/Status;",
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
                "(Ljava/lang/String;I)V",
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
        Label l4 = new Label();
        mv.visitLabel(l4);
        mv.visitLocalVariable("this", "Lasm/enumgen/Status;", null, l0, l4, 0);
        mv.visitLocalVariable("code", "I", null, l0, l4, 3);
        mv.visitLocalVariable("desc", "Ljava/lang/String;", null, l0, l4, 4);
        mv.visitMaxs(3, 5);
        mv.visitEnd();
    }

    public static void genMethodOfValues(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC,
                "values",
                "()[Lasm/enumgen/Status;",
                null,
                null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitFieldInsn(GETSTATIC, "asm/enumgen/Status",
                "$VALUES",
                "[Lasm/enumgen/Status;");
        mv.visitMethodInsn(INVOKEVIRTUAL,
                "[Lasm/enumgen/Status;",
                "clone",
                "()Ljava/lang/Object;",
                false);
        mv.visitTypeInsn(CHECKCAST, "[Lasm/enumgen/Status;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 0);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC,
                "valueOf",
                "(Ljava/lang/String;)Lasm/enumgen/Status;",
                null,
                null);
        mv.visitCode();
        l0 = new Label();
        mv.visitLabel(l0);
        mv.visitLdcInsn(Type.getType("Lasm/enumgen/Status;"));
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Enum",
                "valueOf",
                "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;",
                false);
        mv.visitTypeInsn(CHECKCAST, "asm/enumgen/Status");
        mv.visitInsn(ARETURN);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLocalVariable("name", "Ljava/lang/String;", null, l0, l1, 0);
        mv.visitMaxs(2, 1);
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
        mv.visitIntInsn(BIPUSH, 0);
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
        mv.visitIntInsn(BIPUSH, 1);
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
        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitInsn(ICONST_2);
        mv.visitTypeInsn(ANEWARRAY, "asm/enumgen/Status");

        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_0);
        mv.visitFieldInsn(GETSTATIC, "asm/enumgen/Status",
                "OFFLINE",
                "Lasm/enumgen/Status;");
        mv.visitInsn(AASTORE);

        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_1);
        mv.visitFieldInsn(GETSTATIC, "asm/enumgen/Status",
                "ONLINE",
                "Lasm/enumgen/Status;");
        mv.visitInsn(AASTORE);

        mv.visitFieldInsn(PUTSTATIC,
                "asm/enumgen/Status",
                "$VALUES",
                "[Lasm/enumgen/Status;");
        mv.visitInsn(RETURN);

        mv.visitMaxs(6, 0);
        mv.visitEnd();
    }

    public static void genGetter(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC,
                "getCode",
                "()I",
                null,
                null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD,
                "asm/enumgen/Status",
                "code",
                "I");
        mv.visitInsn(IRETURN);
        mv.visitMaxs(2, 2);
    }

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;

        cw.visit(V1_5, ACC_PUBLIC + ACC_FINAL + ACC_SUPER + ACC_ENUM, "asm/enumgen/StatusEnum", "Ljava/lang/Enum<Lasm/enumgen/StatusEnum;>;", "java/lang/Enum", null);

        cw.visitSource("StatusEnum.java", null);

        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM, "ONLINE", "Lasm/enumgen/StatusEnum;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM, "OFFLINE", "Lasm/enumgen/StatusEnum;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM, "BUSY", "Lasm/enumgen/StatusEnum;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "code", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "desc", "Ljava/lang/String;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC + ACC_SYNTHETIC, "$VALUES", "[Lasm/enumgen/StatusEnum;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "values", "()[Lasm/enumgen/StatusEnum;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(8, l0);
            mv.visitFieldInsn(GETSTATIC, "asm/enumgen/StatusEnum", "$VALUES", "[Lasm/enumgen/StatusEnum;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "[Lasm/enumgen/StatusEnum;", "clone", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lasm/enumgen/StatusEnum;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "valueOf", "(Ljava/lang/String;)Lasm/enumgen/StatusEnum;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(8, l0);
            mv.visitLdcInsn(Type.getType("Lasm/enumgen/StatusEnum;"));
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Enum", "valueOf", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;", false);
            mv.visitTypeInsn(CHECKCAST, "asm/enumgen/StatusEnum");
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("name", "Ljava/lang/String;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "(ILjava/lang/String;)V", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(17, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Enum", "<init>", "(Ljava/lang/String;I)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(18, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitFieldInsn(PUTFIELD, "asm/enumgen/StatusEnum", "code", "I");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(19, l2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitFieldInsn(PUTFIELD, "asm/enumgen/StatusEnum", "desc", "Ljava/lang/String;");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(20, l3);
            mv.visitInsn(RETURN);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLocalVariable("this", "Lasm/enumgen/StatusEnum;", null, l0, l4, 0);
            mv.visitLocalVariable("code", "I", null, l0, l4, 3);
            mv.visitLocalVariable("desc", "Ljava/lang/String;", null, l0, l4, 4);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(9, l0);
            mv.visitTypeInsn(NEW, "asm/enumgen/StatusEnum");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("ONLINE");
            mv.visitInsn(ICONST_0);
            mv.visitIntInsn(BIPUSH, 100);
            mv.visitLdcInsn("在线");
            mv.visitMethodInsn(INVOKESPECIAL, "asm/enumgen/StatusEnum", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", false);
            mv.visitFieldInsn(PUTSTATIC, "asm/enumgen/StatusEnum", "ONLINE", "Lasm/enumgen/StatusEnum;");
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(10, l1);
            mv.visitTypeInsn(NEW, "asm/enumgen/StatusEnum");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("OFFLINE");
            mv.visitInsn(ICONST_1);
            mv.visitIntInsn(BIPUSH, 101);
            mv.visitLdcInsn("下线");
            mv.visitMethodInsn(INVOKESPECIAL, "asm/enumgen/StatusEnum", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", false);
            mv.visitFieldInsn(PUTSTATIC, "asm/enumgen/StatusEnum", "OFFLINE", "Lasm/enumgen/StatusEnum;");
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(11, l2);
            mv.visitTypeInsn(NEW, "asm/enumgen/StatusEnum");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("BUSY");
            mv.visitInsn(ICONST_2);
            mv.visitIntInsn(BIPUSH, 102);
            mv.visitLdcInsn("忙碌");
            mv.visitMethodInsn(INVOKESPECIAL, "asm/enumgen/StatusEnum", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", false);
            mv.visitFieldInsn(PUTSTATIC, "asm/enumgen/StatusEnum", "BUSY", "Lasm/enumgen/StatusEnum;");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(8, l3);
            mv.visitInsn(ICONST_3);
            mv.visitTypeInsn(ANEWARRAY, "asm/enumgen/StatusEnum");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(GETSTATIC, "asm/enumgen/StatusEnum", "ONLINE", "Lasm/enumgen/StatusEnum;");
            mv.visitInsn(AASTORE);
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_1);
            mv.visitFieldInsn(GETSTATIC, "asm/enumgen/StatusEnum", "OFFLINE", "Lasm/enumgen/StatusEnum;");
            mv.visitInsn(AASTORE);
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_2);
            mv.visitFieldInsn(GETSTATIC, "asm/enumgen/StatusEnum", "BUSY", "Lasm/enumgen/StatusEnum;");
            mv.visitInsn(AASTORE);
            mv.visitFieldInsn(PUTSTATIC, "asm/enumgen/StatusEnum", "$VALUES", "[Lasm/enumgen/StatusEnum;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
