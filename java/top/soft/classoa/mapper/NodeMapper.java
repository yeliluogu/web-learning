package top.soft.classoa.mapper;

import top.soft.classoa.entity.Node;

import java.util.List;

public interface NodeMapper {
    List<Node> selectNodeByUserId(Long userId);
}
