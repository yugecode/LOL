package top.yoga.lol.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 召唤师技能实体类
 *
 * @author luojiayu
 * @date 2019/12/25 15:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spells {

    private int id;

    private String name;

    private int grade;

    private String description;

    private String heard_photo;

    private String photo;

}
