package com.icezhg.sunflower.controller.resource;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.ResourceInfo;
import com.icezhg.sunflower.pojo.query.ResourceQuery;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@RestController
@RequestMapping("/resource/conference")
public class ConferenceResourceController extends AbstractResourceController {

    @Override
    ResourceType resourceType() {
        return ResourceType.CONFERENCE_ROOM;
    }

    @PostMapping
    @Secured(Authority.Resource.ConferenceRoom.ADD)
    @Operation(title = "conference rooms addition", type = OperationType.INSERT)
    public Object add(@Validated @RequestBody ResourceInfo info) {
        return this.resourceService.insert(buildResource(info));
    }

    @PutMapping
    @Secured(Authority.Resource.ConferenceRoom.EDIT)
    @Operation(title = "conference rooms modification", type = OperationType.UPDATE)
    public Object edit(@Validated @RequestBody ResourceInfo info) {
        checkDataPermission(List.of(info.getId()));
        return this.resourceService.update(buildResource(info));
    }

    @DeleteMapping
    @Secured(Authority.Resource.ConferenceRoom.DELETE)
    @Operation(title = "conference rooms deletion", type = OperationType.DELETE)
    public void delete(@RequestBody List<Long> resourceIds) {
        checkDataPermission(resourceIds);
        this.resourceService.deleteByIds(resourceIds);
    }

    @PostMapping("/restore")
    @Secured(Authority.Resource.ConferenceRoom.RESTORE)
    @Operation(title = "conference rooms restore", type = OperationType.RESTORE)
    public void restore(@RequestBody List<Long> resourceIds) {
        checkDataPermission(resourceIds);
        this.resourceService.restoreByIds(resourceIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Resource.ConferenceRoom.QUERY)
    public PageResult list(ResourceQuery query) {
        query.setType(resourceType().getType());
        return new PageResult(resourceService.count(query), resourceService.find(query));
    }

    @GetMapping("/{id}")
    @Secured(Authority.Resource.ConferenceRoom.QUERY)
    public Resource get(@PathVariable Long id) {
        checkDataPermission(List.of(id));
        return this.resourceService.findById(id);
    }

    @PutMapping("/changeStatus")
    @Secured(Authority.Resource.ConferenceRoom.STATUS)
    @Operation(title = "conference rooms status change", type = OperationType.UPDATE)
    public int changeStatus(@RequestBody ChangeStatus change) {
        checkDataPermission(List.of(change.getId()));
        return this.resourceService.changeStatus(change);
    }

}
