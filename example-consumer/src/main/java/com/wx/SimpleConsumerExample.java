package com.wx;

import com.wx.common.model.User;
import com.wx.common.service.UserService;
import com.wx.consumer.proxy.ServiceProxyFactory;

/**
 * @author wangx
 * @apiNote 简单的服务消费者示例
 * @since ${DATE}
 */
public class SimpleConsumerExample {
    public static void main(String[] args) {

        // 获取UserService的实现类对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User userParam = new User();
        userParam.setUsername("orign");
        User userResult = userService.getUser(userParam);

        if (userResult != null) {
            System.out.println(userResult.getUsername());
        } else {
            System.out.println("user == null");
        }
    }
}