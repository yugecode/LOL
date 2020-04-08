package top.yoga.lol.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import top.yoga.lol.tweet.entity.Message;
import top.yoga.lol.user.dao.UserDao;
import top.yoga.lol.user.entity.User;
import top.yoga.lol.user.service.UserService;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务类
 * 传递发送消息人的id
 *
 * @author luojiayu
 * @date 2020/2/26 9:24
 */
@ServerEndpoint(value = "/websocket")
@Component
@Slf4j
public class WebSocketServer {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //spring上下文，用于获取userSerivce
    private static ApplicationContext applicationContext;
    //用于接收shiro中的用户信息
    private User shiroUser;
    //用户service信息
    private UserService userService;
    @Autowired
    private UserDao userDao;
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<Integer, WebSocketServer> webSocketSet = new ConcurrentHashMap();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") Integer id) {
        this.session = session;
        //注入userService
        this.userService = applicationContext.getBean(UserService.class);
        this.userDao = applicationContext.getBean(UserDao.class);
        //设置用户
        this.shiroUser = (User) session.getUserProperties().get("user");
        webSocketSet.put(id, this);//加入set中
        addOnlineCount();//在线人数加1
        log.info("有新连接加入!当前在线人数为:{}", getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为：{}", getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //收到消息不做处理
//        User user = userDao.getUserById(id);
//        log.info("收到来自窗口:{}的信息:{}", user.getUserName(), message);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送(文本内容)
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("推送消息失败：{}", e.getMessage());
        }
    }

    /**
     * 实现服务器主动推送（对象）
     *
     * @param message
     */
    public void sendMessage(Message message) {
        try {
            this.session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            log.error("推送消息失败：{}", e.getMessage());
        }
    }


    /**
     * 群发自定义消息
     */
    public void sendInfo(String message) {
        for (Integer item : webSocketSet.keySet()) {
            User user = userDao.getUserById(item);
            webSocketSet.get(item).sendMessage(message);
            log.info("推送消息到窗口：{}，推送内容:{}", user.getUserName(), message);
        }
    }

    /**
     * 给指定的某给人推送消息
     *
     * @param message 消息（对象）
     */
    public void sendToUser(Message message) {
        User user = userDao.getUserById(message.getUserId());
        User touser = userDao.getUserById(message.getToUserId());
        if (null != webSocketSet.get(message.getToUserId())) {
            webSocketSet.get(message.getToUserId()).sendMessage(message);
            log.info("当前用户为：{}，推送用户为：{}，推送消息为：{}", user.getUserName(), touser.getUserName(), message);
        }
    }

    /**
     * 获取在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 增加在线人数
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 减少在线人数
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}