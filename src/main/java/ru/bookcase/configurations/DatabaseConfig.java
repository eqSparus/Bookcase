package ru.bookcase.configurations;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@PropertySource(value = "classpath:database/database.properties", encoding = "UTF-8")
@ComponentScan(basePackages = {"ru.bookcase.services", "ru.bookcase.repositories"})
public class DatabaseConfig {

    Environment env;

    @Autowired
    public DatabaseConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        var factory = new LocalSessionFactoryBean();
        factory.setHibernateProperties(getSettingsHibernate());
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("ru.bookcase.entity");
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        var dataSource = new BasicDataSource();
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
        return dataSource;
    }

    private Properties getSettingsHibernate(){
        var settings = new Properties();
        settings.put(AvailableSettings.DIALECT, env.getRequiredProperty("jdbc.dialect"));
        settings.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("jdbc.show_sql"));
        settings.put(AvailableSettings.FORMAT_SQL, env.getRequiredProperty("jdbc.format_sql"));
        settings.put(AvailableSettings.STATEMENT_FETCH_SIZE, env.getRequiredProperty("jdbc.fetch_size"));
        settings.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty("jdbc.batch_size"));
        settings.put(AvailableSettings.USE_SQL_COMMENTS, env.getRequiredProperty("jdbc.use_sql_comments"));
        settings.put(AvailableSettings.GENERATE_STATISTICS, env.getRequiredProperty("jdbc.generate_statistics"));
        return settings;
    }
}
