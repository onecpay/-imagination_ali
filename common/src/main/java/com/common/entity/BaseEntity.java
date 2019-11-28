package com.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 模型基类
 *
 *
 */
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 3455146906203708046L;

  @JSONField(serialize = false)
  @JsonIgnore
  @Transient
  private String orderby;

  public String getOrderby() {
    return orderby;
  }

  public void setOrderby(String orderby) {
    this.orderby = orderby;
  }
}
