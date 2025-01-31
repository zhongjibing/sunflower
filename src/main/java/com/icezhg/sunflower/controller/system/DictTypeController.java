package com.icezhg.sunflower.controller.system;

import com.icezhg.commons.exception.ErrorCodeException;
import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.DictTypeInfo;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.DictQuery;
import com.icezhg.sunflower.service.DictTypeService;
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
@RequestMapping("/dict/type")
public class DictTypeController {
    private final DictTypeService dictTypeService;

    public DictTypeController(DictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    @PostMapping
    @Secured(Authority.System.Dict.ADD)
    @Operation(title = "dict types addition", type = OperationType.INSERT)
    public DictTypeInfo add(@Validated @RequestBody DictTypeInfo typeInfo) {
        if (!dictTypeService.checkUnique(typeInfo)) {
            throw new ErrorCodeException("", "dict type is already exists");
        }
        return dictTypeService.save(typeInfo);
    }

    @PutMapping
    @Secured(Authority.System.Dict.EDIT)
    @Operation(title = "dict types modification", type = OperationType.UPDATE)
    public DictTypeInfo edit(@Validated @RequestBody DictTypeInfo typeInfo) {
        if (!dictTypeService.checkUnique(typeInfo)) {
            throw new ErrorCodeException("", "dict type is already exists");
        }
        return dictTypeService.update(typeInfo);
    }

    @DeleteMapping
    @Secured(Authority.System.Dict.DELETE)
    @Operation(title = "dict types deletion", type = OperationType.DELETE)
    public int delete(@RequestBody List<Integer> dictTypeIds) {
        return dictTypeService.deleteByIds(dictTypeIds);
    }

    @GetMapping("/list")
    @Secured(Authority.System.Dict.QUERY)
    public PageResult list(DictQuery query) {
        return new PageResult(dictTypeService.count(query), dictTypeService.find(query));
    }

    @GetMapping("/options")
    @Secured(Authority.System.Dict.QUERY)
    public List<DictTypeInfo> listOptions() {
        return dictTypeService.listOptions();
    }

    @GetMapping("/{id}")
    @Secured(Authority.System.Dict.QUERY)
    public DictTypeInfo get(@PathVariable Integer id) {
        return dictTypeService.findById(id);
    }
}
