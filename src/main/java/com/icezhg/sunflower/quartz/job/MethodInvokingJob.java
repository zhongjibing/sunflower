package com.icezhg.sunflower.quartz.job;

import com.icezhg.sunflower.pojo.TaskInfo;
import com.icezhg.sunflower.quartz.util.InvokeExpression;
import com.icezhg.sunflower.quartz.util.InvokeParameter;
import com.icezhg.sunflower.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionException;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class MethodInvokingJob extends QuartzJob {

    @Override
    protected void executeInternal(TaskInfo taskInfo) throws JobExecutionException {
        try {
            InvokeExpression expression = InvokeExpression.instanceOf(taskInfo.getInvokeTarget());
            String executableName = expression.getExecutableName();
            String methodName = expression.getMethodName();
            List<InvokeParameter> parameters = expression.getParameters();

            String paramStr = StringUtils.join(parameters.stream().map(InvokeParameter::toString).toList(), ",");
            log.info("try exec task: {}.{}({})", executableName, methodName, paramStr);

            Object target;
            try {
                target = ApplicationContextUtil.getBean(executableName);
            } catch (Exception ex1) {
                try {
                    target = Class.forName(executableName).getDeclaredConstructor().newInstance();
                } catch (Exception ex2) {
                    throw new Exception("executable instance not found: " + executableName);
                }
            }

            Class<?>[] paramTypes = parameters.stream().map(InvokeParameter::type).toArray(Class<?>[]::new);
            Method method = ReflectionUtils.findMethod(target.getClass(), methodName, paramTypes);
            Assert.notNull(method, "method not found: " + methodName);

            ReflectionUtils.makeAccessible(method);
            Object[] args = parameters.stream().map(InvokeParameter::value).toArray();
            ReflectionUtils.invokeMethod(method, target, args);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }


}
