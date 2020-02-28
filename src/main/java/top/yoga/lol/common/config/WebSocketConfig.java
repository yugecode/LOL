package top.yoga.lol.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * websocket配置类
 *
 * @author luojiayu
 * @date 2020/2/25 14:06
 */
//@Component
@Configuration
public class WebSocketConfig {

//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
