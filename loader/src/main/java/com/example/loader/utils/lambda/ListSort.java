package com.example.loader.utils.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lamada 排序
 *
 * @author ONEC
 */
public class ListSort {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(16);
        list.add("11111");
        list.add("我是java7");
        list.add("aaaaa");
        list.add("我是java7");

        ListSort listSort = new ListSort();
//        System.out.println("java7排序版本前：" + list);
//        listSort.sort7(list);
//        System.out.println("java7排序版本后：" + list);

        System.out.println("java7排序版本前：" + list);
        listSort.sort8(list);
        System.out.println("java7排序版本后：" + list);
    }

    /**
     * java 7 的排序
     *
     * @param list
     */
    public void sort7(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
    }

    /**
     * java8 排序简介
     * @param list
     */
    public void sort8(List<String> list) {
        list.sort(String::compareTo);
    }

}
