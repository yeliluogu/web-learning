package top.soft.classoa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.classoa.entity.Department;
import top.soft.classoa.entity.Employee;
import top.soft.classoa.entity.Node;
import top.soft.classoa.service.DepartmentService;
import top.soft.classoa.service.EmployeeService;
import top.soft.classoa.service.NodeService;
import top.soft.classoa.utils.ResponseUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 14:38
 */
@WebServlet("/api/user")
public class UserInfoServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService();
    private final NodeService nodeService = new NodeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String eid = req.getParameter("eid");

        Employee employee = employeeService.selectById(Long.parseLong(eid));
        Department department = departmentService.selectById(employee.getDepartmentId());

        List<Node> nodes = nodeService.selectNodeByUserId(Long.parseLong(uid));
        List<Map<String,Object>> treeList = new ArrayList<>();
        Map<String,Object> module = null;
        for (Node node : nodes) {
            if (node.getNodeType()==1){
                module = new LinkedHashMap<>();
                module.put("node",node);
                module.put("children",new ArrayList<>());
                treeList.add(module);
            }else if (node.getNodeType()==2){
                assert module != null;
                List<Node> children = (List<Node>) module.get("children");
                children.add(node);
            }
        }
        String json = new ResponseUtils().put("employee",employee)
                .put("department",department)
                .put("nodeList",treeList).toJsonString();
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
