package com.example.producer01.aspect;

import entity.SaasBaseEntity;
import support.BaseEntityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhenhua zhang
 * @data 2019/12/3
 */
@Aspect
@Component
public class BaseMethodAspect {

    @Pointcut("execution(* tk.mybatis.mapper.common..*.*(..))")
    public void tkPointCut() {
    }

    @Pointcut("execution(* zl.mybatis.best.practice.service..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        if (methodName.contains("update")) {
            Object[] args = jp.getArgs();
            if (args != null && args.length > 0) {
                for (Object obj : args) {
                    if (obj instanceof SaasBaseEntity) {
                        BaseEntityUtil.perfectAttributeBeforeUpdate((SaasBaseEntity) obj);
                    }
                }
            }
        }
        if (methodName.contains("insert")) {
            Object[] args = jp.getArgs();
            if (args != null && args.length > 0) {
                for (Object obj : args) {
                    if (obj instanceof SaasBaseEntity) {
                        BaseEntityUtil.perfectAttributeBeforeInsert((SaasBaseEntity) obj);
                    }
                }
            }
        }
        if (methodName.contains("save")) {
            Object[] args = jp.getArgs();
            if (args != null && args.length > 0) {
                for (Object obj : args) {
                    Long id = ((SaasBaseEntity) obj).getId();
                    if (id == null) {
                        BaseEntityUtil.perfectAttributeBeforeInsert((SaasBaseEntity) obj);
                    } else {
                        BaseEntityUtil.perfectAttributeBeforeUpdate((SaasBaseEntity) obj);
                    }
                }
            }
        }
    }

    @AfterReturning(value = "tkPointCut()", returning = "result")
    public void afterReturningMethod(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        if (methodName.contains("insert") || methodName.contains("update") || methodName.contains("save")) {
            if (result != null && (int) result == 0) {
                throw new RuntimeException("version is changed,this operation failed !");
            }
        }
    }

}
