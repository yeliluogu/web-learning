package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.Department;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 14:30
 */
 class DepartmentServiceTest {
    private final DepartmentService departmentService = new DepartmentService();
    @Test
    void selectById(){
        Department department = departmentService.selectById(3L);
        System.out.println(department);
    }
}
