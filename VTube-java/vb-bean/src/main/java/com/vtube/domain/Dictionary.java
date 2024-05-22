package com.vtube.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 字典表
 * @TableName vt_dictionary
 */
@TableName(value ="vt_dictionary")
@Data
public class Dictionary implements Serializable {
    /**
     * 字典ID
     */
    @TableId(value = "dictionary_id", type = IdType.AUTO)
    private Integer dictionaryId;

    /**
     * 字典编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 字典值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 字典名称
     */
    @TableField(value = "label")
    private String label;

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
        Dictionary other = (Dictionary) that;
        return (this.getDictionaryId() == null ? other.getDictionaryId() == null : this.getDictionaryId().equals(other.getDictionaryId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDictionaryId() == null) ? 0 : getDictionaryId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dictionaryId=").append(dictionaryId);
        sb.append(", code=").append(code);
        sb.append(", value=").append(value);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}