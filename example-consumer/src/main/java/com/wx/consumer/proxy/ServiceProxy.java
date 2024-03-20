package com.wx.consumer.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wx.server.model.SimpleRpcRequest;
import com.wx.server.model.SimpleRpcResponse;
import com.wx.server.serializer.impl.JdkSerializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangx
 * @apiNote
 * @since 2024-03-20
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();

        // 构造请求
        SimpleRpcRequest rpcRequest = SimpleRpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            // TODO 这里地址被硬编码了（需要使用注册中心和服务发现机制解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8888")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();

                // 反序列化
                SimpleRpcResponse rpcResponse = serializer.deserialize(result, SimpleRpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
