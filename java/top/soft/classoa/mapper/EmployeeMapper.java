package top.soft.classoa.mapper;

import top.soft.classoa.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee selectById(Long id);
    /**
     * 根据参数组合动态查询
     * @param params 参数
     * @return 查询结果(列表)
     */
    List<Employee> selectByParams(Map<String,Object> params);
}
