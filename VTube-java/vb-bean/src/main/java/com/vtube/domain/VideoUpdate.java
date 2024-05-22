package com.vtube.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 番剧更新表
 * @TableName vt_video_update
 */
@TableName(value ="vt_video_update")
@Data
public class VideoUpdate implements Serializable {
    /**
     * 更新ID
     */
    @TableId(value = "update_id", type = IdType.AUTO)
    private Integer updateId;

    /**
     * 星期几
     */
    @TableField(value = "week_day")
    private Object weekDay;

    /**
     * 视频ID
     */
    @TableField(value = "video_id")
    private Integer videoId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

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
        VideoUpdate other = (VideoUpdate) that;
        return (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getWeekDay() == null ? other.getWeekDay() == null : this.getWeekDay().equals(other.getWeekDay()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getWeekDay() == null) ? 0 : getWeekDay().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", updateId=").append(updateId);
        sb.append(", weekDay=").append(weekDay);
        sb.append(", videoId=").append(videoId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}