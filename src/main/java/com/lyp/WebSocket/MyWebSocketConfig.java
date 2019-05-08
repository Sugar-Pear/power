package com.lyp.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Component
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        webSocketHandlerRegistry.addHandler(WebSocketPushHandler(),"/webSocketServer").addInterceptors(new MyWebSocketInterceptor());
        webSocketHandlerRegistry.addHandler(WebSocketPushHandler(), "/sockjs/webSocketServer").addInterceptors(new MyWebSocketInterceptor()).withSockJS();
    }

    @Bean
    private WebSocketHandler WebSocketPushHandler() {
        return new MyWebSocketHandler();
    }
}
