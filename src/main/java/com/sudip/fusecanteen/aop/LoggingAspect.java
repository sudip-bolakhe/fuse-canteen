package com.sudip.fusecanteen.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut(value="execution(* com.sudip.fusecanteen.resources.*.*(..) ) ||  execution(* com.sudip.fusecanteen.service.*.*(..) )")
    public void myPointCut(){}

    @Around("myPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
            ObjectMapper mapper = new ObjectMapper();
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().toString();
            Object[] array = joinPoint.getArgs();
            log.info( className + " : " + methodName + "()" + " with : "
                    + mapper.writeValueAsString(array));
            Object object = joinPoint.proceed();
            log.info(className + " : " + methodName + "()" + "Response : "
                    + mapper.writeValueAsString(object));
            return object;
        }

    @AfterThrowing(pointcut = "myPointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        log.error("Exception in " + className + ": " + methodName + " () with cause = "+ e.getCause());
    }
}

