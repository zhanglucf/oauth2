package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public enum LogOperateTypeEnum implements BaseEnum {
    LOGIN(1, "登录"),
    VIRTUAL_MACHINE(2, "桌面"),
    FILE(3, "文件"),
    APPLICATION(4, "应用"),
    USB(5, "USB"),
    VM_GROUP(6, "桌面池"),
    MEMBER(7, "用户"),
    MEMBER_GROUP(8, "用户组"),
    CLOUD_BOX(9, "云盒"),
    IMAGE(10, "镜像");

    private Integer code;
    private String description;

    LogOperateTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @JsonCreator()
    public static LogOperateTypeEnum getEnum(Integer code) {
        if (code != 1
                && code != 2
                && code != 3
                && code != 4
                && code != 5
                && code != 6
                && code != 7
                && code != 8
                && code != 9
                && code != 10) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        LogOperateTypeEnum[] values = LogOperateTypeEnum.values();
        for (LogOperateTypeEnum logOperateTypeEnum : values) {
            if (logOperateTypeEnum.code == code) {
                return logOperateTypeEnum;
            }
        }
        return null;
    }
}
