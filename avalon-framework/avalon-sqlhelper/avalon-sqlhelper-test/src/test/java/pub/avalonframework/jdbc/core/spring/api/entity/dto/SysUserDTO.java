package pub.avalonframework.jdbc.core.spring.api.entity.dto;

import pub.avalonframework.jdbc.core.spring.dao.Eq;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author baichao
 */
@Table(name = "sys_user")
public class SysUserDTO {

    @Eq
    @Column(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}