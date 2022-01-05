package com.foxminded.university.config;

import com.foxminded.university.domain.model.Teacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.foxminded.university")
public class UniversityConfigTest {
    @Value("${db.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public SimpleJdbcInsert insertTeacher() {
        return new SimpleJdbcInsert(dataSource())
                .withTableName("teachers")
                .usingColumns("first_name", "last_name")
                .usingGeneratedKeyColumns("id");
    }

    @Bean
    public BeanPropertyRowMapper<Teacher> teacherRowMapper() {
        return new BeanPropertyRowMapper<>(Teacher.class);
    }

}
