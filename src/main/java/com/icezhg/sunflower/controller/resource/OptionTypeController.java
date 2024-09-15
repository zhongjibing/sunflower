package com.icezhg.sunflower.controller.resource;

import com.icezhg.commons.exception.ErrorCodeException;
import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.OptionTypeInfo;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OptionQuery;
import com.icezhg.sunflower.service.OptionTypeService;
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
 * 数据字典信息
 */
@RestController
@RequestMapping("/option/type")
public class OptionTypeController {
    private final OptionTypeService optionTypeService;

    public OptionTypeController(OptionTypeService optionTypeService) {
        this.optionTypeService = optionTypeService;
    }

    @PostMapping
    @Secured(Authority.Resource.Option.ADD)
    @Operation(title = "option types addition", type = OperationType.INSERT)
    public OptionTypeInfo add(@Validated @RequestBody OptionTypeInfo typeInfo) {
        if (!optionTypeService.checkUnique(typeInfo)) {
            throw new ErrorCodeException("", "option type is already exists");
        }
        return optionTypeService.save(typeInfo);
    }

    @PutMapping
    @Secured(Authority.Resource.Option.EDIT)
    @Operation(title = "option types modification", type = OperationType.UPDATE)
    public OptionTypeInfo edit(@Validated @RequestBody OptionTypeInfo typeInfo) {
        if (!optionTypeService.checkUnique(typeInfo)) {
            throw new ErrorCodeException("", "option type is already exists");
        }
        return optionTypeService.update(typeInfo);
    }

    @DeleteMapping
    @Secured(Authority.Resource.Option.DELETE)
    @Operation(title = "option types deletion", type = OperationType.DELETE)
    public int delete(@RequestBody List<Integer> optionTypeIds) {
        return optionTypeService.deleteByIds(optionTypeIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Resource.Option.QUERY)
    public PageResult list(OptionQuery query) {
        return new PageResult(optionTypeService.count(query), optionTypeService.find(query));
    }

    @GetMapping("/options")
    @Secured(Authority.Resource.Option.QUERY)
    public List<OptionTypeInfo> listOptions() {
        return optionTypeService.listOptions();
    }

    @GetMapping("/{id}")
    @Secured(Authority.Resource.Option.QUERY)
    public OptionTypeInfo get(@PathVariable Integer id) {
        return optionTypeService.findById(id);
    }
}
