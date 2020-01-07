package com.wulang.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author wulang
 * @create 2020/1/6/21:17
 */
@Service
@Slf4j
public class TestStreamConsumer {
    @StreamListener(Sink.INPUT)
    public void receive(String messageBody){
        log.info("通过stream收到消息：messageBody = {}",messageBody);
    }
}
