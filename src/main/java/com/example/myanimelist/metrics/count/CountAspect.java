package com.example.myanimelist.metrics.count;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CountAspect {

    MeterRegistry meterRegistry;

    @Pointcut("execution(public * com.example.myanimelist.web.controller.AdminController.*(..))")
    public void apiMethods() {}

    @Around("apiMethods()")
    public Object handleCount(ProceedingJoinPoint jp) throws Throwable {
        Object result = jp.proceed();
        meterRegistry.counter("request_count").increment();

        return result;
    }
}
