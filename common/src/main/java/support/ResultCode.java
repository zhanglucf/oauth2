package support;

public enum ResultCode {
    SUCCESS(1,"success"),
    FAILURE(500,"fail"),
    FAILURE_1001_DATA_NOT_FOUND(1001,"自定义异常 FAILURE_1001_DATA_NOT_FOUND"),
    FAILURE_1002_FIELD_IS_EMPTY(1002,"自定义异常 FAILURE_1002_FIELD_IS_EMPTY"),
    FAILURE_1003_FIELD_IS_MISS(1003,"自定义异常 FAILURE_1003_FIELD_IS_MISS"),
    FAILURE_1004_PARAM_ERROR(1004,"自定义异常 参数异常，参数不在约定范畴之内"),
    FAILURE_1005_DATA_XXXX(1005,"自定义异常 FAILURE_1005_DATA_XXXX"),
    FAILURE_1006_DATA_XXXX(1006,"自定义异常 FAILURE_1006_DATA_XXXX"),
    FAILURE_1007_DATA_XXXX(1007,"自定义异常 FAILURE_1007_DATA_XXXX");



    /**
     *   1000-1999 参数不合法
     *   2000-2999 业务逻辑有误
     *   3000-3999 其他业务异常
     */
    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultCode getResultCode(Integer code){
        ResultCode[] values = ResultCode.values();
        for (ResultCode resultCode: values) {
            if (resultCode.code().intValue() == code.intValue()) {
                return resultCode;
            }
        }
        return null;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }


}
