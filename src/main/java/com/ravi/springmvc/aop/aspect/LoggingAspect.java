package com.ravi.springmvc.aop.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private static final Map<String, String> cache = new HashMap<>();

    /**
     * Using separate method as Pointcut
     */
    @Pointcut("execution(public String com.ravi.springmvc.controller.WelcomeController.sayHello(..)) || execution(public String com.ravi.springmvc.controller.WelcomeController.deleteUser(..))")
    public void loggingForMethods() {
        //Defines which methods need logging advice.
    }

    /**
     * Using Annotations as pointcut
     */
    @Before("@annotation(com.ravi.springmvc.annotation.Loggable)")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.debug("AOP: loggingBeforeAdvice using annotation as pointcut");
    }

    @Before("loggingForMethods()")
    public void loggingBeforeAdvice(JoinPoint joinPoint) {
        try {
            //Using joinPoint we can access method parameters.
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (nonNull(attributes)) {
                HttpServletRequest request = attributes.getRequest();
                logger.info("AOP: **************Start API Request**************");
                logger.info("AOP: URL : {}", request.getRequestURI());
                logger.info("AOP: HTTP_METHOD : {}", request.getMethod());
                logger.info("AOP: IP : {}", request.getRemoteAddr());
                logger.info("AOP: CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
                Object[] args = joinPoint.getArgs();
                logger.info("AOP: ARGS : {}", Arrays.toString(args));
                for (Object argument : args) {
                    logger.info("ARGS : " + new Gson().toJson(argument));
                }
                logger.info("AOP: loggingBeforeAdvice");
                logger.info("AOP: **************END API Request**************");
            }
        } catch (Exception e) {
            logger.error("AOP: Exception occurred in Before Advice:{}", e.getMessage());
        }

    }

    @After("execution(public String com.ravi.springmvc.controller.WelcomeController.sayHello(..))")
    public void loggingAfterAdvice(JoinPoint joinPoint) {
        try {
            logger.debug("AOP: loggingAfterAdvice");
        } catch (Exception e) {
            logger.error("AOP: Exception occurred in After Advice:{}", e.getMessage());
        }
    }

    @Around("execution(public String com.ravi.springmvc.controller.WelcomeController.sayHello(..))")
    public Object cacheMethodReturn(ProceedingJoinPoint pjp) {
        String cacheKey = pjp.getArgs()[1].toString();

        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        } else {
            String result = null;
            try {
                result = pjp.proceed().toString();
            } catch (Throwable e) {
                logger.error("AOP: Exception occurred in Before Advice:{}", e.getMessage());
            }
            cache.put(cacheKey, result);

            return result;
        }
    }
}
