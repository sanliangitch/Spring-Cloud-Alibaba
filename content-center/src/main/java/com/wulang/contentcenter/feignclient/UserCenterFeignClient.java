package com.wulang.contentcenter.feignclient;

import com.wulang.contentcenter.domain.dto.user.UserDTO;
import com.wulang.contentcenter.feignclient.fallbackfactory.UserCenterFeignClientFallbackFactory;
import com.wulang.contentcenter.feignclient.interceptor.TokenRelayRequestIntecepor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign的配置可以用注解或者配置文件的方式配置
 *
 * @author wulang
 * @create 2020/1/4/10:52
 */
//@FeignClient(name = "user-center",configuration = {GlobalFeignCofiguration.class,TokenRelayRequestIntecepor.class})
@FeignClient(name = "user-center",
//        fallback = UserCenterFeignClientFallback.class,
        fallbackFactory = UserCenterFeignClientFallbackFactory.class,
        configuration = TokenRelayRequestIntecepor.class)
public interface UserCenterFeignClient {

    /**
     * http://user-center/users/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    UserDTO findById(
            @PathVariable("id") Integer id);

//    @GetMapping("/users/{id}")
//    UserDTO findById(
//            @PathVariable("id") Integer id,
//            @RequestHeader("X-Token") String token);
}
