package com.wulang.contentcenter.controller.content;

import com.wulang.contentcenter.domain.dto.content.ShareAuditDTO;
import com.wulang.contentcenter.domain.entity.content.Share;
import com.wulang.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wulang
 * @create 2020/1/5/19:57
 */
@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {
    private final ShareService shareService;

    /**
     * 管理员审核分享业务实现
     *
     * @param id
     * @param auditDTO
     * @return
     */
    @PutMapping("/audit/{id}")
    public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDTO auditDTO) {
        // TODO 认证授权
        return this.shareService.auditById(id, auditDTO);
    }

}
