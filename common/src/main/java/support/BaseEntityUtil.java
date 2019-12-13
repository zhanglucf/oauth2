package support;

import entity.SaasBaseEntity;

import java.util.Date;

/**
 * @author zhenhua zhang
 * @data 2019/12/3
 */
public class BaseEntityUtil {

    public static void perfectAttributeBeforeUpdate(SaasBaseEntity user) {
        user.setUpdateTime(new Date());
        user.setUpdateBy(99999L);
    }

    public static void perfectAttributeBeforeInsert(SaasBaseEntity user) {
        user.setInsertTime(new Date());
        user.setUpdateTime(new Date());
        user.setInsertBy(99999L);
        user.setUpdateBy(99999L);
        user.setVersion(1L);
    }
}
