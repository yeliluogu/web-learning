package top.soft.classoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 24399
 * @description: TODO
 * @date 2024/11/30 14:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long userId;
    private String username;
    private String password;
    private Long employeeId;
    private Integer salt;

}
