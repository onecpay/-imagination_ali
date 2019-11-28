package com.example.wechat.config.data;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * springboot mybatis 第一数据源配置
 *
 * @author ONEC
 */
@Slf4j
//@EnableConfigurationProperties(MasterDataProperties.class)
@Configuration
@MapperScan(basePackages = {MasterDataSourceUtil.MASTER_DATA_PACKAGE},
        sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceUtil {

    /**
     * 精确到主数据源的配置，以便和其他数据源分开
     */
    protected static final String MASTER_DATA_PACKAGE = "com.example.wechat.dao.**";
    private static final String MASTER_DATA_MAPPER = "classpath:mapper/**/*.xml";

    @Resource
    private MasterDataProperties properties;

    /**
     * 不支持宽松绑定。不支持云数据的配置。支持spel
     */
    @Value("${spring.datasource.master.password}")
    private String password;

    /**
     * 数据源连接配置
     *
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {

        DruidDataSource dataSource = new DruidDataSource();

        log.info("数据库：driverClassName：{}", properties.getDriverClassName());

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getJdbcUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        /**
         * 数据库链接池配置
         */
        // 最大连接池的数量
        dataSource.setMaxActive(properties.getMaxActive());
        // 最小连接池的数量
        dataSource.setMinIdle(properties.getMinIdle());
        // 初始化建立的物理链接数
        dataSource.setInitialSize(properties.getInitialSize());
        //	获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
        dataSource.setMaxWait(properties.getMaxWait());
        /**
         * 如果空闲时间大于这个数。且isTestWhileIdle true 进行sql 验证。
         */
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        /**
         *
         */
        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        /**
         * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
         */
        dataSource.setValidationQuery(properties.getValidationQuery());
        /**
         * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
         */
        dataSource.setTestWhileIdle(properties.isTestWhileIdle());
        /**
         * 检查查询语句时候有效，会降低性能
         */
        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        /**
         * 归还链接时，检查链接是否有效。会降低性能
         */
        dataSource.setTestOnReturn(properties.isTestOnReturns());
        /**
         * 开启：对具有游标的数据库，性能提升较大。
         * mysql： 建议关闭
         */
        dataSource.setPoolPreparedStatements(false);
        /**
         *
         */
        //dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        return dataSource;
    }

    /**
     * 数据源事务管理
     *
     * @return
     */
    @Bean(name = "masterDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    /**
     * 数据源配置成功
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(
            @Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MASTER_DATA_MAPPER));
        return sessionFactory.getObject();
    }
}
