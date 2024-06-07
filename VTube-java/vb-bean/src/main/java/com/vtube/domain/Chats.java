package com.vtube.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 聊天会话表
 * @TableName vt_chats
 */
@TableName(value ="vt_chats")
@Data
public class Chats implements Serializable {
    /**
     * 会话ID
     */
    @TableId(value = "chat_id", type = IdType.AUTO)
    private String chatId;

    /**
     * 参与者1 ID
     */
    @TableField(value = "participant_one")
    private String participantOne;

    /**
     * 参与者2 ID
     */
    @TableField(value = "participant_two")
    private String participantTwo;

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
        Chats other = (Chats) that;
        return (this.getChatId() == null ? other.getChatId() == null : this.getChatId().equals(other.getChatId()))
            && (this.getParticipantOne() == null ? other.getParticipantOne() == null : this.getParticipantOne().equals(other.getParticipantOne()))
            && (this.getParticipantTwo() == null ? other.getParticipantTwo() == null : this.getParticipantTwo().equals(other.getParticipantTwo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChatId() == null) ? 0 : getChatId().hashCode());
        result = prime * result + ((getParticipantOne() == null) ? 0 : getParticipantOne().hashCode());
        result = prime * result + ((getParticipantTwo() == null) ? 0 : getParticipantTwo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chatId=").append(chatId);
        sb.append(", participantOne=").append(participantOne);
        sb.append(", participantTwo=").append(participantTwo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}