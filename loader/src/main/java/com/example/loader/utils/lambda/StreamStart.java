package com.example.loader.utils.lambda;


import lombok.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * steam 详解/及使用
 */
public class StreamStart {


    /**
     * java 7
     * <p>
     * 客户每月平均消费金额
     * 最昂贵的在售商品
     * 本周完成的有效订单（排除了无效的）
     * 取十个数据样本作为首页推荐
     *
     * @param transactions
     */
    public static void getId(List<Transaction> transactions) {
        List<Transaction> transactionsid = new ArrayList<>(20);

        for (Transaction t : transactions) {
            if (t.getType() == Transaction.GROCERY) {
                transactionsid.add(t);
            }
        }
        Collections.sort(transactionsid, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        List<Integer> transactionIds = new ArrayList<>();
        Iterator<Transaction> integers = transactionsid.iterator();
        while (integers.hasNext()) {
            Transaction t = integers.next();
            transactionIds.add(t.getId());
        }
    }

    /**
     * java 8
     * <p>
     * 客户每月平均消费金额
     * 最昂贵的在售商品
     * 本周完成的有效订单（排除了无效的）
     * 取十个数据样本作为首页推荐
     *
     * @param transactions
     */
    public static void get(List<Transaction> transactions) {
        List<Integer> idList = transactions.parallelStream()
                .filter(t -> t.getType().equals(Transaction.GROCERY))
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getId).collect(Collectors.toList());

        System.out.println("javad8获取到的数据：" + idList);
    }


    /**
     * stream 学习：1
     * 构造stream
     */
    public void createStream() {

        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
    }

    /**
     * 数值流的构造
     */
    public void createIntStream() {
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    /**
     * 流的转化
     *
     * @param
     */
    public void receveStream() {

        Stream stream = Stream.of(new String[]{"q", "b"});
        // 1. Array
//        String[] strArray1 = stream.toArray(String[]::new);
//        // 2. Collection
//        List<String> list1 = stream.collect(Collectors.toList());
//        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
//        Set set1 = stream.collect(Collectors.toSet());
//        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = stream.collect(Collectors.joining()).toString();
    }

    /**
     * map的使用：
     * 它的作用就是把 input Stream 的每一个元素，映射成 output Stream 的另外一个元素。
     */
    public void map() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
    }

    /**
     * flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字
     * flatMap
     */
    public void flatMap() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());

    }


    /**
     * ilter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream
     */
    public void filter() {
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);

        //---------------------

//        List<String> output = reader.lines().
//        flatMap(line -> Stream.of(line.split(REGEXP))).
//            filter(word -> word.length() > 0).
//                    collect(Collectors.toList());
    }

    /**
     * 当需要为多核系统优化时，可以 parallelStream().forEach()，只是此时原有元素的次序没法保证
     * forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了，你无法对一个 Stream 进行两次 terminal 运算
     * forEach
     */
    public void forEach(List<Transaction> roster) {
        // Java 8
        roster.stream()
                .filter(p -> p.getType() == Transaction.GROCERY)
                .forEach(p -> System.out.println(p.getType()));
        //Pre - Java 8
        for (Transaction p : roster) {
            if (p.getValue() == Transaction.GROCERY) {
                System.out.println(p.getType());
            }
        }
    }

    /**
     * forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
     * peek 对每个元素执行操作并返回一个新的 Stream
     * forEach
     */
    public void peek(List<Transaction> roster) {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

//     Optional
//    public static void print(String text) {
//        // Java 8
//        Optional.ofNullable(text).ifPresent(System.out::println);
//        // Pre-Java 8
//        if (text != null) {
//            System.out.println(text);
//        }
//    }
//    public static int getLength(String text) {
//        // Java 8
//        return Optional.ofNullable(text).map(String::length).orElse(-1);
//        // Pre-Java 8
//// return if (text != null) ? text.length() : -1;
//    };

    /**
     * 上面代码例如第一个示例的 reduce()，第一个参数（空白字符）即为起始值，
     * 第二个参数（String::concat）为 BinaryOperator。这类有起始值的 reduce() 都返回具体的对象。
     * 而对于第四个示例没有起始值的 reduce()，由于可能没有足够的元素，返回的是 Optional，请留意这个区别。
     */
    public void reduce() {

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
    }

    /**
     * limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）。
     *
     * @param persons
     */
    public void limit(List<Transaction> persons) {
        List<String> personList2 = persons.stream().
                map(Transaction::getValue).limit(10).skip(3).collect(Collectors.toList());
        System.out.println(personList2);
    }

    /**
     * 找出最长的一条
     * 面的例子则使用 distinct 来找出不重复的单词。
     */
    public void min() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("c:\\SUService.log"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int longest = br.lines().mapToInt(String::length).max().getAsInt();
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(longest);
        // 面的例子则使用 distinct 来找出不重复的单词。
        List<String> words = br.lines().flatMap(line -> Stream.of(line.split(" ")))
                .filter(word -> word.length() > 0).map(String::toLowerCase).distinct()
                .sorted().collect(Collectors.toList());
        try {
            br.close();
        } catch (IOException e) {

        }
        System.out.println(words);
    }

    /**
     * 分组
     */
    public void groupby() {

//        Map<Integer, List<Transaction>> personGroups = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.groupingBy(Transaction::getType));
//        Iterator it = personGroups.entrySet().iterator();
//        while (it.hasNext()) {
//        Map.Entry<Integer, List<Transaction>> persons = (Map.Entry) it.next();
//        System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
//        }
    }

//    allMatch：Stream 中全部元素符合传入的 predicate，返回 true
//    anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
//    noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true

//boolean isAllAdult = persons.stream().
//        allMatch(p -> p.getAge() > 18);
//System.out.println("All are adult? " + isAllAdult);
//    boolean isThereAnyChild = persons.stream().
//            anyMatch(p -> p.getAge() < 12);
//System.out.println("Any child? " + isThereAnyChild);
}

@Data
class Transaction {
    public static final String GROCERY = "GROCERY";
    private Integer id;
    private String type;
    private String value;
}




