package com.vtube.netty.server;

import com.vtube.netty.handler.NettyWebSocketHandler;
import com.vtube.netty.handler.TextWebSocketFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class NettyWebSocketServer {

    @Autowired
    private NettyWebSocketHandler nettyWebSocketHandler;

    public void start(String host, int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 添加HTTP服务器编解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 添加ChunkedWriteHandler以支持异步写大数据流
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 将HTTP请求的多个部分聚合为一条完整的HTTP消息
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            // 添加WebSocket服务器协议处理器
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            // 添加自定义的业务处理Handler
                            pipeline.addLast(nettyWebSocketHandler);
                        }
                    });

            // 绑定服务器到指定的地址和端口
            ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}