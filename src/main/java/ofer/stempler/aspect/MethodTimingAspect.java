package ofer.stempler.aspect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j2;
import ofer.stempler.model.Request;

@Aspect
@Component
@Log4j2
public class MethodTimingAspect {
    private static final String DOT = ".";
    private static final List<String> TIMING_DEBUG_PRINTS_EXCLUDES =
            Collections.unmodifiableList(Arrays.asList("Exclusionclass1.method", "Exclusionclass2.method"));
    
    
	@Around("@annotation(MethodTiming)")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable  {
        Object result = null;

        StopWatch watch = new StopWatch();
        try {
            watch.start();
            result = joinPoint.proceed();
        } finally {
            watch.stop();
            long executionTime = watch.getLastTaskTimeMillis();
            
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            String classAndMethodName = className + DOT + methodName;

            boolean requestFound = false;
            
            Object[] methodArgs = joinPoint.getArgs();
            if (methodArgs != null) {
                for (Object arg : methodArgs) {
                    if (arg instanceof Request) {
                        // inject time back into TransactionContext
                    	Request request = (Request) arg;
                    	request.addMethodTiming(classAndMethodName, executionTime);
                        
                        requestFound = true;
                        break;
                    }
                }
            }
            
//            if (!requestFound) {
//            	Request request = Request.THREAD_LOCAL.get();
//                if (request != null) {
//                	log.debug("Execution time captured for {}: {} ms", classAndMethodName, executionTime);
//                	request.addMethodTiming(classAndMethodName, executionTime);
//                } else if(!TIMING_DEBUG_PRINTS_EXCLUDES.contains(classAndMethodName)) {
//                    // filter out prints from debug that have no Requests in them,
//                    // such as during ResourceCheck
//                    log.debug("Execution time captured for {}: {} ms", classAndMethodName, executionTime);
//                }
//            }
        }
        return result;
	}
}
