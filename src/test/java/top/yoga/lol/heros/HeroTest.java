package top.yoga.lol.heros;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yoga.lol.information.service.HerosService;
import top.yoga.lol.information.service.ItemsService;
import top.yoga.lol.information.service.SpellsService;
import top.yoga.lol.information.service.StrategyService;

/**
 * @author luojiayu
 * @date 2020/4/9 16:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HeroTest {

    @Autowired
    private HerosService herosService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private SpellsService spellsService;

    @Autowired
    private StrategyService strategyService;

    /***
     * @author luojiayu
     * @date 2020/4/9
     */
    @Test
    public void getHeroList() {
        //英雄列表
        Assert.assertNotNull(herosService.getHeros());
        //英雄详情
        Assert.assertNotNull(herosService.getHerosDetails(1));
        //物品列表及其详情
        Assert.assertNotNull(itemsService.getItems());
        //召唤师技能列表
        Assert.assertNotNull(spellsService.getAllSpells());
        //召唤师技能详情
        Assert.assertNotNull(spellsService.getSpell(1));
        //新闻资讯列表
        Assert.assertNotNull(strategyService.getStrategy(0, null, null));
    }
}
