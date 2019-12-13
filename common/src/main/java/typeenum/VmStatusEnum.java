package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public enum VmStatusEnum implements BaseEnum {
    READY(0, "已完成"),
    RESTARTED(1, "重启"),
    CLOSED(2, "关闭"),
    DELETED(3, "删除");

    private int code;
    private String description;

    VmStatusEnum(int code, String description) {
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
    public static VmStatusEnum getEnum(Integer code) {
        if (code != 0 && code != 1 && code != 2 && code != 3) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        VmStatusEnum[] values = VmStatusEnum.values();
        for (VmStatusEnum vmStatusEnum : values) {
            if (vmStatusEnum.code == code) {
                return vmStatusEnum;
            }
        }
        return null;
    }
}
