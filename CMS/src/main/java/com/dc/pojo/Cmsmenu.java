package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Cmsmenu {
    @ApiModelProperty(value = "搜都分类id")
    private int menu_id;
    @ApiModelProperty(value = "等级分类编号")
    private int code;
    @ApiModelProperty(value = "分类名")
    private String menu_name;
    @ApiModelProperty(value = "逻辑删除")
    private int is_deleted;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cmsmenu{");
        sb.append("menu_id=").append(menu_id);
        sb.append(", code=").append(code);
        sb.append(", menu_name='").append(menu_name).append('\'');
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}
