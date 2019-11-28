package com.example.loader.utils.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 式编程：Reactor 学习
 *
 * @author ONEC
 */
public class FluxStart {


    public static void main(String[] s) {
        //createFlux();
        //generateFlux();
        //createFluxAsyn();
        createMono();
    }

    /**
     * 简单方法创建flux
     */
    public static void createFlux() {
        //可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束
        Flux hello = Flux.just("hello word");
        hello.subscribe(System.out::println);

        //可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象
        Flux.fromArray(new String[]{"我是", "adb"}).subscribe(System.out::println);

        List<String> fluxList = new ArrayList<>();
        fluxList.add("abce");

        Stream stream = Stream.of(fluxList);
        Flux.fromStream(stream).subscribe(System.out::println);


//        empty()：创建一个不包含任何元素，只发布结束消息的序列。
//        error(Throwable error)：创建一个只包含错误消息的序列。
//        never()：创建一个不包含任何消息通知的序列。
//        range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
//        interval(Duration period)和 interval(Duration delay, Duration period)：创建一个包含了从 0 开始递增的 Long 对象的序列。其中包含的元素按照指定的间隔来发布。除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
//        intervalMillis(long period)和 intervalMillis(long delay, long period)：与 interval()方法的作用相同，只不过该方法通过毫秒数来指定时间间隔和延迟时间。
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);


        //Flux.intervalMillis(1000).subscribe(System.out::println);


    }

    /**
     * generate()方法通过同步和逐一的方式来产生 Flux 序列
     * 创建复杂的flux
     */
    private static void generateFlux() {

        // next()方法产生一个简单的值，然后通过 complete()方法来结束该序列。如果不调用 complete()方法，所产生的是一个无限序列
        Flux.generate(sink -> {
            sink.next("hello word");
            sink.complete();
        }).subscribe(System.out::println);

        //ArrayList 对象。实际产生的值是一个随机数。产生的随机数被添加到 ArrayList 中。当产生了 10 个数时，通过 complete()方法来结束序列
        Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(1000);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }


    /**
     * crate 方法创建flux
     * create()方法与 generate()方法的不同之处在于所使用的是 FluxSink 对象。FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生多个元素
     */
    private static void createFluxAsyn() {

        Flux.create(sink -> {
            for (int i = 0; i < 100; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }


    /**
     * Mono 的创建方式与之前介绍的 Flux 比较相似。Mono 类中也包含了一些与 Flux 类中相同的静态方法。这些方法包括 just()，empty()，error()和 never()
     * fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和 fromSupplier()：分别从 Callable、CompletionStage、CompletableFuture、Runnable 和 Supplier 中创建 Mono。
     * delay(Duration duration)和 delayMillis(long duration)：创建一个 Mono 序列，在指定的延迟时间之后，产生数字 0 作为唯一值。
     * ignoreElements(Publisher<T> source)：创建一个 Mono 序列，忽略作为源的 Publisher 中的所有元素，只产生结束消息。
     * justOrEmpty(Optional<? extends T> data)和 justOrEmpty(T data)：从一个 Optional 对象或可能为 null 的对象中创建 Mono。只有 Optional 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
     * 还可以通过 create()方法来使用 MonoSink 来创建 Mono。代码清单 4 中给出了创建 Mono 序列的示例。
     */
    private static void createMono() {
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

    }

}
