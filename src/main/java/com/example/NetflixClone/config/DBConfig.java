package com.example.NetflixClone.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Value("netflix.cgz2d3rkfuxm.ap-southeast-2.rds.amazonaws.com")
    private String jdbcUrl;

    @Value("admin")
    private String username;

    @Value("kicha123")
    private String password;

    @Bean(destroyMethod = "close")
    @Primary
    DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.jdbc.driver");
        return dataSource;
    }
}
