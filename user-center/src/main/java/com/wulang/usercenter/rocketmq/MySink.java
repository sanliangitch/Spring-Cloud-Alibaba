package com.wulang.usercenter.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wulang
 * @create 2020/1/6/21:29
 */
public interface MySink {
    String MY_INPUT = "my-input";

    @Input(MY_INPUT)
    SubscribableChannel input();
}
