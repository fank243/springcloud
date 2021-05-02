package com.fank243.cloud.gen.controller;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.text.Convert;
import com.fank243.cloud.common.core.web.controller.BaseController;
import com.fank243.cloud.common.core.web.page.TableDataInfo;
import com.fank243.cloud.common.log.annotation.Log;
import com.fank243.cloud.common.log.enums.BusinessType;
import com.fank243.cloud.common.security.annotation.PreAuthorize;
import com.fank243.cloud.gen.domain.GenTable;
import com.fank243.cloud.gen.domain.GenTableColumn;
import com.fank243.cloud.gen.service.IGenTableColumnService;
import com.fank243.cloud.gen.service.IGenTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RequestMapping("/gen")
@RestController
public class GenController extends BaseController {
    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @PreAuthorize(hasPermi = "tool:gen:list")
    @GetMapping("/list")
    public TableDataInfo genList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 修改代码生成业务
     */
    @PreAuthorize(hasPermi = "tool:gen:query")
    @GetMapping(value = "/{talbleId}")
    public ResultInfo<?> getInfo(@PathVariable Long talbleId) {
        GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return ResultInfo.ok(map);
    }

    /**
     * 查询数据库列表
     */
    @PreAuthorize(hasPermi = "tool:gen:list")
    @GetMapping("/db/list")
    public TableDataInfo dataList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{talbleId}")
    public TableDataInfo columnList(Long tableId) {
        TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @PreAuthorize(hasPermi = "tool:gen:list")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public ResultInfo<?> importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return ResultInfo.ok();
    }

    /**
     * 修改保存代码生成业务
     */
    @PreAuthorize(hasPermi = "tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo<?> editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return ResultInfo.ok();
    }

    /**
     * 删除代码生成
     */
    @PreAuthorize(hasPermi = "tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public ResultInfo<?> remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return ResultInfo.ok();
    }

    /**
     * 预览代码
     */
    @PreAuthorize(hasPermi = "tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    public ResultInfo<?> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return ResultInfo.ok(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @PreAuthorize(hasPermi = "tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @PreAuthorize(hasPermi = "tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public ResultInfo<?> genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return ResultInfo.ok();
    }

    /**
     * 同步数据库
     */
    @PreAuthorize(hasPermi = "tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    public ResultInfo<?> synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return ResultInfo.ok();
    }

    /**
     * 批量生成代码
     */
    @PreAuthorize(hasPermi = "tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
