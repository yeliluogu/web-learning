package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.ProcessFlow;
import top.soft.classoa.mapper.ProcessFlowMapper;
import top.soft.classoa.utils.MybatisUtils;

import java.util.Date;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 15:48
 */
class ProcessFlowMapperTest {
    @Test
    void insert(){
        MybatisUtils.executeQuery(sqlSession -> {
            ProcessFlowMapper mapper = sqlSession.getMapper(ProcessFlowMapper.class);
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setFormId(3L);
            processFlow.setOperatorId(3L);
            processFlow.setAction("apply");
            processFlow.setCreateTime(new Date());
            processFlow.setAuditTime(new Date());
            processFlow.setOrderNo(1);
            processFlow.setState("processing");
            processFlow.setIsLast(0);
            mapper.insert(processFlow);
            return null;
        });
    }
}
