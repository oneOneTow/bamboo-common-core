package com.luzhiqing.common.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luzhiqing.common.web.annotation.RawRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/28 15:44
 */
@ControllerAdvice(basePackages = "com.luzhiqing.bamboo")
public class RestResponseAdvice implements ResponseBodyAdvice<Object> {

    private Logger logger = LoggerFactory.getLogger(RestResponseAdvice.class);

    @Autowired
    private ObjectMapper jsonMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.hasMethodAnnotation(RawRestResponse.class)
                || AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), RawRestResponse.class)
                || returnType.getParameterType().isAssignableFrom(RestResult.class)) {
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(//返回的实体
                                  Object body,
                                  //
                                  MethodParameter returnType,
                                  //
                                  MediaType selectedContentType,
                                  //
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  //
                                  ServerHttpRequest serverHttpRequest,
                                  //
                                  ServerHttpResponse serverHttpResponse) {
        if (body instanceof RestResult || "org.springframework.http.converter.ByteArrayHttpMessageConverter".equals(selectedConverterType.getName())) {
            return body;
        } else if ("org.springframework.http.converter.StringHttpMessageConverter".equals(selectedConverterType.getName())) {
            RestResult<Object> result = new RestResult<>(body);
            try {
                // Controller返回String类型时使用SHMC转换消息,此时ContentType为TEXT_HTML,转换为JSON
                serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                return jsonMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                logger.error("Json 转换错误", e);
                return "";
            }
        } else {
            return new RestResult<>(body);
        }
    }
}
