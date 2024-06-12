package com.vtube.netty.config;

import com.vtube.netty.Interceptor.AuthWebSocketInterceptor;
import com.vtube.netty.handler.NettyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private AuthWebSocketInterceptor authWebSocketInterceptor;

    @Autowired
    private NettyWebSocketHandler nettyWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    }
}