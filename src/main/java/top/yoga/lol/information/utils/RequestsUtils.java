package top.yoga.lol.information.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import top.yoga.lol.common.exception.AppException;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 公共请求类，请求三方接口
 */
@Slf4j
@Component
public class RequestsUtils {

    @Resource
    private RestTemplate restTemplate;

    /**
     * GET方式进行请求
     *
     * @param url
     * @return
     */
    public JSONObject doGet(String url) {
        MultiValueMap<String, String> header = new HttpHeaders();
        log.info("访问路径为:{}", url);
        HttpEntity httpEntity = new HttpEntity(header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<String>() {
                });
        String jsonStr = "";
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            jsonStr = responseEntity.getBody();
        }
        if (Objects.isNull(jsonStr)) {
            log.error("获取英雄联盟数据失败");
            throw new AppException("未知错误，获取数据失败");
        }
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        log.info("获取接口信息为: {}", jsonObj);
        return jsonObj;
    }

    /**
     * POST方式请求
     *
     * @param url
     * @param json
     * @return
     */
    public String doPost(String url, JSONObject json) {
        log.info("访问路径为:{}", url);
        log.info("请求内容为:{}", json);
        //设置Http Header
        HttpHeaders headers = new HttpHeaders();
        //设置中文编码
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        //设置请求媒体数据类型
        //headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(type);
        //设置返回媒体数据类型
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(json.toString(), headers);
        String str = restTemplate.postForObject(url, formEntity, String.class);
        return str;
    }
}
