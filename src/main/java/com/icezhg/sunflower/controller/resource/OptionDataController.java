package com.icezhg.sunflower.controller.resource;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.domain.OptionData;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.OptionDataInfo;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OptionQuery;
import com.icezhg.sunflower.service.OptionDataService;
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
@RequestMapping("/option/data")
public class OptionDataController {

    private final OptionTypeService optionTypeService;

    private final OptionDataService optionDataService;

    public OptionDataController(OptionTypeService optionTypeService, OptionDataService optionDataService) {
        this.optionTypeService = optionTypeService;
        this.optionDataService = optionDataService;
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{optionType}")
    public List<OptionData> optionType(@PathVariable String optionType) {
        return optionTypeService.findOptionDataByType(optionType);
    }

    @PostMapping
    @Secured(Authority.Resource.Option.ADD)
    @Operation(title = "option data addition", type = OperationType.INSERT)
    public OptionDataInfo add(@Validated @RequestBody OptionDataInfo typeInfo) {
        return optionDataService.save(typeInfo);
    }

    @PutMapping
    @Secured(Authority.Resource.Option.EDIT)
    @Operation(title = "option data modification", type = OperationType.UPDATE)
    public OptionDataInfo edit(@Validated @RequestBody OptionDataInfo typeInfo) {
        return optionDataService.update(typeInfo);
    }

    @DeleteMapping
    @Secured(Authority.Resource.Option.DELETE)
    @Operation(title = "option data deletion", type = OperationType.DELETE)
    public int delete(@RequestBody List<Integer> optionTypeIds) {
        return optionDataService.deleteByIds(optionTypeIds);
    }

    @GetMapping("/list")
    @Secured(Authority.Resource.Option.QUERY)
    public PageResult list(OptionQuery query) {
        return new PageResult(optionDataService.count(query), optionDataService.find(query));
    }

    @GetMapping("/{id}")
    @Secured(Authority.Resource.Option.QUERY)
    public OptionDataInfo get(@PathVariable Integer id) {
        return optionDataService.findById(id);
    }
}
