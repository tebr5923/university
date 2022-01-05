package com.foxminded.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@SuppressWarnings("squid:S106") //don't use logger in this task
@Component("dbFactory")
@PropertySource("classpath:db.properties")
public class DBFactory {
    private final DataSource dataSource;

    @Value("${db.create}")
    private String createTablesFileName;

    @Value("${db.drop}")
    private String dropTablesFileName;

    @Autowired
    public DBFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTables() {
        executeScript(createTablesFileName, "CREATE");
    }

    public void dropTables() {
        executeScript(dropTablesFileName, "DROP");
    }

    public void executeScriptFromResources(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        try {
            databasePopulator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            System.err.printf("wrong script form file %s%n", fileName);
            throw new IllegalStateException(String.format("wrong script form file %s%n", fileName),e);
        }
    }

    private void executeScript(String fileName, String message) {
        Resource resource = new ClassPathResource(fileName);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        try {
            databasePopulator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            System.err.printf("table NOT %s%n", message);
            throw new IllegalStateException(String.format("cant %s table", message), e);
        }
    }

}
