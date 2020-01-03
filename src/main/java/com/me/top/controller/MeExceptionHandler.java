package com.me.top.controller;

import com.me.top.util.MeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description
 * @ClassName MeExceptionHandler
 * @Author joyonline
 * @Date 2019-05-09 17:20
 * @Version 1.0
 **/
@RestControllerAdvice
public class MeExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(MeExceptionHandler.class);

    /**
     *  拦截Exception类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public MeResult exceptionHandler(Exception e){
        logger.error("exception{}",e.getCause());
        return MeResult.build(MeResult.ERROR,e.getMessage());
    }

    /**
     *  拦截MeException类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MeException.class)
    public MeResult meException(MeException e){
        logger.error("visionException{}",e.getMessage());
        return MeResult.build(MeResult.ERROR,e.getMessage());
    }

}
