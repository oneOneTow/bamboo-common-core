package com.luzhiqing.common.token;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description:
 * @version:
 * @Author: 陆志庆
 * @CreateDate: 2019/10/5 18:28
 */
public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = TokenUtil.acquireToken((HttpServletRequest) servletRequest);
        boolean haveAuth = TokenUtil.verify(token,"");
        if(haveAuth){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //TODO:403
            servletResponse.getOutputStream();
        }
    }

    @Override
    public void destroy() {

    }
}
