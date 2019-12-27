package top.yoga.lol.information.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.information.utils.GetInfoUtils;
import top.yoga.lol.information.utils.RequestsUtils;

import java.util.Map;

/**
 * 获取符文列表信息
 *
 * @author luojiayu
 * @date 2019/12/26 10:51
 */
@Service
public class RunesService {
    @Autowired
    private RequestsUtils requestsUtils;

    @Value("${python-address}")
    private String address;
    @Value("${python-port}")
    private String port;

    public JSONObject getRunesList() {
        return requestsUtils.doGet("http://" + address + ":" + port + "/ParseRunes");
    }

    /**
     * 获取英雄符文列表(python打印的方式)
     *
     * @return
     */
    public JSONObject getRunesList1() {
        Map map = GetInfoUtils.getLoLInfo();
        if (!map.containsKey("runes")) {
            throw new AppException("查询英雄符文信息失败");
        }
        String runes = map.get("runes").toString();
        JSONObject jsonObject = JSON.parseObject(runes);
        return jsonObject;
    }
}
