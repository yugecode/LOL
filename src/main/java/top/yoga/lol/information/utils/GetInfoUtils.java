package top.yoga.lol.information.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 从python爬虫程序获取LOL相关信息的工具类
 *
 * @author luojiayu
 * @date 2019/12/25 14:37
 */
public class GetInfoUtils {

    /**
     * 调用python爬虫程序获取英雄联盟相关信息
     *
     * @return
     */
    public static Map getLoLInfo() {
        //python文件所在地址
        String address = "D:\\python\\project\\lol\\venv\\LoL\\LOLSpider.py";
        Map<String, String> map = new HashMap<>();
        BufferedReader in = null;
        String[] arguments = new String[]{"python", address};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            in = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("@@##@@");
            }
            //获取符文列表信息
            map.put("runes", stringBuffer.toString().split("@@##@@")[0]);
            //获取英雄列表信息
            map.put("heros", stringBuffer.toString().split("@@##@@")[1]);
            //获取物品列表信息
            map.put("items", stringBuffer.toString().split("@@##@@")[2]);
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

}
