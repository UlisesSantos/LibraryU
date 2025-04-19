package com.school.library.Java.LibraryU.configuration;

import com.school.library.Java.LibraryU.common.Env;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppContext {

    //Object DataSource
    @Bean
    DataSource dataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(Env.getPropertyByPrefix("ds","jdbc.url"));
        ds.setUsername(Env.getPropertyByPrefix("ds", "username"));
        ds.setPassword(Env.getPropertyByPrefix("ds", "password"));
        ds.setDriverClassName(Env.getPropertyByPrefix("ds", "driver.class.name"));
        ds.setMaximumPoolSize(Integer.parseInt(Env.getPropertyByPrefix("ds", "maximum.pool.size")));
        return ds;
    }

    //Object LocalContainerEntityManagerFactoryBean
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.school.library.Java.LibraryU.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect",Env.getPropertyByPrefix("jpa", "dialect"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", Env.getPropertyByPrefix("jpa", "hbm2ddl.auto"));
        jpaProperties.setProperty("hibernate.show_sql", Env.getPropertyByPrefix("jpa", "show.sql"));
        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    //Object PlatformTransactionManager
    @Bean
    PlatformTransactionManager transactionManager(){
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }
}
