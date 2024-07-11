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
@RequestMapping("/resource/banquet")
public class BanquetResourceController extends AbstractResourceController {

    @Override
    ResourceType resourceType() {
        return ResourceType.BANQUET_HALL;
    }

    @PostMapping
    @Secured(Authority.Resource.BanquetHall.ADD)
    @Operation(title = "banquet halls addition", type = OperationType.INSERT)
    public Object add(@Validated @RequestBody ResourceInfo info) {
        return resourceService.insert(info, resourceType());
    }

    @PutMapping
    @Secured(Authority.Resource.BanquetHall.EDIT)
    @Operation(title = "banquet halls modification", type = OperationType.UPDATE)
    public Object edit(@Validated @RequestBody ResourceInfo info) {
        checkDataPermission(List.of(info.getId()));
        return resourceService.update(info, resourceType());
    }

    @DeleteMapping
    @Secured(Authority.Resource.BanquetHall.DELETE)
    @Operation(title = "banquet halls deletion", type = OperationType.DELETE)
    public void delete(@RequestBody List<Long> resourceIds) {
        checkDataPermission(resourceIds);
        resourceService.deleteByIds(resourceIds);
    }

    @PostMapping("/restore")
    @Secured(Authority.Resource.BanquetHall.RESTORE)
    @Operation(title = "banquet halls restore", type = OperationType.RESTORE)
    public void restore(@RequestBody List<Long> resourceIds) {
        checkDataPermission(resourceIds);
        resourceService.restoreByIds(resourceIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Resource.BanquetHall.QUERY)
    public PageResult list(ResourceQuery query) {
        query.setType(resourceType().getType());
        return new PageResult(resourceService.count(query), resourceService.find(query));
    }

    @GetMapping("/{id}")
    @Secured(Authority.Resource.BanquetHall.QUERY)
    public Resource get(@PathVariable Long id) {
        checkDataPermission(List.of(id));
        return resourceService.findById(id);
    }

    @PutMapping("/changeStatus")
    @Secured(Authority.Resource.BanquetHall.STATUS)
    @Operation(title = "banquet halls status change", type = OperationType.UPDATE)
    public int changeStatus(@RequestBody ChangeStatus change) {
        checkDataPermission(List.of(change.getId()));
        return resourceService.changeStatus(change);
    }

    @GetMapping("/all")
    @Secured({Authority.Price.BanquetHall.ADD, Authority.Price.BanquetHall.EDIT})
    public Object listAll() {
        return resourceService.listAll(resourceType());
    }

}
