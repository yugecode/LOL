package top.yoga.lol.information.entity;

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

    /**
     * 技能id
     */
    private int id;

    /**
     * 技能名称
     */
    private String name;

    /**
     * 技能等级
     */
    private int grade;

    /**
     * 技能描述
     */
    private String description;

    /**
     * 技能头像
     */
    private String heard_photo;

    /**
     * 技能效果图
     */
    private String photo;

}
