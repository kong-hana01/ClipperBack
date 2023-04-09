package com.cliper.store.service.response;

import lombok.Getter;

@Getter
public class Response extends ResponseEmpty{

    private Object data;

    public Response(ExceptionCodeProd exceptionCodeProd, Object data) {
        super(exceptionCodeProd);
        this.data = data;
    }
}
