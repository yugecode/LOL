package top.yoga.lol.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import top.yoga.lol.user.utils.UserUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;


/**
 * websocket配置类
 *
 * @author luojiayu
 * @date 2020/2/25 14:06
 */
@Slf4j
@Component
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

    /**
     * 修改握手信息，将shiro的session放入websocket中
     *
     * @param sec
     * @param request
     * @param response
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //获取shiro中保存的用户
        sec.getUserProperties().put("user", UserUtils.getUserInfo());
        super.modifyHandshake(sec, request, response);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
