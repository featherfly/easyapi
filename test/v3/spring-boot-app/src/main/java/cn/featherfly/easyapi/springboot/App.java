package cn.featherfly.easyapi.springboot;

import cn.featherfly.common.lang.Lang;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Locale;

/**
 * @author zhongj
 */

@SpringBootApplication(scanBasePackages = {"com.cdzkdc", "cn.featherfly.easyapi"}) //申明这是一个Spring Boot项目
@ComponentScan(basePackages = {"com.cdzkdc", "cn.featherfly.easyapi"}) //手动指定bean组件扫描范围
//@EnableTransactionManagement
//@EnableCaching
//@EnableScheduling
//@EnableFeignClients(basePackages = { "com.cdzkdc" })
//@EnableDiscoveryClient
//@EnableHystrix
public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        SpringApplication.run(App.class, args);
    }
}