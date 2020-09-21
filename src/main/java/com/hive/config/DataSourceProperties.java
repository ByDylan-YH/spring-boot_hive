package com.hive.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Author:BYDylan
 * Date:2020/9/21
 * Description: 统一属性控制类,获取配置文件属性
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = DataSourceProperties.DS, ignoreUnknownFields = false)
class DataSourceProperties {
    final static String DS = "spring.datasource";
    private Map<String, String> hive;
    private Map<String, String> commonconfig;
}