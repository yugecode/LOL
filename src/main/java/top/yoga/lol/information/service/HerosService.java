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
 * 英雄信息
 *
 * @author luojiayu
 * @date 2019/12/26 11:32
 */
@Service
public class HerosService {

    @Autowired
    private RequestsUtils requestsUtils;

    @Value("${python-address}")
    private String address;
    @Value("${python-port}")
    private String port;

    /**
     * 获取英雄信息
     *
     * @return
     */
    public JSONObject getHeros() {
        long l = System.currentTimeMillis();
        JSONObject jsonObject = requestsUtils.doGet("http://" + address + ":" + port + "/ParseHeros");
        long e = System.currentTimeMillis();
        System.out.println("时间：" + (e - l));
        return jsonObject;
    }

    /**
     * 通过英雄id获取英雄详细信息
     *
     * @param heroId
     * @return
     */
    public JSONObject getHerosDetails(Integer heroId) {
        String url = "http://" + address + ":" + port + "/ParseHerosInfo/" + heroId;
        return requestsUtils.doGet(url);
    }

    /**
     * 获取英雄信息(python打印的方式)
     *
     * @return
     */
    public JSONObject getHeros1() {
        Map map = GetInfoUtils.getLoLInfo();
        if (!map.containsKey("heros")) {
            throw new AppException("查询英雄信息失败");
        }
        String heros = map.get("heros").toString();
        JSONObject jsonObject = JSON.parseObject(heros);
        return jsonObject;
    }
}
