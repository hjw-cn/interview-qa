package com.interview.qa.controller.model.builder;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

import java.util.List;

public class ResponseBuilder {
    public static Response with(Boolean success,String errCode,String errMessage,Object data) {
        Response response = new Response();
        response.setSuccess(success);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> MultiResponse<T> withMulti(Boolean success,String errCode,String errMessage,List<T> data) {

        MultiResponse multiResponse = new MultiResponse();
        multiResponse.setSuccess(success);
        multiResponse.setErrCode(errCode);
        multiResponse.setErrMessage(errMessage);
        multiResponse.setData(data);
        return multiResponse;
    }

    public static <T> SingleResponse<T> withSingle(Boolean success,String errCode,String errMessage,T data) {

        SingleResponse singleResponse = new SingleResponse();
        singleResponse.setSuccess(success);
        singleResponse.setErrCode(errCode);
        singleResponse.setErrMessage(errMessage);
        singleResponse.setData(data);
        return singleResponse;
    }
}
