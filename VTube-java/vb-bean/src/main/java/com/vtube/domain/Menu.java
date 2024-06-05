package com.vtube.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 菜单表
 * @TableName vt_sys_menu
 */
@TableName(value ="vt_sys_menu")
@Data
public class Menu implements Serializable {
    /**
     * 主键ID，自增
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 父菜单ID，顶级菜单为NULL
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @TableField(value = "type")
    private String type;

    /**
     * 菜单标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 菜单路由路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 路由name
     */
    @TableField(value = "name")
    private String name;

    /**
     * 前端Vue组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 菜单排序顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 菜单是否可见, 1为可见，0为不可见
     */
    @TableField(value = "visible")
    private Integer visible;

    /**
     * 权限标识，用于权限控制
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 当前路由是否缓存 0：否 1：是
     */
    @TableField(value = "is_keep_alive")
    private Integer isKeepAlive;

    /**
     * 路由重定向地址
     */
    @TableField(value = "redirect")
    private String redirect;

    /**
     * 是否全屏
     */
    @TableField(value = "is_full")
    private Integer isFull;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getComponent() == null ? other.getComponent() == null : this.getComponent().equals(other.getComponent()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getVisible() == null ? other.getVisible() == null : this.getVisible().equals(other.getVisible()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()))
            && (this.getIsKeepAlive() == null ? other.getIsKeepAlive() == null : this.getIsKeepAlive().equals(other.getIsKeepAlive()))
            && (this.getRedirect() == null ? other.getRedirect() == null : this.getRedirect().equals(other.getRedirect()))
            && (this.getIsFull() == null ? other.getIsFull() == null : this.getIsFull().equals(other.getIsFull()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getVisible() == null) ? 0 : getVisible().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        result = prime * result + ((getIsKeepAlive() == null) ? 0 : getIsKeepAlive().hashCode());
        result = prime * result + ((getRedirect() == null) ? 0 : getRedirect().hashCode());
        result = prime * result + ((getIsFull() == null) ? 0 : getIsFull().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", parentId=").append(parentId);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", icon=").append(icon);
        sb.append(", path=").append(path);
        sb.append(", name=").append(name);
        sb.append(", component=").append(component);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", visible=").append(visible);
        sb.append(", permission=").append(permission);
        sb.append(", isKeepAlive=").append(isKeepAlive);
        sb.append(", redirect=").append(redirect);
        sb.append(", isFull=").append(isFull);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}