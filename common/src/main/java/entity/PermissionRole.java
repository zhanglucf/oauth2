package entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Data
@Table(name = "sys_permission_role")
public class PermissionRole extends BaseEntity  {

    @Column(name = "sys_role_id")
    private Long roleId;

    @Column(name = "sys_permission_id")
    private Long permissionId;
}
