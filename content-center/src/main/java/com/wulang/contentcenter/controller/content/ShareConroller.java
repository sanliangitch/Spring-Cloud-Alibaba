package com.wulang.contentcenter.controller.content;

import com.wulang.contentcenter.auth.CheckLogin;
import com.wulang.contentcenter.domain.dto.content.ShareDTO;
import com.wulang.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareConroller {
    private final ShareService shareService;

    @GetMapping("/{id}")
    @CheckLogin
    public ShareDTO findById(
            @PathVariable Integer id,
            @RequestHeader("X-Token") String token) {
//        return this.shareService.findById(id,token);
        return this.shareService.findById(id);
    }
}
