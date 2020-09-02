package io.starskyoio.dbstore.server.ds;

import io.starskyoio.dbstore.common.constants.ResponseCode;
import io.starskyoio.dbstore.common.constants.ResponseMessage;
import io.starskyoio.dbstore.common.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 动态数据源切面
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class DynamicDataSourceAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(public * itc.dbstore.server.web..*(..))")
    public void changeDataSource() {
    }

    /**
     * 方法执行之前，根据请求头[x-ds]确定数据源
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("changeDataSource()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ds = attributes.getRequest().getHeader("x-ds");
        if (StringUtils.isEmpty(ds)) {
            log.error(ResponseMessage.REQUEST_HEADER_ERROR);
            throw ServerException.build(ResponseCode.REQUEST_HEADER_ERROR,
                    ResponseMessage.REQUEST_HEADER_ERROR);
        }

        if (!DynamicDataSourceContextHolder.containsDataSourceKey(ds)) {
            log.error(ds + ResponseMessage.DS_NOT_FOUND);
            throw ServerException.build(ResponseCode.DS_NOT_FOUND,
                    ds + ResponseMessage.DS_NOT_FOUND);
        }

        DynamicDataSourceContextHolder.setDataSourceKey(ds);
    }

    /**
     * 方法执行之后，清理数据源
     *
     * @param joinPoint
     * @throws Throwable
     */
    @After("changeDataSource()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

}
