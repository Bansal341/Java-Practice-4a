package com.example.sm.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.example.sm")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        // Using H2 for quick demo. For MySQL, change URL/driver/username/password
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/studentdb;DB_CLOSE_DELAY=-1;MODE=MYSQL");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(ds);
        lsfb.setPackagesToScan("com.example.sm.model");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", "update"); // create/update for dev, use validate in prod
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        lsfb.setHibernateProperties(props);
        return lsfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory);
        return tx;
    }
}
