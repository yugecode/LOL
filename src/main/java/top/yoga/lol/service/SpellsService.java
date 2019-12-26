package top.yoga.lol.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yoga.lol.dao.SpellsDao;
import top.yoga.lol.entity.Spells;

import java.util.List;

/**
 * 召唤师技能的逻辑层
 *
 * @author luojiayu
 * @date 2019/12/25 15:12
 */
@Service
@Slf4j
public class SpellsService {

    @Autowired
    private SpellsDao spellsDaoDao;

    /**
     * 查询所有召唤师技能信息
     *
     * @return
     */
    public List<Spells> getAllSpells() {
        return spellsDaoDao.findAllSpells();
    }

    /**
     * 通过召唤师技能id查询相应的详情信息
     *
     * @param id
     * @return
     */
    public Spells getSpell(int id) {
        log.info("获取召唤师技能id:{}", id);
        Spells spell = spellsDaoDao.findSpellById(id);
        return spell;
    }

}
