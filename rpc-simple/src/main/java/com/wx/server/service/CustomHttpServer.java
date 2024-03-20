package com.wx.server.service;

/**
 * @author wangx
 * @apiNote HTTP服务器接口，方便后续可以扩展， 实现多种不同的web服务器
 * @since 2024-03-19
 */
public interface CustomHttpServer {

    /**
     * 启动服务器
     *
     * @param port
     */
    void start(int port);
}
