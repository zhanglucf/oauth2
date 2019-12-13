package entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Data
@Table(name = "sys_role_user")
public class RoleUser extends BaseEntity{

    @Column(name = "sys_user_id")
    private Long userId;

    @Column(name = "sys_role_id")
    private Long roleId;
}
