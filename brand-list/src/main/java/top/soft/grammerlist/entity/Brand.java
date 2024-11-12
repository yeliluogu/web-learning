package top.soft.grammerlist.entity;


import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 24399
 * @description: TODO
 * @date 2024/11/9 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    private Integer id;
    private String companyName;
    private String brandName;
    private Integer ordered;
    private String description;
}