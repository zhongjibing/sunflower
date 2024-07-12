package com.icezhg.sunflower.controller.inventory;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.InventoryInfo;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.PriceQuery;
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
 * Created by zhongjibing on 2023/07/12.
 */
@RestController
@RequestMapping("/inventory/conference")
public class ConferenceInventoryController extends AbstractInventoryController {

    @Override
    ResourceType resourceType() {
        return ResourceType.CONFERENCE_ROOM;
    }

    @PostMapping
    @Secured(Authority.Inventory.ConferenceRoom.ADD)
    @Operation(title = "conference rooms inventories addition", type = OperationType.INSERT)
    public Object addInventory(@Validated @RequestBody InventoryInfo info) {
        return this.inventoryService.insert(info, resourceType());
    }

    @PutMapping
    @Secured(Authority.Inventory.ConferenceRoom.EDIT)
    @Operation(title = "conference rooms inventories modification", type = OperationType.UPDATE)
    public Object editInventory(@Validated @RequestBody InventoryInfo info) {
        checkDataPermission(List.of(info.getId()));
        return this.inventoryService.update(info, resourceType());
    }

    @DeleteMapping
    @Secured(Authority.Inventory.ConferenceRoom.DELETE)
    @Operation(title = "conference rooms inventories deletion", type = OperationType.DELETE)
    public void deleteInventory(@RequestBody List<Long> resourceIds) {
        checkDataPermission(resourceIds);
        this.inventoryService.deleteByIds(resourceIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Inventory.ConferenceRoom.QUERY)
    public PageResult list(PriceQuery query) {
        query.setType(resourceType().getType());
        return new PageResult(inventoryService.count(query), inventoryService.findDetails(query));
    }

    @GetMapping("/{id:\\d+}")
    @Secured(Authority.Inventory.ConferenceRoom.QUERY)
    public Object getInventory(@PathVariable Long id) {
        checkDataPermission(List.of(id));
        return this.inventoryService.findDetailById(id);
    }

    @PostMapping("/generate")
    @Secured(Authority.Inventory.ConferenceRoom.GENERATE)
    @Operation(title = "conference rooms inventory plan generated", type = OperationType.GENERATE)
    public void generate(Long id) {
        checkDataPermission(List.of(id));
        this.inventoryPlanService.generate(id);
    }
}
