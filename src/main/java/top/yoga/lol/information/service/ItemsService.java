package top.yoga.lol.information.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yoga.lol.common.exception.AppException;
import top.yoga.lol.information.dao.ItemsDao;
import top.yoga.lol.information.utils.GetInfoUtils;
import top.yoga.lol.information.utils.RequestsUtils;

import java.util.Map;

/**
 * 物品信息
 *
 * @author luojiayu
 * @date 2019/12/26 11:45
 */
@Service
public class ItemsService {

    @Autowired
    private RequestsUtils requestsUtils;
    @Autowired
    private ItemsDao itemsDao;

    @Value("${python-address}")
    private String address;
    @Value("${python-port}")
    private String port;

    /**
     * 获取物品信息
     *
     * @return
     */
    public JSONObject getItems() {
//        JSONObject jsonObject = requestsUtils.doGet("http://" + address + ":" + port + "/ParseItems");
//        itemsDao.insertItems(jsonObject.toString());
        String items = itemsDao.getItems();
        return JSONObject.parseObject(items);
    }

    /**
     * 获取物品信息(python打印的方式)
     *
     * @return
     */
    public JSONObject getItems1() {
        Map map = GetInfoUtils.getLoLInfo();
        if (!map.containsKey("items")) {
            throw new AppException("查询物品信息失败");
        }
        String items = map.get("items").toString();
        JSONObject jsonObject = JSON.parseObject(items);
        return jsonObject;
    }
}
