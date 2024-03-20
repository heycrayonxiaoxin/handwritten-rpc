package com.wx.server;

import com.wx.server.model.SimpleRpcRequest;
import com.wx.server.model.SimpleRpcResponse;
import com.wx.server.registry.LocalRegistryCenter;
import com.wx.server.serializer.Serializer;
import com.wx.server.serializer.impl.JdkSerializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author wangx
 * @apiNote HTTP请求处理
 * @since 2024-03-20
 */
public class SimpleHttpRequestHandler implements Handler<HttpServerRequest> {

   @Override
   public void handle(HttpServerRequest event) {
      // 指定序列化器
      final Serializer serializer = new JdkSerializer();

      // 记录日志
      System.out.println("Received request: " + event.method() + " " + event.uri());

      // 异步处理 HTTP 请求
      event.bodyHandler(body -> {
         byte[] bytes = body.getBytes();
         SimpleRpcRequest rpcRequest = null;
         try {
            rpcRequest = serializer.deserialize(bytes, SimpleRpcRequest.class);
         } catch (Exception e) {
            e.printStackTrace();
         }

         // 构造响应结果对象
         SimpleRpcResponse rpcResponse = new SimpleRpcResponse();
         // 如果请求为 null，直接返回
         if (rpcRequest == null) {
            rpcResponse.setMessage("rpcRequest is null");
            doResponse(event, rpcResponse, serializer);
            return;
         }

         try {
            // 获取要调用的服务实现类，通过反射调用
            Class<?> implClass = LocalRegistryCenter.get(rpcRequest.getServiceName());
            Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
            Object result = method.invoke(implClass.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
            // 封装返回结果
            rpcResponse.setData(result);
            rpcResponse.setDataType(method.getReturnType());
            rpcResponse.setMessage("ok");
         } catch (Exception e) {
            e.printStackTrace();
            rpcResponse.setMessage(e.getMessage());
            rpcResponse.setException(e);
         }
         // 响应
         doResponse(event, rpcResponse, serializer);
      });
   }

   /**
    * 响应
    *
    * @param request 请求
    * @param rpcResponse 响应
    * @param serializer 序列化器
    */
   void doResponse(HttpServerRequest request, SimpleRpcResponse rpcResponse, Serializer serializer) {
      HttpServerResponse httpServerResponse = request.response()
              .putHeader("content-type", "application/json");
      try {
         // 序列化
         byte[] serialized = serializer.serialize(rpcResponse);
         httpServerResponse.end(Buffer.buffer(serialized));
      } catch (IOException e) {
         e.printStackTrace();
         httpServerResponse.end(Buffer.buffer());
      }
   }
}
