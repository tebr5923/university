package com.foxminded.university.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(UniversityDataSourceConfig.class)
@ComponentScan("com.foxminded.university")
public class UniversityConfig {

}
