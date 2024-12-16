package top.soft.classoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveForm {
    //    主键，请假单编号
    private Long formId;
    //    员工编号（请假人）
    private Long employeeId;
    //    请假类型 1-事假 2-病假 3-工伤假 4-婚假 5-产假 6-丧假
    private Integer formType;
    //    请假开始时间
    private Date startTime;
    //    请假结束时间
    private Date endTime;
    //    请假理由
    private String reason;
    //    请假单创建时间
    private Date createTime;
    //    请假单状态：processing - 正在审批 approved - 审批已通过 refused - 审批被驳回
    private String state;
}
