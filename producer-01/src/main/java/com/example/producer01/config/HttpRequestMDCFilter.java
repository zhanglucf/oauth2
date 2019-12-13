package com.example.producer01.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

/**
 * 自定义Filter  初始化每次请求的MDC数据
 *
 * @author zhenhua zhang
 * @data 2019/11/20
 */
public class HttpRequestMDCFilter implements Filter {
    /**
     * 是否开启cookies映射，如果开启，那么将可以在logback中使用
     * %X{_C_:<name>}来打印此cookie，比如：%X{_C_:user};
     * 如果开启此选项，还可以使用如下格式打印所有cookies列表:
     * 格式为：key:value,key:value
     * %X{requestCookies}
     */

    private boolean mappedCookies;
    /**
     * 是否开启headers映射，如果开启，将可以在logback中使用
     * %X{_H_:<header>}来打印此header,比如：%X{_H_:X-Forwarded-For}
     * 如果开启此参数，还可以使用如下格式打印所有的headers列表:
     * 格式为：key:value,key:value
     * %X{requestHeaders}
     */
    private boolean mappedHeaders;

    /**
     * 是否开启parameters映射，此parameters可以为Get的查询字符串，可以为post的Form Entries
     * %X{_P_:<parameter>}来答应此参数值，比如：%X{_P_:page}
     * 如果开启此参数，还可以使用如下格式打印所有的headers列表:
     * 格式为：key:value,key:value
     * %X{requestParameters}
     */
    private boolean mappedParameters;

    private String localIp;//本机IP


    //all headers,content as key:value,key:value
    private static final String HEADERS_CONTENT = "requestHeaders";

    //all cookies
    private static final String COOKIES_CONTENT = "requestCookies";

    //all parameters
    private static final String PARAMETERS_CONTENT = "requestParameters";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        mappedCookies = Boolean.valueOf(filterConfig.getInitParameter("mappedCookies"));
        mappedHeaders = Boolean.valueOf(filterConfig.getInitParameter("mappedHeaders"));
        mappedParameters = Boolean.valueOf(filterConfig.getInitParameter("mappedParameters"));
        //getLocalIp
        localIp = getLocalIp();
    }

    private String getLocalIp() {
        try {
            //一个主机有多个网络接口
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = netInterfaces.nextElement();
                //每个网络接口,都会有多个"网络地址",比如一定会有loopback地址,会有siteLocal地址等.以及IPV4或者IPV6    .
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    //get only :172.*,192.*,10.*
                    if (address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            //
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        try {
            mdc(hsr);
        } catch (Exception e) {
            //
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();//must be,threadLocal
        }

    }

    private void mdc(HttpServletRequest hsr) {
        Map<String, String[]> parameterMap = hsr.getParameterMap();
        String parames = JSON.toJSONString(parameterMap);
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        String applicationName = applicationContext.getApplicationName();
        MDC.put("appname", "".equals(applicationName) ? "myapplication" : applicationName);
        MDC.put("parames", parames);
        MDC.put("X-B3-TraceId", UUID.randomUUID().toString());
        MDC.put(MDCConstants.LOCAL_IP_MDC_KEY, localIp);
        MDC.put(MDCConstants.REQUEST_ID_MDC_KEY, hsr.getHeader(MDCConstants.REQUEST_ID_HEADER));
        String requestSeq = hsr.getHeader(MDCConstants.REQUEST_SEQ_HEADER);
        if (requestSeq != null) {
            String nextSeq = requestSeq + "0";//seq will be like:000,real seq is the number of "0"
            MDC.put(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY, nextSeq);
        } else {
            MDC.put(MDCConstants.NEXT_REQUEST_SEQ_MDC_KEY, "0");
        }
        MDC.put(MDCConstants.REQUEST_SEQ_MDC_KEY, requestSeq);
        MDC.put(MDCConstants.TIMESTAMP, "" + System.currentTimeMillis());
        MDC.put(MDCConstants.URI_MDC_KEY, hsr.getRequestURI());

        if (mappedHeaders) {
            Enumeration<String> e = hsr.getHeaderNames();
            if (e != null) {
                //
                while (e.hasMoreElements()) {
                    String header = e.nextElement();
                    String value = hsr.getHeader(header);
                    MDC.put(MDCConstants.HEADER_KEY_PREFIX + header, value);
                    //
                }

            }
        }

        if (mappedCookies) {
            Cookie[] cookies = hsr.getCookies();
            if (cookies != null && cookies.length > 0) {
                //
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    String value = cookie.getValue();
                    MDC.put(MDCConstants.COOKIE_KEY_PREFIX + name, value);
                    //
                }

            }
        }

        if (mappedParameters) {
            Enumeration<String> e = hsr.getParameterNames();
            if (e != null) {
                //
                while (e.hasMoreElements()) {
                    String key = e.nextElement();
                    String value = hsr.getParameter(key);
                    MDC.put(MDCConstants.PARAMETER_KEY_PREFIX + key, value);
                    //
                }

            }
        }
    }

    @Override
    public void destroy() {

    }
}
