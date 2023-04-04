package com.cliper.store.response;

import lombok.Getter;

@Getter
public class Response extends ResponseEmpty{

    private Object data;

    public Response(ExceptionCode exceptionCode, Object data) {
        super(exceptionCode);
        this.data = data;
    }
}
