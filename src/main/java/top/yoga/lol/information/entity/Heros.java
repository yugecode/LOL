package top.yoga.lol.information.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luojiayu
 * @date 2020/2/25 18:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Heros {
    private Integer heroId;

    private String name;

    private String title;

    private String iconImg;

    private List<String> rolesList;

    private String roles;
}
