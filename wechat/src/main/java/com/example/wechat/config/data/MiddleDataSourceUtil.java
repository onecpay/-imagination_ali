//package com.example.wechat.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import tk.mybatis.spring.annotation.MapperScan;
//
//import javax.sql.DataSource;
//
///**
// * 配置alibaba 数据库第二数据源
// * @author ONEC
// *
// */
//@Configuration
//@MapperScan(basePackages = {MiddleDataSourceUtil.MIDDLE_DATA_PACKAGE},
//        sqlSessionFactoryRef = "middleSqlSessionFactory")
//public class MiddleDataSourceUtil {
//    /**
//     * 精确到主数据源的配置，以便和其他数据源分开
//     */
//    protected static final String MIDDLE_DATA_PACKAGE = "com.example.wechat.dao.**";
//    private static final String MIDDLE_DATA_MAPPER = "classpath:mapper/**/*.xml";
//
//    @Value("${spring.datasource.middle.jdbcUrl}")
//    public String jdbcUrl;
//
//    @Value("${spring.datasource.middle.driverClassName}")
//    private String driverClass;
//
//    @Value("${spring.datasource.middle.username}")
//    private String username;
//
//    @Value("${spring.datasource.middle.password}")
//    private String password;
//
//    /**
//     * 数据源连接配置
//     *
//     * @return
//     */
//    @Bean(name = "middleDataSource")
//    public DataSource masterDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    /**
//     * 数据源事务管理
//     *
//     * @return
//     */
//    @Bean(name = "middleDataSourceTransactionManager")
//    public DataSourceTransactionManager masterDataSourceTransactionManager() {
//        return new DataSourceTransactionManager(masterDataSource());
//    }
//
//    /**
//     * 数据源配置
//     *
//     * @param dataSource
//     * @return
//     * @throws Exception
//     */
//    @Bean(name = "middleSqlSessionFactory")
//    public SqlSessionFactory masterSqlSessionFactory(
//            @Qualifier("middleDataSource") DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(MIDDLE_DATA_MAPPER));
//        return sessionFactory.getObject();
//    }
//}
