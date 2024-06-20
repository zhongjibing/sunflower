package com.icezhg.sunflower.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulMethodInvokingJob extends MethodInvokingJob {
}
