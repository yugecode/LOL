package top.yoga.lol.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yoga.lol.user.dao.UserDao;
import top.yoga.lol.user.entity.User;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务类
 *
 * @author luojiayu
 * @date 2020/2/26 9:24
 */
@Slf4j
@Component
@ServerEndpoint(value = "/webSocket/{userId}")
public class WebSocketServer {

    @Autowired
    private UserDao userDao;

    //用于存放每个客户端存对应的WebSocketServer对象
    private static ConcurrentHashMap<Integer, Session> webSocketSet = new ConcurrentHashMap();

    /**
     * 连接成功调用的方法
     *
     * @param userId  用户id
     * @param session 用户对应的session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") Integer userId, Session session) {
        webSocketSet.put(userId,session);
        User user = userDao.getUserById(userId);
        List<String> totalPushMsgs = new ArrayList();
        totalPushMsgs.add("欢迎您:"+ user.getUserName());
        if(totalPushMsgs != null && !totalPushMsgs.isEmpty()) {
            totalPushMsgs.forEach(e -> this.sendMessage(userId,e));
        }
    }

    public void sendMessage(Integer userId,String message) {
        try {
            Session currentSession = webSocketSet.get(userId);
            if(currentSession != null){
                currentSession.getBasicRemote().sendText(message);
            }
            log.info("推送消息成功，消息为：" + message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 自定义消息
     */
    public static void sendInfo(String userId,String message) throws IOException {
        Session currentSession = webSocketSet.get(userId);
        if(currentSession != null){
            currentSession.getBasicRemote().sendText(message);
        }
    }

    /**
     * 用户退出时，连接关闭调用的方法
     */
    public static void onCloseConection(String userId) {
        webSocketSet.remove(userId); // 从set中删除
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("一个客户端关闭连接");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket出现错误");
    }
}
