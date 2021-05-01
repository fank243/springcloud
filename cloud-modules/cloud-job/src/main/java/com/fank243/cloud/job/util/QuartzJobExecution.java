package com.fank243.cloud.job.util;

import org.quartz.JobExecutionContext;

import com.fank243.cloud.job.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author FanWeiJie \n @date 2021-04-05 23:41:10
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
