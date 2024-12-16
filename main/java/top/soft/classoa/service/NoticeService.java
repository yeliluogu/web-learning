package top.soft.classoa.service;


import top.soft.classoa.entity.Notice;
import top.soft.classoa.mapper.NoticeMapper;
import top.soft.classoa.utils.MybatisUtils;

import java.util.List;

/**
 * @author 86188
 * @description: TODO
 * @date 2024/12/15 00:42
 */

public class NoticeService {
    public List<Notice> getNoticeList(Long receiverId){
        return (List) MybatisUtils.executeQuery(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            return mapper.selectByReceiverId(receiverId);
        });
    }
}