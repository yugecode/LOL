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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
    public JSONObject doGet1(String url) {
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


    /**
     * GET方式进行请求
     *
     * @param url
     * @return
     */
    public JSONObject doGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (Object key : ((Map) map).keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return JSONObject.parseObject(result);
    }
}
