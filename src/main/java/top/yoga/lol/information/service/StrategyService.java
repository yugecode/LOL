package top.yoga.lol.information.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yoga.lol.information.utils.RequestsUtils;

/**
 * @author luojiayu
 * @date 2020/3/30 15:13
 */
@Service
@Slf4j
public class StrategyService {
    @Value("${python-address}")
    private String address;
    @Value("${python-port}")
    private String port;

    @Autowired
    private RequestsUtils requestsUtils;

    public JSONObject getStrategy(Integer status, Integer pageNum, Integer pageSize) {
        log.info("新闻资讯请求参数：{}，{}，{}", status, pageNum, pageSize);
        String url = "";
        if (pageNum == null || pageSize == null) {
            url = "http://" + address + ":" + port + "/getStrategy?status=" + status;
        } else {
            url = "http://" + address + ":" + port +
                "/getStrategy?status=" + status + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
        }
        JSONObject jsonObject = requestsUtils.doGet(url);
        return jsonObject;
    }
}
