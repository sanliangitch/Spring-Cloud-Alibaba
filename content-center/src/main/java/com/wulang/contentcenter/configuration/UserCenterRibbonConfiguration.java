package com.wulang.contentcenter.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

/**
 * 代码配置 Ribbon 细腻度问题
 *
 * 尽量使用属性配置，属性配置实现不了的情况下，再考虑用代码配置
 * 配置尽量保证单一性，不要两种方式混用，增加定位代码的复杂性
 * @author wulang
 * @create 2020/1/4/9:11
 */
//单一配置
//@Configuration
//@RibbonClient(name = "user-center",configuration = RibbonConfiguration.class)
//全局配置
 @Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {
}
