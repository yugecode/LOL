package top.yoga.lol.information.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.information.service.ItemsService;

/**
 * 物品信息
 *
 * @author luojiayu
 * @date 2019/12/26 11:48
 */
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    /**
     * 获取物品信息列表
     *
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseTemplate<JSONObject> getItems() {
        return ResponseTemplate.ok(itemsService.getItems());
    }
}
