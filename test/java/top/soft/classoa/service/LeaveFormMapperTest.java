package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.LeaveForm;
import top.soft.classoa.mapper.LeaveFormMapper;
import top.soft.classoa.utils.MybatisUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 15:20
 */
class LeaveFormMapperTest {
    @Test
    void insert() {
        MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(3L);
            form.setFormType(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;
            Date endTime = null;
            try{
                startTime = sdf.parse("2024-12-03 10:00:00");
                endTime = sdf.parse("2024-12-12 10:00:00");
            }catch (ParseException e){
                e.printStackTrace();
            }
            form.setStartTime(startTime);
            form.setEndTime(endTime);
            form.setReason("个人私事");
            form.setCreateTime(new Date());
            form.setState("processing");
          leaveFormMapper.insert(form);
            return null;
        });
    }
}
