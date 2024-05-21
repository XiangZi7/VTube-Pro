package com.vtube.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 视频选集表
 * @TableName vt_video_episode
 */
@TableName(value ="vt_video_episode")
@Data
public class VideoEpisode implements Serializable {
    /**
     * 选集ID
     */
    @TableId(value = "episode_id", type = IdType.AUTO)
    private Integer episodeId;

    /**
     * 视频ID
     */
    @TableField(value = "video_id")
    private Integer videoId;

    /**
     * 选集编号
     */
    @TableField(value = "episode_number")
    private Integer episodeNumber;

    /**
     * 选集标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 视频地址
     */
    @TableField(value = "video_path")
    private String videoPath;

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
        VideoEpisode other = (VideoEpisode) that;
        return (this.getEpisodeId() == null ? other.getEpisodeId() == null : this.getEpisodeId().equals(other.getEpisodeId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getEpisodeNumber() == null ? other.getEpisodeNumber() == null : this.getEpisodeNumber().equals(other.getEpisodeNumber()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getVideoPath() == null ? other.getVideoPath() == null : this.getVideoPath().equals(other.getVideoPath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEpisodeId() == null) ? 0 : getEpisodeId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getEpisodeNumber() == null) ? 0 : getEpisodeNumber().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getVideoPath() == null) ? 0 : getVideoPath().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", episodeId=").append(episodeId);
        sb.append(", videoId=").append(videoId);
        sb.append(", episodeNumber=").append(episodeNumber);
        sb.append(", title=").append(title);
        sb.append(", videoPath=").append(videoPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}