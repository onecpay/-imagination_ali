package com.example.loader.utils.loader;

import lombok.Data;
import reactor.core.publisher.Flux;

/**
 * 测试类加载
 *
 * @author ONEC
 */
public class Parent {

    private static final String url = "类加载url------------------：";

    private Integer name;

    static {
        int a = 5;
        int b = 6;
        System.out.println("加载静态块：" + url);
        System.out.println("加载静态块值：" + a + "" + b);
    }

    Parent(Integer name) {
        this.name = name;
    }


}

/**
 * 创建子类继承自父类
 */
@Data
class Sun extends Parent {

    private static final String s = "子类静态资源：";

    static {
        int a = 10;
        System.out.println("子类静态资源：88888888" + s);
        System.out.println("子类静态资源：a" + a);
    }

    Sun() {
        super(null);
    }

    Sun(Integer name) {
        super(name);
        System.out.println("加载子类构造方法：" + name);
    }

    public static void main(String[] args) {
        Sun sun = new Sun(10);
        System.out.println("测试类加载顺寻：" + sun.toString());
    }

}