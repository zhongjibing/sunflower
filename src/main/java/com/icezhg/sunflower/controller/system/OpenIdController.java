package com.icezhg.sunflower.controller.system;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.OpenidInfo;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OpenidQuery;
import com.icezhg.sunflower.service.OpenidService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/07/18.
 */
@RestController
@RequestMapping("/open")
public class OpenidController {

    private final OpenidService openidService;

    public OpenidController(OpenidService openidService) {
        this.openidService = openidService;
    }

    @PutMapping("/update")
    @Secured(Authority.System.Openid.EDIT)
    @Operation(title = "openid user modification", type = OperationType.UPDATE)
    public Object edit(@Validated @RequestBody OpenidInfo info) {
        return this.openidService.update(info);
    }

    @GetMapping("/list")
    @Secured(Authority.System.Openid.QUERY)
    public PageResult list(OpenidQuery query) {
        return new PageResult(openidService.count(query), openidService.find(query));
    }

    @GetMapping("/{id:\\d+}")
    @Secured(Authority.System.Openid.QUERY)
    public Object get(@PathVariable Long id) {
        return this.openidService.findById(id);
    }

    @PutMapping("/changeStatus")
    @Secured(Authority.System.Openid.STATUS)
    @Operation(title = "openid user status change", type = OperationType.UPDATE)
    public int changeStatus(@RequestBody ChangeStatus change) {
        return this.openidService.changeStatus(change);
    }
}
