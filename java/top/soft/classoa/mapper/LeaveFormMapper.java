package top.soft.classoa.mapper;

import org.apache.ibatis.annotations.Param;
import top.soft.classoa.entity.LeaveForm;

import java.util.List;
import java.util.Map;

public interface LeaveFormMapper {
    /**
     * 更新请假单
     * @param form 请假单对象
     */
    void update(LeaveForm form);
    /**
     * 新增请假单
     * @param form 请假单对象
     */
    void insert(LeaveForm form);
    /**
     * 根据请假单编号查找请假单
     *
     * @param formId 请假单编号
     * @return 请假单信息
     */
    LeaveForm selectById(Long formId);

    /**
     * 获取指定任务状态及指定经办人对应的请假单列表
     *
     * @param state      任务状态
     * @param operatorId 经办人id
     * @return 请假单列表
     */
    List<Map<String, Object>> selectByParams(@Param("state") String state, @Param("operatorId") Long operatorId);

    /**
     * 查询最新的一条请假单
     * @return
     */
    LeaveForm selectLatestForm();

}
