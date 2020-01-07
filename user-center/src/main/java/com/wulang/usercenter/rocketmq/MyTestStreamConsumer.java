package com.wulang.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

/**
 * @author wulang
 * @create 2020/1/6/21:17
 */
@Service
@Slf4j
public class MyTestStreamConsumer {
    @StreamListener(MySink.MY_INPUT)
    public void receive(String messageBody){
        log.info("自定义接口消费：通过stream收到消息：messageBody = {}",messageBody);
    }

    /**
     * 全局异常处理
     *
     * @param message 发生异常消息
     */
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        ErrorMessage errorMessage = (ErrorMessage) message;
        System.out.println("Handling ERROR: " + errorMessage);
        log.warn("发生异常,errorMessage = {}" + errorMessage);
    }
}
