package com.lyp.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;


@Component
public class MyWebSocketHandler implements WebSocketHandler {


    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生
    public static final Map<String, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<>();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String userNumber = (String) webSocketSession.getAttributes().get("userNumber");
        if(userSocketSessionMap.get(userNumber) ==null){

            userSocketSessionMap.put(userNumber,webSocketSession);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
