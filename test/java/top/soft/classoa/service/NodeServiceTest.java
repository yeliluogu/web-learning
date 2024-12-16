package top.soft.classoa.service;

import org.junit.jupiter.api.Test;
import top.soft.classoa.entity.Node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NodeServiceTest {
    private final NodeService nodeService=new NodeService();
    @Test
    void selectNodeByUserId() {
        List<Node> nodes = nodeService.selectNodeByUserId(1L);
        List<Map<String, Object>> treeList = new ArrayList<>();
        Map<String, Object> module = null;
        for (Node node : nodes) {
            if (node.getNodeType() == 1) {
                module = new LinkedHashMap<>();
                module.put("node", node);
                module.put("children", new ArrayList<>());
                treeList.add(module);
            } else if (node.getNodeType() == 2) {
                List<Node> children = (List<Node>) module.get("children");
                children.add(node);
            }
        }
        System.out.println(treeList);
    }
}