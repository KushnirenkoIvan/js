package ua.kushnirenko.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ua.kushnirenko.dao")
public class DbConfig {

    @Bean
    public DriverManagerDataSource dataSource(
            @Value("${db.url}") String dbUrl,
            @Value("${db.login}") String login,
            @Value("${db.pass}") String pass) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(login);
        dataSource.setPassword(pass);
        return dataSource;
    }


    @Bean
    org.hibernate.cfg.Configuration configuration(@Value("${hibernate.hbm2ddl}") String hbm2ddl) {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.pool_size", "1");
        configuration.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        return configuration;
    }

    @Bean
    LocalSessionFactoryBean sessionFactory(DriverManagerDataSource dataSource,
                                           org.hibernate.cfg.Configuration configuration) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(configuration.getProperties());
        factoryBean.setPackagesToScan("ua.kushnirenko.entity");
        return factoryBean;
    }

    @Bean
    HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}



