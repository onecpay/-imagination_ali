package com.example.wechat.entity.security;

import com.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author ONEC
 */
@Table(name = "t_wechat_permission")
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity implements GrantedAuthority,Serializable {
    @Id
    private Long id;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 权限名
     */
    private String name;

    /**
     * 备注信息
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return null;
    }
}