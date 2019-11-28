//package com.common.config;
//
//import com.common.uid.UidGenerator;
//import com.common.uid.impl.DefaultUidGenerator;
//import com.example.wechat.utils.DisposableWorkerIdAssigner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 将服务写入到表
// *
// * @author yangzhongying
// * @date 2018/6/24 11:24
// */
//@Configuration
//public class UidConfiguration {
//
//
//    @Bean
//    public DisposableWorkerIdAssigner getDisposableWorkerIdAssigner() {
//        return new DisposableWorkerIdAssigner();
//    }
//
//
//    @Bean
//    public UidGenerator getUidGenerator() {
//        DefaultUidGenerator cachedUidGenerator = new DefaultUidGenerator();
//        cachedUidGenerator.setTimeBits(30);
//        cachedUidGenerator.setWorkerBits(18);
//        cachedUidGenerator.setSeqBits(15);
//        cachedUidGenerator.setEpochStr("2019-04-02");
//        return cachedUidGenerator;
//    }
//}
