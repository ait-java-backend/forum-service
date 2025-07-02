package ait.cohort60.post.service.logging;

import ait.cohort60.post.dto.PostDto;
import ait.cohort60.post.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "Post Service")
@Aspect
public class PostServiceLogger {

    @Pointcut("execution(public * ait.cohort60.post.service.PostServiceImpl.*(Long)) && args(id)")
    public void findById(Long id) {}

    @Pointcut("@annotation(ait.cohort60.post.service.logging.PostLongger)")
    public void annotatePostLogger() {}

    @Pointcut("execution(public Iterable<ait.cohort60.post.dto.PostDto> ait.cohort60.post.service.PostServiceImpl.findPosts*(..))")
    public void bulkFindPostLogger() {}

    @Before("findById(id)")
    public void logFindById(Long id) {
        log.info("Finding post by id {}", id);
    }

    @AfterReturning("annotatePostLogger()")
    public void logAnnotatePostLogger(JoinPoint joinPoint) {
        log.info("Annotated by PostLogger method: {}, done", joinPoint.getSignature().getName());
    }

    @Around("bulkFindPostLogger()")
    public Object logBulkFindPostLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String str) {
                args[i] = str.toLowerCase();
            }
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(args);
        long end = System.currentTimeMillis();
        log.info("method: {}, time: {} ms" , joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
