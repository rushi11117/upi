package com.upi.transactionmanager.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.upi.transactionmanager.Repositories")
public class RepositoryConfiguration {
}
