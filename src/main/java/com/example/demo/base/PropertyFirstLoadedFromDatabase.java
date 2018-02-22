package com.example.demo.base;

import org.springframework.context.annotation.DependsOn;

import java.lang.annotation.*;

/**
 * 属性优先从数据库加载
 * liuwei
 */

@Target({ElementType.TYPE, ElementType.METHOD}) //表示此注解能用来修饰类中的type和Method
@Retention(RetentionPolicy.RUNTIME)
/*
@Retention(保留)注解说明,这种类型的注解会被保留到那个阶段. 有三个值:
1.RetentionPolicy.SOURCE —— 这种类型的Annotations只在源代码级别保留,编译时就会被忽略
2.RetentionPolicy.CLASS —— 这种类型的Annotations编译时被保留,在class文件中存在,但JVM将会忽略
3.RetentionPolicy.RUNTIME —— 这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
 */
@Documented //它代表着此注解会被javadoc工具提取成文档
/**
 * @Inherited这是一个稍微复杂的注解类型. 它指明被注解的类会自动继承.
 * 更具体地说,如果定义注解时使用了 @Inherited 标记,然后用定义的注解来标注另一个父类,
 * 父类又有一个子类(subclass),则父类的所有属性将被继承到它的子类中.
 */
@Inherited
//@DependsOn用于强制初始化其他Bean。可以修饰Bean类或方法，使用该Annotation时可以指定一个字符串数组作为参数，每个数组元素对应于一个强制初始化的Bean。
@DependsOn("PropertyFirstLoadedFromDatabaseConfig")
public @interface PropertyFirstLoadedFromDatabase {
}
