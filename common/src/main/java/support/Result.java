package support;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Integer code;
    private String message;
    private String status;
    private Object data;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.data = resultCode.code();
        this.message = resultCode.message();
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(ResultCode.SUCCESS.message());
        result.setStatus("success");
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(ResultCode.SUCCESS.message());
        result.setStatus("success");
        result.setData(data);
        return result;
    }
    public static Result success(ResultCode resultCode) {
        Result result = new Result(resultCode);
        result.setStatus("success");
        return result;
    }

    public static Result success(ResultCode resultCode, Object data) {
        Result result = new Result(resultCode, data);
        result.setStatus("success");
        return result;
    }

    public static Result failure() {
        Result result = new Result();
        result.setCode(ResultCode.FAILURE.code());
        result.setMessage(ResultCode.FAILURE.message());
        result.setStatus("fail");
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result(resultCode);
        result.setStatus("fail");
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result(resultCode, data);
        result.setStatus("fail");
        return result;
    }

}
