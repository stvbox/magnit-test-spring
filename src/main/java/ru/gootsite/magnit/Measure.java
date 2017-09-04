package ru.gootsite.magnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class Measure {
    public Object measureTime(ProceedingJoinPoint joinPoint ) {
        long start = System.currentTimeMillis();

        Object output = null;
        try {
            output = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        
        System.out.println("Выполнение метода ["+joinPoint.getSignature()+"] время выполнения: " + time);

         return output;
    }
}