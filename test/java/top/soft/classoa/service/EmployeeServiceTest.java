package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.Employee;
import top.soft.classoa.mapper.EmployeeMapper;
import top.soft.classoa.utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 14:05
 */
 class EmployeeServiceTest {
    private final EmployeeService employeeService=new EmployeeService();
    @Test
    void selectById() {
        Employee employee=employeeService.selectById(1L);
        System.out.println(employee);
    }
    @Test
            void setLeader(){
        Employee leader=employeeService.selectLeader(4L);
        System.out.println(leader);
    }


     @Test
    void selectByParams() {
         MybatisUtils.executeQuery(sqlSession -> {
             Map<String, Object> params = new HashMap<>();
             params.put("departmentId", 2);
             params.put("level",7);
             EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
             List<Employee> leaders = employeeMapper.selectByParams(params);
             System.out.println(leaders.get(0));
             return leaders.get(0);
         });
     }
}
