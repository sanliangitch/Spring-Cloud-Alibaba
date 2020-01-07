package com.wulang.contentcenter.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Spring Cloud Stream自定义接口 - 发送消息
 *
 * @author wulang
 * @create 2020/1/6/21:21
 */
public interface MySource {
    String MY_OUTPUT = "my-output";

    @Output(MY_OUTPUT)
    MessageChannel output();
}
