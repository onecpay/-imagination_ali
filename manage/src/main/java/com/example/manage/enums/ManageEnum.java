package com.example.manage.enums;

/**
 * 权限设计
 *
 * @author 26500
 */
public enum ManageEnum {

    /**
     * 可用状态.
     */
    AVAILABLE("正常状态", 0),
    /**
     * 可用状态.
     */
    FREEZE("冻结状态", 1);

    private String name;
    private Integer index;

    ManageEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}
