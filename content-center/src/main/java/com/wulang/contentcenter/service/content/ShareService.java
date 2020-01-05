package com.wulang.contentcenter.service.content;

import com.wulang.contentcenter.dao.content.ShareMapper;
import com.wulang.contentcenter.domain.dto.content.ShareAuditDTO;
import com.wulang.contentcenter.domain.dto.content.ShareDTO;
import com.wulang.contentcenter.domain.dto.user.UserDTO;
import com.wulang.contentcenter.domain.entity.content.Share;
import com.wulang.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author wulang
 * @create 2020/1/3/7:28
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;
//    private final RestTemplate restTemplate;
//    private final DiscoveryClient discoveryClient;

    public ShareDTO findById(Integer id) {
        // 获取分享详情
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 发布人id
        Integer userId = share.getUserId();
//        //用户中心所有实例信息
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//        /**
//         * 强调：
//         * 了解stream -->JDK8
//         * lambda表达式
//         * functional -->函数式编程
//         */
////        String targetURL = instances.stream()
////                .map(instance -> instance.getUri().toString() + "/users/{id}")
////                .findFirst()
////                .orElseThrow(() -> new IllegalArgumentException("当前没有实例"));
//        //所有用户实例的请求地址
//        List<String> targetURLS = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/users/{id}")
//                .collect(Collectors.toList());
//        int i = ThreadLocalRandom.current().nextInt(targetURLS.size());
//
////        log.info("请求的URL地址：{}",targetURL);
//        log.info("请求的URL地址：{}",targetURLS.get(i));
        /**
         * 1. 代码不可读
         * 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
         * 3. 难以相应需求的变化，变化很没有幸福感
         * 4. 编程体验不统一
         */
//        UserDTO userDTO = this.restTemplate.getForObject(
//                "http://user-center/users/{userId}",
//                UserDTO.class, userId
//        );
        UserDTO userDTO = this.userCenterFeignClient.findById(userId);
        ShareDTO shareDTO = new ShareDTO();
        // 消息的装配
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;

//        UserDTO userDTO = this.userCenterFeignClient.findById(userId);
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        // 用HTTP GET方法去请求，并且返回一个对象
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "http://localhost:8080/users/{id}",
                String.class, 2
        );

        System.out.println(forEntity.getBody());
        // 200 OK
        // 500
        // 502 bad gateway...
        System.out.println(forEntity.getStatusCode());
    }

    /**
     * 管理员审核分享业务实现
     *
     * @param id
     * @param auditDTO
     * @return
     */
    public Share auditById(Integer id, ShareAuditDTO auditDTO) {
        //1、查询share是否存在，不存在或者当前audit_status ！= NOT_YET ，那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null){
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())){
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
        }
        //2、审核资源，将资源设置成 PASS/REJECT
        share.setAuditStatus(auditDTO.getAuditStatusEnum().toString());
        this.shareMapper.updateByPrimaryKey(share);
        //3、如果是PASS，那么为发布人添加积分
//        userCenterFeignClient.addBonus(id,500);

        return null;
    }
}
