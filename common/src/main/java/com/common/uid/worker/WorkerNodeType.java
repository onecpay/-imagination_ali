package com.common.uid.worker;

/** @author Administrator */
public enum WorkerNodeType {

  /** CONTAINER */
  CONTAINER("CONTAINER", 1),
  /** ACTUAL */
  ACTUAL("ACTUAL", 2);

  WorkerNodeType(String s, Integer i) {
    this.desc = s;
    this.index = i;
  }

  private String desc;
  private Integer index;

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }
}
