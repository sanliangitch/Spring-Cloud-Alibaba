package com.wulang.contentcenter.feignclient;

import com.wulang.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wulang
 * @create 2020/1/4/20:38
 */
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {
    /**
     * @SpringQueryMap ：
     *
     * @param userDTO
     * @return
     */
    @GetMapping("/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);
}
