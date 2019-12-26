package top.yoga.lol.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 召唤师技能
 *
 * @author luojiayu
 * @date 2019/12/26 11:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpellsResp {

    /**
     * 技能id
     */
    private Integer id;

    /**
     * 技能名称
     */
    private String name;

    /**
     * 技能头像
     */
    private String heard_photo;
}
