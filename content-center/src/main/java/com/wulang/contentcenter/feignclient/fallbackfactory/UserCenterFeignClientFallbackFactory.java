package com.wulang.contentcenter.feignclient.fallbackfactory;

import com.wulang.contentcenter.domain.dto.user.UserDTO;
import com.wulang.contentcenter.feignclient.UserCenterFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wulang
 * @create 2020/1/5/16:39
 */
@Component
@Slf4j
public class UserCenterFeignClientFallbackFactory implements FallbackFactory<UserCenterFeignClient> {
    @Override
    public UserCenterFeignClient create(Throwable cause) {
        return causes -> {
            log.warn("远程调用被限流/降级了", cause);
            UserDTO userDTO = new UserDTO();
            userDTO.setWxNickname("流控/降级返回的用户");
            return userDTO;
        };
    }
}
