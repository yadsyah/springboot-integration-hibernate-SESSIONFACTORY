package com.hibernatetutorial.demo.configuration;

import com.hibernatetutorial.demo.DemoApplication;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hibernatetutorial.demo.repositoryjpa",transactionManagerRef = "emTX")
public class HibernateConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private Environment environment;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        LOGGER.info("getDataSource : {}", dataSource);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        Properties properties = new Properties();

        // Hibernate Properties
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
//        properties.put("current_session_context_class", environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.put("hibernate.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.use_sql_comments",environment.getProperty("hibernate.use_sql_comments"));
//        properties.put("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.id.new_generator_mappings",false);
        properties.put("hibernate.search.default.directory_provider","filesystem");
        properties.put("hibernate.search.default.indexBase","G:/Testter/HibernateSearch/index");
        properties.put("hibernate.search.default.indexwriter.infostream",true);
        LOGGER.info("Properties SessionFactory : {}",properties.toString());
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.afterPropertiesSet();
        LOGGER.info("getSessionFactory : {}", sessionFactory);
        return sessionFactory;
    }

    @Bean
    @PersistenceContext(unitName = "emTX")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        em.setPersistenceUnitName("emTX");
        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        LOGGER.info("Properties JPA : {}",additionalProperties().toString());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.use_sql_comments",environment.getProperty("hibernate.use_sql_comments"));
        properties.setProperty("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));
        return properties;
    }

    @Primary
    @Bean(value = "sfTX")
    @ConditionalOnMissingBean(HibernateTransactionManager.class)
    public HibernateTransactionManager getTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean(value = "emTX")
    @ConditionalOnMissingBean(JpaTransactionManager.class)
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
