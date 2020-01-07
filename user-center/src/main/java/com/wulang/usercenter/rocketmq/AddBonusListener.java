package com.wulang.usercenter.rocketmq;

import com.wulang.usercenter.dao.bonus.BonusEventLogMapper;
import com.wulang.usercenter.dao.user.UserMapper;
import com.wulang.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.wulang.usercenter.domain.entity.bonus.BonusEventLog;
import com.wulang.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wulang
 * @create 2020/1/5/22:04
 */
@Service
@RocketMQMessageListener(consumerGroup = "consumer-group",topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
        //当收到消息的时候执行的业务
        //1、为用户加积分
        Integer userId = message.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + message.getBonus());
        this.userMapper.updateByPrimaryKey(user);
        //2、记录日志到 bonus_event_log 表里面
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(message.getBonus())
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("投稿加积分..")
                .build());
    }
}
