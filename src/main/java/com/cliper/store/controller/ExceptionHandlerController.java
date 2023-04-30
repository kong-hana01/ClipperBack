package com.cliper.store.controller;

import com.cliper.store.service.response.ExceptionCodeProd;
import com.cliper.store.service.response.ResponseEmpty;
import com.cliper.store.service.response.ResponseMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object handleIllegalArgumentException(IllegalArgumentException exception) {
        ResponseMessage responseMessage = ResponseMessage.findByMessage(exception.getMessage());
        return new ResponseEmpty(ExceptionCodeProd.findByResponseMessage(responseMessage));
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public Object handleDataAccessException(DataAccessException exception) {
        return new ResponseEmpty(ExceptionCodeProd.DATABASE_ERROR);
    }
}
