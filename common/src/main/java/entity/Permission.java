package entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Data
@Table(name = "sys_permission")
public class Permission  extends BaseEntity {

    //权限名称
    private String name;

    //权限描述
    private String descritpion;

    //授权链接
    private String url;

    //父节点id
    private Long pid;

}
