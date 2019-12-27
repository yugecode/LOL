package top.yoga.lol.information.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.yoga.lol.information.dao.SpellsDao;
import top.yoga.lol.information.entity.Spells;
import top.yoga.lol.information.vo.SpellsResp;

import java.util.ArrayList;
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
    public List<SpellsResp> getAllSpells() {
        List<Spells> SpellsList = spellsDaoDao.findAllSpells();
        List<SpellsResp> spellsResps = new ArrayList<>();
        if (!CollectionUtils.isEmpty(SpellsList)) {
            for (Spells spells : SpellsList) {
                SpellsResp spellsResp = new SpellsResp();
                BeanUtils.copyProperties(spells, spellsResp);
                spellsResps.add(spellsResp);
            }
        }
        return spellsResps;
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
