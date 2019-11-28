package com.example.product.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置线程池
 *
 * @author ONEC
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {


//    /**
//     * 配置异步线程池数据
//     *  使用异步线程池的时候：@Async("asyncExecutor")
//     * @return
//     */
//    @Bean
//    public Executor asyncExecutor() {
//
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        // 核心线程数
//        executor.setCorePoolSize(6);
//        // 最大线程数
//        executor.setMaxPoolSize(6);
//        // 最大队列数
//        executor.setQueueCapacity(600);
//        // 线程名称开头:
//        executor.setThreadNamePrefix("async-pool");
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//
//        executor.initialize();
//        log.info("executor.initialize(): end");
//        return executor;
//    }

}
