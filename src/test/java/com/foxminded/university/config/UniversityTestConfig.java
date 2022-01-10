package com.foxminded.university.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@ComponentScan("com.foxminded.university")
@TestPropertySource("classpath:db.properties")
public class UniversityTestConfig {

}
