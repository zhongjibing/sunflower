package com.icezhg.sunflower.controller.price;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.PriceRuleInfo;
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
 * Created by zhongjibing on 2023/07/08.
 */
@RestController
@RequestMapping("/price/conference")
public class ConferencePriceController extends AbstractPriceController {

    @Override
    ResourceType resourceType() {
        return ResourceType.CONFERENCE_ROOM;
    }

    @PostMapping
    @Secured(Authority.Price.ConferenceRoom.ADD)
    @Operation(title = "conference rooms price rules addition", type = OperationType.INSERT)
    public Object addRule(@Validated @RequestBody PriceRuleInfo info) {
        return this.priceRuleService.insert(buildPriceRule(info));
    }

    @PutMapping
    @Secured(Authority.Price.ConferenceRoom.EDIT)
    @Operation(title = "conference rooms price rules modification", type = OperationType.UPDATE)
    public Object editRule(@Validated @RequestBody PriceRuleInfo info) {
        checkDataPermission(List.of(info.getId()));
        return this.priceRuleService.update(buildPriceRule(info));
    }

    @DeleteMapping
    @Secured(Authority.Price.ConferenceRoom.DELETE)
    @Operation(title = "conference rooms price rules deletion", type = OperationType.DELETE)
    public void deleteRule(@RequestBody List<Long> resourceIds) {
        checkDataPermission(resourceIds);
        this.priceRuleService.deleteByIds(resourceIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Price.ConferenceRoom.QUERY)
    public PageResult list(PriceQuery query) {
        query.setType(resourceType().getType());
        return new PageResult(priceRuleService.count(query), priceRuleService.findDetails(query));
    }

    @GetMapping("/{id:\\d+}")
    @Secured(Authority.Price.ConferenceRoom.QUERY)
    public Object getRule(@PathVariable Long id) {
        checkDataPermission(List.of(id));
        return this.priceRuleService.findDetailById(id);
    }

    @PostMapping("/generate")
    @Secured(Authority.Price.ConferenceRoom.GENERATE)
    @Operation(title = "conference rooms price plan generated", type = OperationType.GENERATE)
    public void generate(Long id) {
        checkDataPermission(List.of(id));
        this.pricePlanService.generate(id);
    }
}
