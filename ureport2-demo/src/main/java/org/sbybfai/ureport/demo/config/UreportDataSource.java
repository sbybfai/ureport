package org.sbybfai.ureport.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.sbybfai.ureport.definition.datasource.BuildinDatasource;


@Component
public class UreportDataSource implements BuildinDatasource {

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Override
    public String name() {
        return "test_db";
    }

    @Override
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
