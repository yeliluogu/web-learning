package top.soft.classoa.service;

import top.soft.classoa.entity.Node;
import top.soft.classoa.mapper.NodeMapper;
import top.soft.classoa.utils.MybatisUtils;

import java.util.List;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 14:34
 */
public class NodeService {
    public List<Node> selectNodeByUserId(Long userId) {
        return (List<Node>) MybatisUtils.executeQuery(sqlSession -> {
            NodeMapper mapper = sqlSession.getMapper(NodeMapper.class);
            return mapper.selectNodeByUserId(userId);
        });


    }
}