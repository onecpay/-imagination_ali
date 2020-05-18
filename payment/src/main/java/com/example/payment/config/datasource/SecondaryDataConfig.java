package com.example.payment.config.datasource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * 配置第二数据源
 *
 * @author ONEC
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondaryEntityManageFactory",
        transactionManagerRef = "secondaryTransactionManager",
        basePackages = {"com.example.payment.dao.secondary"})
public class SecondaryDataConfig {


    /**
     * 数据源作用的实体类所在的位置
     */
    private static final String PACKAGES="com.example.payment.entity.secondary";

    @Resource
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;


    @Bean("secondaryEntityManage")
    public EntityManager entityManage(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(secondaryEntityManageFactory(builder).getObject()).createEntityManager();
    }



    @Bean("secondaryEntityManageFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManageFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(secondaryDataSource)
                .properties(properties)
                .packages(PACKAGES)
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }


    @Bean("secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(secondaryEntityManageFactory(builder).getObject()));
    }
}
