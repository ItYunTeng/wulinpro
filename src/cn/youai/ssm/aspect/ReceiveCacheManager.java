package cn.youai.ssm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.youai.ssm.util.RedisClientTemplate;
import cn.youai.ssm.util.SerializeUtil;
@Component
@Aspect
public class ReceiveCacheManager {
	 @Autowired
	 private RedisClientTemplate redisClientCache;
	 
	 @Pointcut("execution(* cn.youai.ssm.dao..*.update*(..))||execution(* cn.youai.ssm.dao..*.dalete*(..))||execution(* cn.youai.ssm.dao..*.add*(..))||execution(* cn.youai.ssm.dao..*.insert*(..))")
	 public void deleteBasicCache(){
		 System.out.println("进入redis删除--------");
	 }
	 @Pointcut("execution(* cn.youai.ssm.dao..*.query*(..))||execution(* cn.youai.ssm.dao..*.select*(..))||execution(* cn.youai.ssm.dao..*.get*(..))")
	 public void getBasicCache(){
		 System.out.println("进入redis查询--------");
	 }
	 @Around("getBasicCache()")
	 public Object aroundGetBasicCache(ProceedingJoinPoint joinPoint){
		String clazzName=joinPoint.getSignature().getDeclaringTypeName();
		String methodName =joinPoint.getSignature().getName();
		String applId = null;
		Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0]);
        }
        //String redisKey = applId;
        byte[] bs = redisClientCache.get((clazzName+methodName+applId).getBytes());
        if(bs != null){
        	 Object objectFromRedis = SerializeUtil.unserialize(bs);
        	 return objectFromRedis;
        }
        /* Object objectFromRedis =  redisClientCache.get(clazzName);*/
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        redisClientCache.set((clazzName+methodName+applId).getBytes(), SerializeUtil.serialize(object));
		return object; 
	 }
	 @Around("deleteBasicCache()")
	 public Object aroundDeleteBasicCache(ProceedingJoinPoint joinPoint){
		String clazzName=joinPoint.getSignature().getDeclaringTypeName();
		String methodName =joinPoint.getSignature().getName();
		String applId = null;
		Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0]);
        }
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        redisClientCache.del(clazzName+methodName+applId);
		return object;
	 }
	 
}
