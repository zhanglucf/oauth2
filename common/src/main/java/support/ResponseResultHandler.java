package support;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static support.ResultCode.getResultCode;


@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    private static final String RESPOND_RESULT_ANN = "RESPOND_RESULT_ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPOND_RESULT_ANN);
        return responseResultAnn == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (body instanceof HashMap && ((HashMap) body).get("customFlag") != null) {
            Map m = (HashMap) body;
            if ("Y".equals((m).get("customFlag"))) {
                m.remove("customFlag");
                return Result.failure(getResultCode((Integer) m.get("code")), body);
            } else if ("N".equals(((HashMap) body).get("customFlag"))) {
                m.remove("customFlag");
                Result result = new Result();
                result.setCode((Integer) m.get("code"));
                result.setMessage(m.get("message") == null ? "" : m.get("message").toString());
                result.setStatus(m.get("status").toString());
                result.setData(body);
                return result;
            } else {
                JSONObject json = new JSONObject();
                return json.toJSONString(Result.success(body));
            }
        } else if (body instanceof String) {
            return  new JSONObject().toJSONString(Result.success(body));
        } else if (body instanceof Date) {
            return Result.success(body);
        }
        return Result.success(body);
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        log.error("全局异常捕捉处理 系统异常", ex);
        Map map = new LinkedHashMap();
        map.put("code", 500);
        map.put("status", "fail");
        map.put("message", ex.getMessage());
        map.put("error", ex.toString());
        map.put("customFlag", "N");
        return map;
    }

    /**
     * 自定义异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public Map customExceptionHandler(Exception ex) {
        CustomException cex = (CustomException) ex;
        log.error(cex.getMessage(), ex);
        Map map = new HashMap();
        map.put("status", "fail");
        map.put("code", cex.getCode());
        map.put("message", cex.getMessage());
        map.put("error", cex.getDesc());
        map.put("customFlag", "Y");
        return map;
    }

}
