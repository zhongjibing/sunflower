package com.icezhg.sunflower.controller.customer;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.domain.CustomerTag;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.PriceRuleInfo;
import com.icezhg.sunflower.pojo.query.NameQuery;
import com.icezhg.sunflower.service.CustomerTagService;
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
 * Created by zhongjibing on 2023/07/10.
 */
@RestController
@RequestMapping("/customer/tags")
public class CustomerTagController {

    private final CustomerTagService customerTagService;

    public CustomerTagController(CustomerTagService customerTagService) {
        this.customerTagService = customerTagService;
    }

    @PostMapping
    @Secured(Authority.Price.BanquetHall.ADD)
    @Operation(title = "banquet halls addition", type = OperationType.INSERT)
    public Object add(@Validated @RequestBody CustomerTag info) {
        return this.customerTagService.insert(buildPriceRule(info));
    }

    @PutMapping
    @Secured(Authority.Price.BanquetHall.EDIT)
    @Operation(title = "banquet halls modification", type = OperationType.UPDATE)
    public Object edit(@Validated @RequestBody PriceRuleInfo info) {
        checkDataPermission(List.of(info.getId()));
        return this.customerTagService.update(buildPriceRule(info));
    }

    @DeleteMapping
    @Secured(Authority.Price.BanquetHall.DELETE)
    @Operation(title = "banquet halls deletion", type = OperationType.DELETE)
    public void delete(@RequestBody List<Long> resourceIds) {
        this.customerTagService.deleteByIds(resourceIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Price.BanquetHall.QUERY)
    public PageResult list(NameQuery query) {
        return new PageResult(customerTagService.count(query), customerTagService.find(query));
    }

    @GetMapping("/all")
    @Secured(Authority.Price.BanquetHall.QUERY)
    public Object listAll(NameQuery query) {
        return customerTagService.listAll(query);
    }

    @GetMapping("/{id}")
    @Secured(Authority.Price.BanquetHall.QUERY)
    public Object get(@PathVariable Long id) {
        return this.customerTagService.findById(id);
    }
}
