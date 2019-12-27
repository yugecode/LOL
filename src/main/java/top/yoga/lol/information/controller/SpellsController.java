package top.yoga.lol.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.information.entity.Spells;
import top.yoga.lol.information.service.SpellsService;
import top.yoga.lol.information.vo.SpellsResp;

import java.util.List;

/**
 * 召唤师技能信息
 *
 * @author luojiayu
 * @date 2019/12/25 15:16
 */
@RestController
@RequestMapping("/spells")
public class SpellsController {

    @Autowired
    private SpellsService spellsService;

    /**
     * 获取所有召唤师技能信息
     *
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseTemplate<List<SpellsResp>> getAllSpells() {
        return ResponseTemplate.ok(spellsService.getAllSpells());
    }

    /**
     * 通过召唤师技能id查询召唤师技能详情信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseTemplate<Spells> getSpell(@RequestParam("id") Integer id) {
        return ResponseTemplate.ok(spellsService.getSpell(id));
    }
}
