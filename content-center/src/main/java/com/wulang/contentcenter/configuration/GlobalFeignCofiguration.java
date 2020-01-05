package com.wulang.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * fegin的配置类
 *
 * 无需加@Configuration注解，否则要挪到@ComponentScan能扫描的包以外
 *
 * @author wulang
 * @create 2020/1/4/11:07
 */
public class GlobalFeignCofiguration {
    @Bean
    public Logger.Level level(){
        //打印所有请求的细节
        return Logger.Level.FULL;
    }
}
