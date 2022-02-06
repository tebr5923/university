package com.foxminded.university.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@Configuration
@Import(UniversityConfig.class)
@TestPropertySource("classpath:db.properties")
public class UniversityTestConfig {

}
