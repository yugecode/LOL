package top.yoga.lol.information.dao;

import org.apache.ibatis.annotations.Mapper;
import top.yoga.lol.information.entity.Spells;

import java.util.List;

/**
 * 操作召唤师技能接口
 */
@Mapper
public interface SpellsDao {

    /**
     * 查询所有召唤师技能
     *
     * @return
     */
    List<Spells> findAllSpells();

    /**
     * 通过id查询对应的召唤师信息
     *
     * @param id
     * @return
     */
    Spells findSpellById(int id);
}
