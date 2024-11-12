package top.soft.grammerlist.servlet;


import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.soft.grammerlist.entity.Brand;

import java.io.IOException;
import java.util.List;

/**
 * @author 86188
 * @description: TODO
 * @date 2024/11/9 16:12
 */
@WebServlet("/brand")
public class BrandServlet extends HttpServlet {
    private List<Brand> getBrandList() {
        return List.of(
                Brand.builder().id(101).companyName("apple").brandName("iPhone16").description("苹果-iPhone16").ordered(1).build(),
                Brand.builder().id(102).companyName("huawei").brandName("mate60").description("华为-mate60").ordered(2).build(),
                Brand.builder().id(103).companyName("benz").brandName("mercedes").description("奔驰-梅赛德斯").ordered(3).build());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brandList = getBrandList();
        resp.setContentType("application/json;charset=utf-8");
        req.getServletContext().setAttribute("brands", brandList);
        String jsonString = JSON.toJSONString(brandList);
        resp.getWriter().write(jsonString);


    }

}