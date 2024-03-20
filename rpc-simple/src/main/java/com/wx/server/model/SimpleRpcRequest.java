package com.wx.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangx
 * @apiNote
 * @since 2024-03-20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRpcRequest implements Serializable {

   /**
    * 服务名称
    */
   private String serviceName;

   /**
    * 方法名称
    */
   private String methodName;

   /**
    * 方法的参数类型列表
    */
   private Class<?>[] parameterTypes;

   /**
    * 方法的参数列表
    */
   private Object[] args;

}
