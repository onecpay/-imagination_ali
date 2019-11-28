package com.example.manage.config.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * 配置主数据源
 *
 * @author ONEC
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManageFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.example.manage.dao"})
public class DataConfig {


    /**
     * 数据源作用的实体类所在的位置
     */
    private static final String PACKAGES="com.example.manage.entity";

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private HibernateProperties hibernateProperties;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Primary
    @Bean("entityManage")
    public EntityManager entityManage(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(primaryEntityManageFactory(builder).getObject()).createEntityManager();
    }


    @Primary
    @Bean("entityManageFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManageFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages(PACKAGES)
                .persistenceUnit("persistenceUnit")
                .build();
    }

    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(primaryEntityManageFactory(builder).getObject()));
    }
}
