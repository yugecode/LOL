package top.yoga.lol.information.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.common.exception.CommonExceptionHandler;
import top.yoga.lol.information.entity.Heros;
import top.yoga.lol.information.entity.Spells;
import top.yoga.lol.information.service.HerosService;
import top.yoga.lol.information.service.ItemsService;
import top.yoga.lol.information.service.RunesService;
import top.yoga.lol.information.service.SpellsService;
import top.yoga.lol.information.service.StrategyService;
import top.yoga.lol.information.vo.SpellsResp;

import java.util.List;

/**
 * @author luojiayu
 * @date 2020/1/8 16:10
 */
@RestController
@RequestMapping("/info")
public class InformationController {
    @Autowired
    private HerosService herosService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private RunesService runesService;
    @Autowired
    private SpellsService spellsService;
    @Autowired
    private StrategyService strategyService;

    /**
     * 获取英雄列表信息
     *
     * @return
     */
    @RequestMapping(value = "/heros/getList", method = RequestMethod.GET)
    public ResponseTemplate getHeros() {
        return ResponseTemplate.ok(herosService.getHeros());
    }

    /**
     * 通过英雄id获取英雄详细信息
     *
     * @param heroId
     * @return
     */
    @RequestMapping(value = "/heros/getDetails", method = RequestMethod.GET)
    public ResponseTemplate<JSONObject> getHerosDetails(@RequestParam("heroId") Integer heroId) {
        return ResponseTemplate.ok(herosService.getHerosDetails(heroId));
    }


    /**
     * 获取物品信息列表
     *
     * @return
     */
    @RequestMapping(value = "/items/getList", method = RequestMethod.GET)
    public ResponseTemplate<JSONObject> getItems() {
        return ResponseTemplate.ok(itemsService.getItems());
    }

    /**
     * 获取符文列表
     *
     * @return
     */
    @RequestMapping(value = "/runes/getList", method = RequestMethod.GET)
    public ResponseTemplate<JSONObject> getRunes() {
        return ResponseTemplate.ok(runesService.getRunesList());
    }

    /**
     * 获取所有召唤师技能信息
     *
     * @return
     */
    @RequestMapping(value = "/spells/getList", method = RequestMethod.GET)
    public ResponseTemplate<List<SpellsResp>> getAllSpells() {
        return ResponseTemplate.ok(spellsService.getAllSpells());
    }

    /**
     * 通过召唤师技能id查询召唤师技能详情信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/spells/details", method = RequestMethod.GET)
    public ResponseTemplate<Spells> getSpell(@RequestParam("id") Integer id) {
        return ResponseTemplate.ok(spellsService.getSpell(id));
    }

    /**
     * 新闻资讯
     *
     * @param status   综合,公告,赛事,攻略,社区(0,1,2,3,4)
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getStrategy")
    public ResponseTemplate<JSONObject> getStrategy(Integer status, Integer pageNum, Integer pageSize) {
        return ResponseTemplate.ok(strategyService.getStrategy(status, pageNum, pageSize));
    }
}
