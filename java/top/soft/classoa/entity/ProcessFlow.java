package top.soft.classoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 15:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessFlow {
    //    主键，流程编号
    private Long processId;
    //    对应的请假单编号
    private Long formId;
    //    经办人的员工号
    private Long operatorId;
    //    操作类型：apply -  申请 audit - 审批
    private String action;
    //    本条处理流程的结果：approved - 同意 refused - 撤回（只有审批类型的流程才有）
    private String result;
    //   审批意见（只有审批类型的流程才有）
    private String reason;
    //    此流程数据的创建时间
    private Date createTime;
    //    审批时间（只有审理类型的流程才有）
    private Date auditTime;
    //    流程的序号
    private Integer orderNo;
    //    本条流程数据的状态：ready - 准备 process -正在处理 complete - 处理完成 cancel -  取消
    private String state;
    //    是否为对应请假单流程的最后一步： 0 -否 1-是
    private Integer isLast;
}

