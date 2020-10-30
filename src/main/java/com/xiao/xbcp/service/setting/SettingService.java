package com.xiao.xbcp.service.setting;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SettingService
 *
 * @author KyleZeng
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class SettingService {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Autowired
    private ResourceLoader resourceLoader;

    public void init() {
        try {
            initSqlLite();
        } catch (Exception e) {
            e.printStackTrace();
           throw new RuntimeException("初始化出错");
        }
    }

    public void initSqlLite() throws Exception {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(jdbcUrl, null, null);
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setStopOnError(true);
        Resource resource = resourceLoader.getResource("classpath:sqlite_init.sql");
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        runner.runScript(isr);
        conn.close();
    }
}
