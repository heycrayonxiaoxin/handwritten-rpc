package com.wx;

import com.wx.common.service.UserService;
import com.wx.provider.UserServiceImpl;
import com.wx.server.registry.LocalRegistryCenter;
import com.wx.server.service.CustomHttpServer;
import com.wx.server.service.impl.VertxHttpServer;

/**
 * @author wangx
 * @apiNote
 * @since ${DATE}
 */
public class SimpleProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistryCenter.register(UserService.class.getName(), UserServiceImpl.class);

        // 提供服务
        CustomHttpServer httpServer = new VertxHttpServer();
        httpServer.start(8888);
    }
}