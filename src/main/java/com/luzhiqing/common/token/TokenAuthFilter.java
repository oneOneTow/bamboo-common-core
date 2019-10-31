package com.luzhiqing.common.token;


import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/29 11:24
 */
public class TokenAuthFilter implements Filter {
    private final static String TOKEN_KEY = "token";
    private String password = "luzhiqing";
    private List<String> excludeUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("token filter init ....");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        boolean exclude = false;
        for (String url : excludeUrls) {
            if (request.getRequestURI().contains(url)) {
                exclude = true;
            }
        }
        if(!exclude){
            Cookie[] cookies = request.getCookies();
            String token = null;
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (TOKEN_KEY.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
            if (StringUtils.isBlank(token)) {
                throw new RuntimeException("用户未授权");
            }
            TokenUtil.verify(token, password);
            Payload payload = TokenUtil.acquirePayload(token);
            Map<String, Object> extraParams = new HashMap<>(1);
            extraParams.put("uid", payload.getUid());
            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
            requestParameterWrapper.addParameters(extraParams);
            filterChain.doFilter(requestParameterWrapper, servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void addExclueUrl(String excludeUrl) {
        this.excludeUrls.add(excludeUrl);
    }

    public void addExclueUrls(List<String> excludeUrls) {
        this.excludeUrls.addAll(excludeUrls);
    }


}
