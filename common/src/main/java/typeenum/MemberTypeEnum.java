package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public enum MemberTypeEnum implements BaseEnum {
    SYSTEM_ADMIN(0, "系统管理员"),
    SECURITY_ADMIN(1, "保密管理员"),
    SAFETY_AUDITOR(2, "安全审计员"),
    ORDINARY_USER(3, "普通用户");

    private Integer code;
    private String description;

    MemberTypeEnum(Integer code, String description) {
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
    public static MemberTypeEnum getEnum(Integer code) {
        if (code != 0 && code != 1 && code != 2 && code != 3) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        MemberTypeEnum[] values = MemberTypeEnum.values();
        for (MemberTypeEnum memberTypeEnum : values) {
            if (memberTypeEnum.code == code) {
                return memberTypeEnum;
            }
        }
        return null;
    }
}
