package com.foxminded.university.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        UniversityDataSourceConfig.class,
        UniversityDaoConfig.class,
        UniversityMapperConfig.class,
        UniversityInsertConfig.class,
        UniversityServiceConfig.class
})
public class UniversityConfig {

}
