package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.domain.DictData;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.DictDataInfo;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.DictQuery;
import com.icezhg.sunflower.service.DictDataService;
import com.icezhg.sunflower.service.DictTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/dict/data")
public class DictDataController {

    private final DictTypeService dictTypeService;

    private final DictDataService dictDataService;

    public DictDataController(DictTypeService dictTypeService, DictDataService dictDataService) {
        this.dictTypeService = dictTypeService;
        this.dictDataService = dictDataService;
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    @PreAuthorize("isAuthenticated()")
    public List<DictData> dictType(@PathVariable String dictType) {
        return dictTypeService.findDictDataByType(dictType);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + Authority.System.Dict.ADD + "')")
    @Operation(title = "dict data addition", type = OperationType.INSERT)
    public DictDataInfo add(@Validated @RequestBody DictDataInfo typeInfo) {
        return dictDataService.save(typeInfo);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('" + Authority.System.Dict.EDIT + "')")
    @Operation(title = "dict data modification", type = OperationType.UPDATE)
    public DictDataInfo edit(@Validated @RequestBody DictDataInfo typeInfo) {
        return dictDataService.update(typeInfo);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('" + Authority.System.Dict.DELETE + "')")
    @Operation(title = "dict data deletion", type = OperationType.DELETE)
    public int delete(@RequestBody List<Integer> dictTypeIds) {
        return dictDataService.deleteByIds(dictTypeIds);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + Authority.System.Dict.QUERY + "')")
    public PageResult list(DictQuery query) {
        return new PageResult(dictDataService.count(query), dictDataService.find(query));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Authority.System.Dict.QUERY + "')")
    public DictDataInfo get(@PathVariable Integer id) {
        return dictDataService.findById(id);
    }
}
