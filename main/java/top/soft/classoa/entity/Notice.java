package top.soft.classoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notice {
    /**
     * 主键
     */
    private Long noticeId;
    /**
     * 消息接收者工号
     */
    private Long receiverId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}