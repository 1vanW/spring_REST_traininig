package com.ivan.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

//это конфигурационный файл для настройка спринга вместо xml файла
@Configuration
@ComponentScan (basePackages = "com.ivan.spring.rest")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {


    //создаем бин прописываем внем настройки для конекта с базой данных
    @Bean
    public DataSource dataSource(){

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("root");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    //создаем бин фабрики сессии
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        //ресурсы и назначаем созданный метод выше
        localSessionFactoryBean.setDataSource(dataSource());
        //обозначаем пакет для сканирования
        localSessionFactoryBean.setPackagesToScan("com.ivan.spring.rest.entity");
        //создаем поля
        Properties hibernateProperties = new Properties();
        //прописываем диалекты
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        //
        hibernateProperties.setProperty("hibernate.show_sql","true");
        //назначаем поля
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;

    }
    //менеджер транзакции
    @Bean
    public HibernateTransactionManager transactionManager(){

        HibernateTransactionManager transactionManager =  new HibernateTransactionManager();
        //задаем менеджеру сессию записывая метод фабрики сессий тут долгая история и надо всегда добавлять метод getObject бину фабрики сессий потому как он принимает просто сессию
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());

        return transactionManager;
    }



}
