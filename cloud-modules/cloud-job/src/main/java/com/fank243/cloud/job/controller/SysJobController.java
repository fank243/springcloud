package com.fank243.cloud.job.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.job.service.ISysJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fank243.cloud.common.core.exception.job.TaskException;
import com.fank243.cloud.common.core.utils.SecurityUtils;
import com.fank243.cloud.common.core.utils.poi.ExcelUtil;
import com.fank243.cloud.common.core.web.controller.BaseController;
import com.fank243.cloud.common.core.web.page.TableDataInfo;
import com.fank243.cloud.common.log.annotation.Log;
import com.fank243.cloud.common.log.enums.BusinessType;
import com.fank243.cloud.common.security.annotation.PreAuthorize;
import com.fank243.cloud.job.domain.SysJob;
import com.fank243.cloud.job.util.CronUtils;

/**
 * 调度任务信息操作处理
 * 
 * @author FanWeiJie \n @date 2021-04-05 23:41:10
 */
@RestController
@RequestMapping("/job")
public class SysJobController extends BaseController {
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @PreAuthorize(hasPermi = "monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob) {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * 导出定时任务列表
     */
    @PreAuthorize(hasPermi = "monitor:job:export")
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob) throws IOException {
        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        util.exportExcel(response, list, "定时任务");
    }

    /**
     * 获取定时任务详细信息
     */
    @PreAuthorize(hasPermi = "monitor:job:query")
    @GetMapping(value = "/{jobId}")
    public ResultInfo<?> getInfo(@PathVariable("jobId") Long jobId) {
        return ResultInfo.ok(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @PreAuthorize(hasPermi = "monitor:job:add")
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultInfo<?> add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(sysJob.getCronExpression())) {
            return ResultInfo.fail("cron表达式不正确");
        }
        sysJob.setCreateBy(SecurityUtils.getUsername());
        return ResultInfo.ok(jobService.insertJob(sysJob));
    }

    /**
     * 修改定时任务
     */
    @PreAuthorize(hasPermi = "monitor:job:edit")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultInfo<?> edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(sysJob.getCronExpression())) {
            return ResultInfo.fail("cron表达式不正确");
        }
        sysJob.setUpdateBy(SecurityUtils.getUsername());
        return ResultInfo.ok(jobService.updateJob(sysJob));
    }

    /**
     * 定时任务状态修改
     */
    @PreAuthorize(hasPermi = "monitor:job:changeStatus")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public ResultInfo<?> changeStatus(@RequestBody SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return ResultInfo.ok(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @PreAuthorize(hasPermi = "monitor:job:changeStatus")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public ResultInfo<?> run(@RequestBody SysJob job) throws SchedulerException {
        jobService.run(job);
        return ResultInfo.ok();
    }

    /**
     * 删除定时任务
     */
    @PreAuthorize(hasPermi = "monitor:job:remove")
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public ResultInfo<?> remove(@PathVariable Long[] jobIds) throws SchedulerException {
        jobService.deleteJobByIds(jobIds);
        return ResultInfo.ok();
    }
}
