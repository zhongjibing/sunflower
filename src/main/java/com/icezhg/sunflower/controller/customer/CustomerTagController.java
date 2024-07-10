package com.icezhg.sunflower.controller.customer;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.CustomerTagInfo;
import com.icezhg.sunflower.pojo.PageResult;
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
    @Secured(Authority.Customer.Tags.ADD)
    @Operation(title = "banquet halls addition", type = OperationType.INSERT)
    public Object add(@Validated @RequestBody CustomerTagInfo info) {
        return this.customerTagService.insert(info);
    }

    @PutMapping
    @Secured(Authority.Customer.Tags.EDIT)
    @Operation(title = "banquet halls modification", type = OperationType.UPDATE)
    public Object edit(@Validated @RequestBody CustomerTagInfo info) {
        return this.customerTagService.update(info);
    }

    @DeleteMapping
    @Secured(Authority.Customer.Tags.DELETE)
    @Operation(title = "banquet halls deletion", type = OperationType.DELETE)
    public void delete(@RequestBody List<Integer> tagIds) {
        this.customerTagService.deleteByIds(tagIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Customer.Tags.QUERY)
    public PageResult list(NameQuery query) {
        return new PageResult(customerTagService.count(query), customerTagService.find(query));
    }

    @GetMapping("/all")
    public Object listAll() {
        return customerTagService.listAll();
    }

    @GetMapping("/{id}")
    @Secured(Authority.Customer.Tags.QUERY)
    public Object get(@PathVariable Integer id) {
        return this.customerTagService.findById(id);
    }
}
