package com.wx.server.service.impl;

import com.wx.server.SimpleHttpRequestHandler;
import com.wx.server.service.CustomHttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * @author wangx
 * @apiNote
 * @since 2024-03-19
 */
public class VertxHttpServer implements CustomHttpServer {
    @Override
    public void start(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        // server.requestHandler(request -> {
        //     // 处理 HTTP 请求
        //     System.out.println("Received request: " + request.method() + " " + request.uri());
        //     // 发送 HTTP 响应
        //     request.response()
        //             .putHeader("content-type", "text/plain")
        //             .end("Hello from Vert.x HTTP server!");
        // });

        // 完成请求处理器（SimpleHttpRequestHandler）后， 上述修改后的代码
        server.requestHandler(new SimpleHttpRequestHandler());

        // 启动 HTTP 服务器并监听指定端口
        HttpServer listen = server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
        System.out.println(listen.getClass());
    }
}
