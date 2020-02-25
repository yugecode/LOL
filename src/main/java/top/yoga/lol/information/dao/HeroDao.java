package top.yoga.lol.information.dao;

import org.apache.ibatis.annotations.Mapper;
import top.yoga.lol.information.entity.Heros;

import java.util.List;

/**
 * @author luojiayu
 * @date 2020/2/25 18:13
 */
@Mapper
public interface HeroDao {

    int bacthHeros(List<Heros> list);

    List<Heros> heros();

    int updateHeros(Heros heros);
}
