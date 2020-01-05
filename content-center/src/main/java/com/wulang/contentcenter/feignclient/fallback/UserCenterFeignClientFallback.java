package com.wulang.contentcenter.feignclient.fallback;

import com.wulang.contentcenter.domain.dto.user.UserDTO;
import com.wulang.contentcenter.feignclient.UserCenterFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author wulang
 * @create 2020/1/5/16:35
 */
@Component
public class UserCenterFeignClientFallback implements UserCenterFeignClient {
    @Override
    public UserDTO findById(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setWxNickname("流控/降级返回的用户");
        return userDTO;
    }
}
