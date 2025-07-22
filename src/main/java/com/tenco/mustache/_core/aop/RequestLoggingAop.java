package com.tenco.mustache._core.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class RequestLoggingAop {

    @Around("execution(* com.tenco.blog..*RestController.*(..))")
    public Object logRequestInfo(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        // HttpServletRequest 가져와야 쓸 수 있다!!!
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        String requestUri = request != null ? request.getRequestURI() : "Unknown URI";
        String httpMethod = request != null ? request.getMethod() : "Unknown Method";
        String methodName = joinPoint.getSignature().getName();

        log.info("Requset - URI : {}, Method : {}, Controller: {}",
                requestUri,httpMethod,methodName);
        return joinPoint.proceed();
    }
}
