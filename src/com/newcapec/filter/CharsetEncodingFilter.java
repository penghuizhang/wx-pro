package com.newcapec.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author guoxianwei 2017-07-07 19:04:30
 */
public class CharsetEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //     ַ   .
        request.setCharacterEncoding(encoding);

        // õ            µ   .
        chain.doFilter(request, response);
        //      .
        //System.out.println("CharacterEncodingFilter--end");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");


    }
}