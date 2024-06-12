package com.vtube.netty.config;

import com.vtube.netty.server.NettyWebSocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class NettyConfig {

    @Value("${netty.server.host:0.0.0.0}")
    private String host;

    @Value("${netty.server.port:8080}")
    private int port;

    @Bean
    public CommandLineRunner commandLineRunner(NettyWebSocketServer nettyWebSocketServer) {
        return args -> {
            nettyWebSocketServer.start(host, port);
        };
    }
}