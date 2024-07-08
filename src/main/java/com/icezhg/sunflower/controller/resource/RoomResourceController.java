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
import com.icezhg.sunflower.service.ResourceService;
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
@RequestMapping("/resource/room")
public class RoomResourceController extends AbstractResourceController {
    private final ResourceService resourceService;

    public RoomResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    @Secured(Authority.Resource.GuestRoom.ADD)
    @Operation(title = "guest rooms addition", type = OperationType.INSERT)
    public Object add(@Validated @RequestBody ResourceInfo info) {
        return this.resourceService.insert(buildResource(info));
    }

    @PutMapping
    @Secured(Authority.Resource.GuestRoom.EDIT)
    @Operation(title = "guest rooms modification", type = OperationType.UPDATE)
    public Object edit(@Validated @RequestBody ResourceInfo info) {
        return this.resourceService.update(buildResource(info));
    }

    @DeleteMapping
    @Secured(Authority.Resource.GuestRoom.DELETE)
    @Operation(title = "guest rooms deletion", type = OperationType.DELETE)
    public void delete(@RequestBody List<Long> resourceIds) {
        this.resourceService.deleteByIds(resourceIds);
    }

    @PostMapping("/restore")
    @Secured(Authority.Resource.GuestRoom.RESTORE)
    @Operation(title = "guest rooms restore", type = OperationType.RESTORE)
    public void restore(@RequestBody List<Long> resourceIds) {
        this.resourceService.restoreByIds(resourceIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Resource.GuestRoom.QUERY)
    public PageResult list(ResourceQuery query) {
        query.setType(resourceType().getType());
        return new PageResult(resourceService.count(query), resourceService.find(query));
    }

    @GetMapping("/{id}")
    @Secured(Authority.Resource.GuestRoom.QUERY)
    public Resource get(@PathVariable Long id) {
        return this.resourceService.findById(id);
    }

    @PutMapping("/changeStatus")
    @Secured(Authority.Resource.GuestRoom.STATUS)
    @Operation(title = "guest rooms status change", type = OperationType.UPDATE)
    public int changeStatus(@RequestBody ChangeStatus change) {
        return this.resourceService.changeStatus(change);
    }

    @Override
    ResourceType resourceType() {
        return ResourceType.GUEST_ROOM;
    }
}
