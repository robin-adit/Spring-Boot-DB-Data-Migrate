package com.migrate.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//This class will send an email every time the migrate method in the DataMigrationController is called

@Aspect
public class ShareStatus {

    @Pointcut("execution(* com.migrate.controller.*)")
    private void sendMail()
    {

    }
}
