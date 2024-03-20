package com.wx.consumer.proxy;

import java.lang.reflect.Proxy;

/**
 * @author wangx
 * @apiNote 服务代理工厂（用于创建代理对象）
 * @since 2024-03-20
 */
public class ServiceProxyFactory {

   public static <T> T getProxy(Class<T> serviceClass) {
      return (T) Proxy.newProxyInstance(
              serviceClass.getClassLoader(),
              new Class[]{serviceClass},
              new ServiceProxy()
      );
   }

}
