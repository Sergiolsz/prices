package com.app.prices.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.app.prices.infrastructure.database.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}