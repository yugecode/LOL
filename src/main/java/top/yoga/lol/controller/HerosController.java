package top.yoga.lol.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.yoga.lol.common.ResponseTemplate;
import top.yoga.lol.service.HerosService;

/**
 * 英雄信息
 *
 * @author luojiayu
 * @date 2019/12/26 11:38
 */
@RestController
@RequestMapping("/heros")
public class HerosController {

    @Autowired
    private HerosService herosService;

    /**
     * 获取英雄列表信息
     *
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseTemplate<JSONObject> getHeros() {
        return ResponseTemplate.ok(herosService.getHeros());
    }
}
