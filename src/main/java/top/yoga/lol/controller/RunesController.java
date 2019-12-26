package top.yoga.lol.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.service.RunesService;

/**
 * 英雄符文信息
 *
 * @author luojiayu
 * @date 2019/12/26 10:56
 */
@RestController
@RequestMapping("runes")
public class RunesController {

    @Autowired
    private RunesService runesService;

    /**
     * 获取符文列表
     *
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseTemplate<JSONObject> getRunes() {
        return ResponseTemplate.ok(runesService.getRunesList());
    }
}
