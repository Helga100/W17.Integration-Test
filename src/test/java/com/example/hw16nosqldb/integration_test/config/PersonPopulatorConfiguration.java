package com.example.hw16nosqldb.integration_test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class PersonPopulatorConfiguration {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("person-data.json")});

        return factory;
    }
}
