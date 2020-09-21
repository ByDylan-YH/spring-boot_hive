package com.hive.config;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Author:BYDylan
 * Date:2020/9/21
 * Description: 配置hive数据源
 * EnableConfigurationProperties: 将配置类注入到 bean 容器,使 ConfigurationProperties 注解类生效
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class,DataSourceCommonProperties.class})
public class HiveDruidConfig {

    private static Logger logger = LoggerFactory.getLogger(HiveDruidConfig.class);

    private final DataSourceProperties dataSourceProperties;

    private final DataSourceCommonProperties dataSourceCommonProperties;

    @Autowired
    public HiveDruidConfig(DataSourceCommonProperties dataSourceCommonProperties, DataSourceProperties dataSourceProperties) {
        this.dataSourceCommonProperties = dataSourceCommonProperties;
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean("hiveDruidDataSource") //新建bean实例
    @Qualifier("hiveDruidDataSource")//标识
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();

        //配置数据源属性
        datasource.setUrl(dataSourceProperties.getHive().get("url"));
        datasource.setUsername(dataSourceProperties.getHive().get("username"));
        datasource.setPassword(dataSourceProperties.getHive().get("password"));
        datasource.setDriverClassName(dataSourceProperties.getHive().get("driver-class-name"));

        //配置统一属性
        datasource.setInitialSize(dataSourceCommonProperties.getInitialSize());
        datasource.setMinIdle(dataSourceCommonProperties.getMinIdle());
        datasource.setMaxActive(dataSourceCommonProperties.getMaxActive());
        datasource.setMaxWait(dataSourceCommonProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceCommonProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSourceCommonProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSourceCommonProperties.getValidationQuery());
        datasource.setTestWhileIdle(dataSourceCommonProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(dataSourceCommonProperties.isTestOnBorrow());
        datasource.setTestOnReturn(dataSourceCommonProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(dataSourceCommonProperties.isPoolPreparedStatements());
        try {
            datasource.setFilters(dataSourceCommonProperties.getFilters());
        } catch (SQLException e) {
            logger.error("Druid configuration initialization filter error: {}", e);
        }
        return datasource;
    }

}