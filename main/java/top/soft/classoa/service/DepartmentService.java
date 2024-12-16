package top.soft.classoa.service;

import top.soft.classoa.entity.Department;
import top.soft.classoa.entity.Employee;
import top.soft.classoa.mapper.DepartmentMapper;
import top.soft.classoa.mapper.EmployeeMapper;
import top.soft.classoa.utils.MybatisUtils;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 14:25
 */
public class DepartmentService {
   public Department selectById(Long departmentid) {
       return(Department)MybatisUtils.executeQuery(sqlSession ->
               sqlSession.getMapper(DepartmentMapper.class).selectById(departmentid));
   }
}
