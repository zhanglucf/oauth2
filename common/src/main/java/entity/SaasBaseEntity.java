package entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
@Data
public class SaasBaseEntity implements Serializable {

    private Long id;
    /**
     * 记录创建时间
     */
    @Column(name = "insert_time")
    private Date insertTime;

    /**
     * 记录更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 记录创建者ID
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JSONField(serialize = true)
//    @JsonIgnore
    @Column(name = "insert_by")
    private Long insertBy;

    /**
     * 记录更新者ID
     */

//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JSONField(name = "updateBy",serialize = false)
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 版本
     */
    @Version
    @Column(name = "version")
    private Long version;

    @JSONField(name = "updateBy",serialize = false)
    public Long getUpdateBy() {
        return updateBy;
    }

    @JSONField(name = "updateBy",serialize = false)
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
