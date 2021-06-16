package com.moon.javassist;

import com.alibaba.dubbo.common.compiler.support.JavassistCompiler;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * java ssist 动态编译示例
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-1 15:09
 * @description
 */
public class JavassistTest {

    /**
     * javassist动态生成类示例
     */
    @Test
    public void createClassByJavassist() throws Exception {
        // 1. ClassPool：Class对象的容器
        ClassPool pool = ClassPool.getDefault();

        // 2. 通过ClassPool生成一个public类，指定生成的全限定名
        CtClass ctClass = pool.makeClass("com.moon.service.DemoImpl");

        // 3. 给生成的类添加属性 private String name
        CtField nameFild = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
        nameFild.setModifiers(Modifier.PRIVATE);
        ctClass.addField(nameFild);

        // 添加属性 private int age
        CtField ageField = new CtField(pool.getCtClass("int"), "age", ctClass);
        ageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ageField);

        // 4. 为属性添加getXXX和setXXX方法
        ctClass.addMethod(CtNewMethod.getter("getName", nameFild));
        ctClass.addMethod(CtNewMethod.setter("setName", nameFild));
        ctClass.addMethod(CtNewMethod.getter("getAge", ageField));
        ctClass.addMethod(CtNewMethod.setter("setAge", ageField));

        // 5. 添加方法，如：void sayHello(String name) {...}
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "sayHello", new CtClass[]{}, ctClass);
        // 将方法设置为public
        ctMethod.setModifiers(Modifier.PUBLIC);
        // 设置方法体内容
        ctMethod.setBody("{\nSystem.out.println(\"hello \" + getName() + \" !\");\n}");
        ctClass.addMethod(ctMethod); // 设置方法到类中

        // 6. 生成Class类对象
        Class clazz = ctClass.toClass();

        // 通过反射去创建实例
        Object obj = clazz.newInstance();
        // 反射执行方法sayHello
        obj.getClass().getMethod("setName", new Class[]{String.class}).invoke(obj, new Object[]{"moon"});
        obj.getClass().getMethod("sayHello", new Class[]{}).invoke(obj, new Object[]{});
    }

    /**
     * JavassistCompiler 动态编译类示例
     */
    @Test
    public void createClassByCompile() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1. 创建JavassistCompiler
        JavassistCompiler compiler = new JavassistCompiler();

        // 2. 直接字符串的形式编写一个类，转成Class
        Class<?> clazz = compiler.compile("public class DemoImpl implements DemoService {     public String sayHello(String name) {        System.out.println(\"hello \" + name);        return \"Hello, \" + name;   }}", JavassistTest.class.getClassLoader());

        // 3. 创建对象
        Object obj = clazz.newInstance();
        // 4. 反射 执行方法sayHello
        obj.getClass().getMethod("sayHello", new Class[]{String.class}).invoke(obj, new Object[]{"moon"});
    }

}
