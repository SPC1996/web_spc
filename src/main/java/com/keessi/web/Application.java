package com.keessi.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args-> {
            System.out.println("Let's inspect the beans provided by spring boot:");
            String[] beansNames=context.getBeanDefinitionNames();
            Arrays.sort(beansNames);
            for (String name : beansNames) {
                System.out.println(name);
            }
        };
    }
}
